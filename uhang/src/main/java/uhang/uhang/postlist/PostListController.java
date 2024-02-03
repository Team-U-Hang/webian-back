package uhang.uhang.postlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.comments.domain.exception.LogInRequiredException;
import uhang.uhang.comments.service.CommentService;
import uhang.uhang.interest.service.InterestCategoryService;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//멤버의 관심분야 + 이벤트 목록
@RestController
@RequestMapping("/post-list")
public class PostListController {


    private final PostService postService;
    private final InterestCategoryService interestCategoryService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public PostListController(
            PostService postService,
            InterestCategoryService interestCategoryService
    ) {

        this.postService = postService;
        this.interestCategoryService = interestCategoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<Map<String, Object>> getPosts() {
        Member currentMember = getCurrentMember();


        List<Integer> interestCategories = interestCategoryService.getDefaultInterestCategories(currentMember.getMemberId());

        List<Post> posts = postService.getAllPosts();


        Map<String, Object> responseData = new HashMap<>();
        responseData.put("interestCategories", interestCategories);
        responseData.put("AllPostList", posts);







        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }
}
