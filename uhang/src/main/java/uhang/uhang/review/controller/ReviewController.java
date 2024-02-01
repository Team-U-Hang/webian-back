package uhang.uhang.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.dto.ReviewDTO;
import uhang.uhang.review.service.ReviewService;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 후기 등록
    @PostMapping("reviews")
    public Review createPost(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }


    // 후기 조회
    @GetMapping("reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


}