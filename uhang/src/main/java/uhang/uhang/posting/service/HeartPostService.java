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

import java.util.Optional;

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

}
