package uhang.uhang.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.review.service.ReviewService;
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


    @Autowired
    private   ReviewService reviewService;
    @Autowired
    private   PostService postService;
    @Autowired
    private   InterestCategoryService interestCategoryService;
    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    public MyPageController(MemberRepository memberRepository, ReviewService reviewService, PostService postService, InterestCategoryService interestCategoryService) {
        this.memberRepository = memberRepository;
        this.reviewService = reviewService;
        this.postService = postService;
        this.interestCategoryService = interestCategoryService;
    }



    @GetMapping("/my-data")
    public ResponseEntity<Map<String, Object>> getMyData() {
        Member currentMember = getCurrentMember();

        List<Review> myReviews = reviewService.getCommentsByCurrentMember();
        List<Post> likedPosts = postService.getLikedPostsByCurrentMember();
        List<Integer> interestCategories = interestCategoryService.getDefaultInterestCategories(currentMember.getMemberId());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("currentMember", currentMember.getMemberEmail());
        responseData.put("interestCategories", interestCategories);
        responseData.put("likedPosts", likedPosts);
        responseData.put("myComments", myReviews);



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
