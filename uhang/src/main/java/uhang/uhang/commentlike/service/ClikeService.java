package uhang.uhang.commentlike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.commentlike.domain.repository.ClikeRepository;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.exception.LogInRequiredException;

import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@RequiredArgsConstructor
public class ClikeService {

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;
    private final ClikeRepository clikeRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @Transactional
    public void clikeinsert(ClikeDto clikeDto){

        Member member = getCurrentMember();
        Review review = reviewRepository.findByCommentId(clikeDto.getReview());


        // 이미 좋아요되어있으면 제거하고, 좋아요 없으면 만들기
        Optional<CommentLike> findLike = clikeRepository.findByMemberAndReview(member, review);
        if(findLike.isEmpty()){

            CommentLike commentLike = CommentLike.builder()
                    .member(member)
                    .review(review)
                    .build();
            clikeRepository.save(commentLike);
        } else clikeRepository.delete(findLike.get());
    }

    @Transactional
    public Integer getCountByReview(Review review) {
        return clikeRepository.countByReview(review);
    }

    // 좋아요 개수 기준으로 상위 3개 댓글 ID 조회
    public List<Integer> findTop3CommentIdsByLikeCount() {
        // Create Pageable with top 3
        Pageable top3Pageable = PageRequest.of(0, 3);

        // Retrieve Comment IDs
        return clikeRepository.findTop3CommentIdsByOrderByLikeCountDesc(top3Pageable);
    }


}
