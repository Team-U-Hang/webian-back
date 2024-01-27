package uhang.uhang.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.login.dto.requestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public int join(requestDto dto) {
        Member member = new Member();
        member.setMemberPw((dto.getMemberPw()));
        member.setMemberEmail((dto.getMemberEmail()));
        member.setStudNum((dto.getStudNum()));
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }
    //중복회원 검증 ( 학번으로 검증 )
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findbyStudNum(member.getStudNum());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member findOne(int memberId) {
        return memberRepository.findOne(memberId);
    }
}
