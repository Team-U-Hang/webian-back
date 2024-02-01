package uhang.uhang.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.review.domain.repository.ReviewRepository;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.dto.ReviewDTO;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 후기 등록
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // 후기 조회
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}