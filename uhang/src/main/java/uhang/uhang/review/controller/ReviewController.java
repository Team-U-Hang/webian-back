package uhang.uhang.review.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.service.ReviewService;

import java.util.List;

    @RestController
    public class ReviewController {
        private final ReviewService reviewService;

        @Autowired
        public ReviewController(ReviewService reviewService) {
            this.reviewService = reviewService;

        }


        //후기 등록
        //이해 안됨!!
        /*
        @PostMapping("/post/{eventId}/postingReview")
        public Integer reviewSave(@PathVariable(name = "eventId") Long eventId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
            return reviewService.reviewSave(eventId, reviewRequestDTO);
        }


        // 후기 조회
        @GetMapping("/post/{eventId}/gettingReview")
        public List<ReviewResponseDTO> getReviewsByPostId(@PathVariable(name = "eventId") Long eventId) {
            return reviewService.getReviews(eventId);
        }


         */
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


