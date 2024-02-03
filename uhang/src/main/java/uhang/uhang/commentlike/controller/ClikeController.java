package uhang.uhang.commentlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.commentlike.service.ClikeService;
import uhang.uhang.response.Response;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.service.ReviewService;

import static org.springframework.http.HttpStatus.OK;
import static uhang.uhang.response.Message.*;
import static uhang.uhang.response.Response.success;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ClikeController {
    private final ClikeService clikeService;
    private final ReviewService reviewService;

    @PostMapping("/reviewlike")
    public Response clikeinsert(@RequestBody ClikeDto clikeDto) {
        clikeService.clikeinsert(clikeDto);
        return success(COMMENT_LIKE_SUCCESS);
    }

//    @GetMapping("/commentlike/show") get 요청 보내기 comment 보여주는 get 요청에 총 좋아요 개수 합쳐주세욥!!
    @ResponseStatus(OK)
    @GetMapping("/countByReview/{commentId}")
    public Integer getCountByReview(@PathVariable int commentId) {
        // Review 객체를 얻어옴
        Review review = reviewService.findById(commentId);

        // countByReview 메서드 호출
        return clikeService.getCountByReview(review);
}
}