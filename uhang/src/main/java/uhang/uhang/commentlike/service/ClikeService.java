package uhang.uhang.commentlike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@RequiredArgsConstructor
public class ClikeService {

    private final MemberRepository memberRepository;
  //  private final ReviewRepository commentRepository;
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

//    @Transactional
//    public void countLike(ClikeDto clikeDto) {
//        Comment comment = commentRepository.findByCommentId(clikeDto.getComment());
//        Optional<CommentLike> countedlike = clikeRepository.countByComment(comment);
//    }

}
