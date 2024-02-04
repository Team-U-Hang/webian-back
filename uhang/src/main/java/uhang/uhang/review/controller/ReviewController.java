package uhang.uhang.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.response.Response;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.dto.ReviewDTO;
import uhang.uhang.review.service.ReviewService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static uhang.uhang.response.Message.COMMENT_LIKE_SUCCESS;
import static uhang.uhang.response.Message.COMMENT_POST_SUCCESS;
import static uhang.uhang.response.Response.success;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 후기 등록
    @PostMapping("/post/reviews")
    public Review createPost(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }


    // 후기 조회
    @GetMapping("/show/all-comment")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

//나의 후기
    @GetMapping("/mypage/my-comments")
    public List<Review> getCommentsByCurrentMember() {

        return reviewService.getCommentsByCurrentMember();
    }
    }
