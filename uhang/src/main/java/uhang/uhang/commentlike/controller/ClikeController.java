package uhang.uhang.commentlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.commentlike.domain.repository.ClikeRepository;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.commentlike.service.ClikeService;
import uhang.uhang.response.Response;

import static uhang.uhang.response.Message.*;
import static uhang.uhang.response.Response.success;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class ClikeController {
    private final ClikeService clikeService;

    @PostMapping("/commentlike")
    public Response clikeinsert(@RequestBody ClikeDto clikeDto) {
        clikeService.clikeinsert(clikeDto);
        return success(COMMENT_LIKE_SUCCESS);
    }

//    @GetMapping("/commentlike/show") comment 보여주는 get 요청에 총 좋아요 개수 합쳐주세욥!!




}
