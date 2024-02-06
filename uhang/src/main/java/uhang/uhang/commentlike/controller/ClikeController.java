package uhang.uhang.commentlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.commentlike.service.ClikeService;
import uhang.uhang.response.Response;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

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

    //    @GetMapping("/commentlike/show") get 요청 보내기
    @ResponseStatus(OK)
    @GetMapping("/countByReview/{commentId}")
    public Integer getCountByReview(@PathVariable Integer commentId) {
        // Review 객체를 얻어옴
        Review review = reviewService.findById(commentId);

        // countByReview 메서드 호출
        return clikeService.getCountByReview(review);
    }

    // 좋아요 개수 기준으로 상위 3개 댓글 ID 조회 API
    @GetMapping("/top3")
    public ResponseEntity<List<Review>> getTop3CommentsByLikeCount() {
        // 가져온 댓글 ID 리스트
        List<Integer> top3CommentIds = clikeService.findTop3CommentIdsByLikeCount();

        // 댓글 내용을 담을 리스트
        List<Review> top3Comments = new ArrayList<>();

        // 댓글 ID를 기반으로 각 댓글을 가져와 리스트에 추가
        for (Integer commentId : top3CommentIds) {
            Review comment = reviewService.findById(commentId);
            top3Comments.add(comment);
        }

        // top3Comments 리스트에는 좋아요 개수를 기준으로 내림차순으로 상위 3개의 댓글이 담겨있음
        return new ResponseEntity<>(top3Comments, HttpStatus.OK);
    }
}