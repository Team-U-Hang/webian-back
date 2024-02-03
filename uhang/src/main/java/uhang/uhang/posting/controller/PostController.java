package uhang.uhang.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.dto.PostRequestDto;
import uhang.uhang.posting.dto.PostResponseDto;
import uhang.uhang.posting.service.PostService;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 이벤트 게시글 등록
    @PostMapping("posting")
    public Long createPost(@RequestBody PostRequestDto postDto) {
        return postService.savePost(postDto);

    }


    // 이벤트 게시물 조회
    @GetMapping("posting/{eventId}")
    public PostResponseDto getPost(@PathVariable(name="eventId") Long eventId) {
        return postService.getPostById(eventId);
    }

}