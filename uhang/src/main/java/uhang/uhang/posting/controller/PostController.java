package uhang.uhang.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.posting.domain.entity.Post;
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
    @PostMapping("posts")
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }


    // 이벤트 게시물 조회
    /*
    @GetMapping("posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    */


    @GetMapping
    public ResponseEntity<Page<Post>> getPostsByCategoryIds(
            @RequestParam(required = false) List<Integer> categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("eventId").descending());

        Page<Post> postsPage;
        if (categoryId == null || categoryId.isEmpty()) {
            // eventType이 비어있으면 모든 이벤트 유형의 게시글을 가져옴
            postsPage = postService.getAllPosts(pageable);
        } else {
            postsPage = postService.getPostsByCategoryIds(categoryId, pageable);
        }
        return new ResponseEntity<>(postsPage, HttpStatus.OK);
    }

}