package uhang.uhang.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.comments.domain.exception.LogInRequiredException;
import uhang.uhang.interest.service.InterestCategoryService;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.service.PostService;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;
    private final InterestCategoryService interestCategoryService; // 추가된 부분
    @Autowired

    public PostController(PostService postService, InterestCategoryService interestCategoryService) {
        this.postService = postService;
        this.interestCategoryService = interestCategoryService; // 추가된 부분
    }

    @Autowired
    private MemberRepository memberRepository;



    // 이벤트 게시글 등록
    @PostMapping("posts")
    public Post createPost(@RequestBody Post post) {

        return postService.savePost(post);
    }


    // 이벤트 게시물 전체 조회


    @GetMapping("posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



/*
    @GetMapping("posting")
    public ResponseEntity<Page<Post>> getPostsByEventTypes(
            @RequestParam(required = false) List<Integer> eventType,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

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
*/


    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @GetMapping(value ="/posting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Post>> getPostsByCategoryIds(

            @RequestParam(required = false) List<Integer> eventType,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "6") int size,
            Pageable pageable) {

        Member member = getCurrentMember();
        {



            Page<Post> postsPage;
            if (eventType == null || eventType.isEmpty()) {
                // eventType이 비어있으면 관심분야가 없다고 간주하여 디폴트로 설정된 categoryId를 가져옴
                List<Integer> defaultCategories = interestCategoryService.getDefaultInterestCategories(member.getMemberId());
                postsPage = postService.getPostsByEventTypes(defaultCategories, pageable);
            } else {
                postsPage = postService.getPostsByEventTypes(eventType, pageable);
            }
            return new ResponseEntity<>(postsPage, HttpStatus.OK);
        }
    }

    @GetMapping("/mypage/mylikepost")
    public List<Post> getLikedPostsByCurrentMember() {
        return postService.getLikedPostsByCurrentMember();
    }


}