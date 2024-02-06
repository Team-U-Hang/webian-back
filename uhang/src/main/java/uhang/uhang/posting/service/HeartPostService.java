package uhang.uhang.posting.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostLike;
import uhang.uhang.posting.domain.repository.PostLikeRepository;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.HeartDto;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeartPostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @Transactional
    public void heartpost(HeartDto heartDto){

        Member member = getCurrentMember();
        Post post = postRepository.findByEventId(heartDto.getPost());

        // 이미 좋아요되어있으면 제거하고, 좋아요 없으면 만들기
        Optional<PostLike> findLike = postLikeRepository.findByMemberAndPost(member, post);
        if(findLike.isEmpty()){

            PostLike postLike = PostLike.builder()
                    .member(member)
                    .post(post)
                    .build();
            postLikeRepository.save(postLike);
        } else postLikeRepository.delete(findLike.get());
    }

    //bestpost 를 위해 추가된 부분
    @Transactional
    public List<Post> findTopThreePosts() {
        List<Object[]> topThreeEvents = postLikeRepository.findTopThreeEventIdsWithUniqueMemberCounts();

        // 상위 3개의 이벤트 ID를 추출
        List<Long> topThreeEventIds = topThreeEvents.stream()
                .limit(3)
                .map(array -> (Long) array[0])
                .collect(Collectors.toList());

        // 상위 3개의 이벤트 ID에 해당하는 포스트 엔티티를 검색하여 반환
        return postRepository.findByEventIdIn(topThreeEventIds);
    }
// 여기까지 추가된 부분
}
