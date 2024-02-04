package uhang.uhang.posting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostLike;
import uhang.uhang.posting.domain.repository.PostLikeRepository;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostDto;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public PostService(PostRepository postRepository, PostLikeRepository postLikeRepository, MemberRepository memberRepository) {

        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.memberRepository = memberRepository;
    }

    // 이벤트 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }



    public Page<Post> getPostsByEventTypes(List<Integer> eventTypes, Pageable pageable) {
        return postRepository.findByEventTypeIn(eventTypes, pageable);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }


    public Member getCurrentMember() {
        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member == null) {
            throw new LogInRequiredException();
        }
        return member;
    }
  //  public List<Post> getPosts

    public List<Post> getLikedPostsByCurrentMember() {
        Member currentMember = getCurrentMember();
        List<PostLike> postLikes = postLikeRepository.findByMember(currentMember);
        // Extract the posts from the liked entities
        return postLikes.stream()
                .map(PostLike::getPost)
                .toList();
    }





    // 게시글 상세 정보 조회
    public PostDto getPostDtoById(Long eventId) {
        Post post = postRepository.findById(eventId).orElse(new Post());
        return convertToDto(post);
    }

    private PostDto convertToDto(Post post) {
        return PostDto.builder()
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