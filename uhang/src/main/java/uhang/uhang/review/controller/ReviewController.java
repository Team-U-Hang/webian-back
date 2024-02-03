package uhang.uhang.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.dto.ReviewRequestDTO;
import uhang.uhang.review.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 후기 등록
    /*@PostMapping("/post/{id}/reviews")
    public Review createPost(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }*/

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity reviewSave(@PathVariable Long id,
                                      @RequestBody ReviewRequestDTO dto,
                                      @LoginUser UserSessionDto userSessionDto) {
        return ResponseEntity.ok(reviewService.reviewSave(userSessionDto.getNickname(), id, dto));
    }

    // 후기 조회
//    @GetMapping("/show/{id}/all-comment")
//    public List<Review> getAllReviews() {
//        return reviewService.getAllReviews();
//    }

}
