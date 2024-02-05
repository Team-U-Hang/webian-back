package uhang.uhang.commentlike.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.login.domain.Member;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ClikeRepository extends JpaRepository<CommentLike, Integer> {
    public Optional<CommentLike> findByMemberAndReview(Member member, Review review);

    public Integer countByReview(Review review);
    @Query("SELECT cl.review.commentId " +
            "FROM commentlike cl " +
            "GROUP BY cl.review.commentId " +
            "ORDER BY COUNT(cl.review.commentId) DESC")
    List<Long> findTop3CommentIdsByOrderByLikeCountDesc(Pageable pageable);
}