package uhang.uhang.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.interest.service.InterestCategoryService;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.dto.HeartDto;
import uhang.uhang.posting.dto.PostRequestDto;
import uhang.uhang.posting.dto.PostResponseDto;
import uhang.uhang.posting.service.HeartPostService;
import uhang.uhang.posting.service.PostService;
import uhang.uhang.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uhang.uhang.response.Message.HEART_POST_SUCCESS;
import static uhang.uhang.response.Response.success;

@RestController
public class PostController {

    private final PostService postService;
    private final InterestCategoryService interestCategoryService; // 추가된 부분
    private final HeartPostService heartPostService;

    @Autowired
    public PostController(PostService postService, InterestCategoryService interestCategoryService, HeartPostService heartPostService) {
        this.postService = postService;
        this.interestCategoryService = interestCategoryService; // 추가된 부분
        this.heartPostService=heartPostService;
    }

    @Autowired
    private MemberRepository memberRepository;


    // 이벤트 게시글 등록
    @PostMapping("posting")
    public Long createPost(@RequestBody PostRequestDto postDto,
                           @RequestParam(value = "categories")List<Long> categories) {
        return postService.savePost(postDto, categories);
    }

    // 이벤트 게시물 상세정보 조회
    @GetMapping("post/{eventId}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable(name="eventId") Long eventId) {
        List<Long> postCategories = postService.getPostCategories(eventId);

        PostResponseDto post = postService.getPostById(eventId);


        Map<String, Object> responseData = new HashMap<>();
        responseData.put("postCategories", postCategories);
        responseData.put("postDetail", post);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    //하고 싶은 이벤트 하트하기
    @PostMapping("/heart/post")
    public Response heartpost(@RequestBody HeartDto heartDto) {
        heartPostService.heartpost(heartDto);
        return success(HEART_POST_SUCCESS);
    }


    @GetMapping(value ="/customposting", produces = MediaType.APPLICATION_JSON_VALUE)
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