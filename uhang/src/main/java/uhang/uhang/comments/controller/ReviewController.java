package uhang.uhang.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.comments.domain.Review;
import uhang.uhang.comments.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/mypage")
public class ReviewController {



    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/my-comments")
    public List<Review> getCommentsByCurrentMember() {

        return reviewService.getCommentsByCurrentMember();
    }
}