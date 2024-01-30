package uhang.uhang.posting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.dto.PostDto;
import uhang.uhang.posting.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getPostsByEventTypes(
            @RequestParam(required = false) List<Integer> eventType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("eventId").descending());

        Page<Post> postsPage;
        if (eventType == null || eventType.isEmpty()) {
            // eventType이 비어있으면 모든 이벤트 유형의 게시글을 가져옴
            postsPage = postService.getAllPosts(pageable);
        } else {
            postsPage = postService.getPostsByEventTypes(eventType, pageable);
        }
        return new ResponseEntity<>(postsPage, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.savePost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }




}


