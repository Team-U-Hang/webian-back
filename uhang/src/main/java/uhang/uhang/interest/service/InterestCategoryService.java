package uhang.uhang.interest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.interest.domain.Category;
import uhang.uhang.interest.domain.InterestCategory;
import uhang.uhang.interest.domain.repository.CategoryRepository;
import uhang.uhang.interest.domain.repository.InterestCategoryRepository;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterestCategoryService {

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InterestCategoryRepository interestCategoryRepository;




    @Transactional
    public void saveInterestCategory(Long memberId,List<Integer> categoryIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Member member = memberRepository.findByMemberEmail(userEmail);
        interestCategoryRepository.deleteByMember(member);

        for (int categoryId : categoryIds) {
            Category category = categoryRepository.findById((long) categoryId)
                    .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

            InterestCategory interestCategory = new InterestCategory();
            interestCategory.setMember(member);  // member 필드를 통해 member_id가 자동으로 설정됩니다.
            interestCategory.setCategory(category);

            interestCategoryRepository.save(interestCategory);
        }
    }
        public List<Integer> getDefaultInterestCategories(Long memberId){
            List<InterestCategory> userInterestCategories = interestCategoryRepository.findByMember_MemberId(memberId);

            if (userInterestCategories.isEmpty()) {
                // 사용자의 관심 카테고리가 없을 경우 디폴트로 지정한 카테고리 ID 목록 반환
                return Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

            } else {
                // 사용자의 관심 카테고리가 있을 경우 해당 카테고리 ID 목록 반환  //!
                return userInterestCategories.stream()
                        .map(category -> category.getCategory().getCategoryId().intValue())
                        .collect(Collectors.toList());
            }
        }





}