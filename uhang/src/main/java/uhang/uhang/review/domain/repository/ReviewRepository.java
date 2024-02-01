package uhang.uhang.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.review.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
