package uhang.uhang.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import uhang.uhang.login.domain.Member;

public class SecurityUtil {

    private SecurityUtil() { }

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }

        // Principal 객체에서 memberId 추출
        if (authentication.getPrincipal() instanceof Member.MemberDetails) {
            Member.MemberDetails memberDetails = (Member.MemberDetails) authentication.getPrincipal();
            return memberDetails.getMemberId();
        }

        throw new RuntimeException("인증 정보에서 memberId를 추출할 수 없습니다.");
    }

}