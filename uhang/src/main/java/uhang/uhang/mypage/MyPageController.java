package uhang.uhang.mypage;

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

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    private final CommentService commentService;
    private final PostService postService;
    private final InterestCategoryService interestCategoryService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public MyPageController(
            CommentService commentService,
            PostService postService,
            InterestCategoryService interestCategoryService
    ) {
        this.commentService = commentService;
        this.postService = postService;
        this.interestCategoryService = interestCategoryService;
    }

    @GetMapping("/my-data")
    public ResponseEntity<Map<String, Object>> getMyData() {
        Member currentMember = getCurrentMember();

        List<Comment> myComments = commentService.getCommentsByCurrentMember();
        List<Post> likedPosts = postService.getLikedPostsByCurrentMember();
        List<Integer> interestCategories = interestCategoryService.getDefaultInterestCategories(currentMember.getMemberId());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("currentMember", currentMember.getMemberEmail());
        responseData.put("interestCategories", interestCategories);
        responseData.put("likedPosts", likedPosts);
        responseData.put("myComments", myComments);



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
