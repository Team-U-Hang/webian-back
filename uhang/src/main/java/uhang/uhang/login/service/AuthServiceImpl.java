package uhang.uhang.login.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import uhang.uhang.Auth.JwtProvider;
import uhang.uhang.Auth.TokenDto;
import uhang.uhang.exception.LogInFailureEmail;
import uhang.uhang.exception.LogInFailurePassword;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.login.dto.loginDto;
import uhang.uhang.login.dto.signDto;


@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @Transactional
    @Override
    public void signUpUser(signDto signDto) {
        Member member = Member.builder()
                .studNum(signDto.getStudNum())
                .memberEmail(signDto.getMemberEmail())
                .memberPw(passwordEncoder.encode(signDto.getMemberPw()))
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public TokenDto loginUser(loginDto dto) {
        String email = dto.getMemberEmail();
        if(!(memberRepository.existsByMemberEmail(email))){
            throw new LogInFailureEmail();
        }
        Member member = memberRepository.findByMemberEmail(dto.getMemberEmail());
        checkPassword(dto.getMemberPw(), member.getMemberPw());


        // user 검증
        Authentication authentication = setAuthentication(dto);
        // token 생성
        String accessToken = jwtProvider.generateAccessToken(authentication);
        User user = (User) authentication.getPrincipal(); // user 정보

        return TokenDto.builder()
                .accessToken(accessToken)
                .build();

    }
    public void checkPassword(String getPassword, String password) {
        if (!(passwordEncoder.matches(getPassword, password))) {
            throw new LogInFailurePassword();
        }
    }

    public Authentication setAuthentication(loginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getMemberEmail(), dto.getMemberPw());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }


}
