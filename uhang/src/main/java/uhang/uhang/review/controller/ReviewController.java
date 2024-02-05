package uhang.uhang.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostResponseDto;
import uhang.uhang.posting.service.PostService;
import uhang.uhang.response.Response;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;
import uhang.uhang.review.dto.ReviewRequestDTO;
import uhang.uhang.review.dto.ReviewResponseDTO;
import uhang.uhang.review.service.ReviewService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static uhang.uhang.response.Message.COMMENT_LIKE_SUCCESS;
import static uhang.uhang.response.Message.COMMENT_POST_SUCCESS;
import static uhang.uhang.response.Response.success;
@RequestMapping("/post/{eventId}")
public class ReviewController {
    private final ReviewService reviewService;
    private final PostService postService;
    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;

    @Autowired
    public ReviewController(ReviewService reviewService, PostService postService, ReviewRepository reviewRepository, PostRepository postRepository) {
        this.reviewService = reviewService;
        this.postService = postService;
        this.reviewRepository = reviewRepository;
        this.postRepository = postRepository;
    }

    // 후기 등록
    /*@PostMapping("/post/{id}/reviews")
    @PostMapping("/post/reviews")
    public Review createPost(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }*/

    // 이벤트 아이디 받아오기
//    @GetMapping("post/getEventId")
//    public PostResponseDto getPost(@PathVariable(name="getEventId") Long getEventId) {
//        return reviewService.getPostById(getEventId);
//    }

   // 후기 등록
   @PostMapping("/postingReview")
   public Long reviewSave(@PathVariable(name = "eventId") Long eventId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
       return reviewService.reviewSave(eventId,reviewRequestDTO);
   }


     // 후기 조회
    @GetMapping("/gettingReview")
    public List<ReviewResponseDTO> getReviewsByPostId(@PathVariable(name = "eventId") Long eventId) {
        return reviewService.getReviews(eventId);
    }


    //나의 후기
    @GetMapping("/mypage/my-comments")
    public List<Review> getCommentsByCurrentMember() {

        return reviewService.getCommentsByCurrentMember();
    }
/*
    @ResponseStatus(OK)
    @GetMapping("/top3BestReviewIds")
    public List<Integer> findTop3BestCommentIds() {
        return reviewService.findTop3BestCommentIds();
    }

     */
}
