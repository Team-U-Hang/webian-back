package uhang.uhang.posting.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostRequestDto;
import uhang.uhang.posting.dto.PostResponseDto;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }
    @Autowired
    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    // 이벤트 게시물 등록 -> 이벤트 아이디값 넘기기
    @Transactional
    public Long savePost(PostRequestDto postDto) {

        Post post = postRepository.save(postDto.toEntity());
        Member member = getCurrentMember();

        return post.getEventId();
    }

    // 이벤트 게시물 상세 조회 -> 이벤트 아이디값 받아와서
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        assert post != null;

        return convertToDTO(post);
    }

    private PostResponseDto convertToDTO(Post post) {
        return PostResponseDto.builder()
                .eventId(post.getEventId())
                .eventTitle(post.getEventTitle())
                .eventTime(post.getEventTime())
                .eventDate(post.getEventDate())
                .eventLoc(post.getEventLoc())
                .eventType(post.getEventType())
                .eventText(post.getEventText())
                .imageUrl(post.getImageUrl())
                .timeStamp(post.getTimeStamp())
                .totalLike(post.getTotalLike())
                .build();
    }

}