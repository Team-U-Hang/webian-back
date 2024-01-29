package uhang.uhang.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.login.dto.loginDto;
import uhang.uhang.login.dto.signDto;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void signUpUser(signDto signDto) {
        if (memberRepository.findOneWithAuthoritiesByUsername(signDto.getMemberEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Member member = Member.builder()
                .studNum(signDto.getStudNum())
                .memberEmail(signDto.getMemberEmail())
                .memberPw(signDto.getMemberPw())
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member loginUser(loginDto loginDto) {
        Member member = memberRepository.findByMemberEmail(loginDto.getMemberEmail());
        if (member == null) throw new Exception("회원이 조회되지 않음");

        if (!member.getMemberPw().equals(loginDto.getMemberPw())) {
            throw new Exception("비밀번호가 틀립니다.");
        }

        return member;
    }
}
