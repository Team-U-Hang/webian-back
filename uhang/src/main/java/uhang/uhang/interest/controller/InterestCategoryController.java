package uhang.uhang.interest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.interest.service.InterestCategoryService;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.util.SecurityUtil;

import java.util.List;
import uhang.uhang.util.SecurityUtil;
import uhang.uhang.interest.service.InterestCategoryService;


@RestController
@RequestMapping("/interest-category")
public class InterestCategoryController {

    @Autowired
    private InterestCategoryService interestCategoryService;
    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }
    @Autowired
    private MemberRepository memberRepository;



 @PostMapping("/save")
        public ResponseEntity<String> saveInterestCategory( @RequestBody List<Integer> categoryIds) {

     Member member = getCurrentMember();

            try {
                // 사용자의 관심 카테고리 저장
               Long memberId = member.getMemberId();
                interestCategoryService.saveInterestCategory(memberId, categoryIds);
                return new ResponseEntity<>("관심 카테고리가 성공적으로 저장되었습니다.", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("관심 카테고리 저장에 실패했습니다. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}


