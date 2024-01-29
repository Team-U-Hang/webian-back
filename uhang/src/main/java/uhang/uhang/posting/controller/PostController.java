package uhang.uhang.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}