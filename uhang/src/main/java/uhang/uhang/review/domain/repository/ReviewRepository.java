package uhang.uhang.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public Review findByCommentId(Long commentId);

    List<Review> findByPostEventId(Long eventId);
}
