package uhang.uhang.commentlike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.login.domain.Member;
import uhang.uhang.review.domain.entity.Review;

import java.util.Optional;

public interface ClikeRepository extends JpaRepository<CommentLike, Integer> {
    public Optional<CommentLike> findByMemberAndReview(Member member, Review review);

    public Integer countByReview(Review review);
}