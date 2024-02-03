package uhang.uhang.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public Review findByCommentId(long commentId);

    List<Review> findAll();
}
