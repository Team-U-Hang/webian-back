package uhang.uhang.login.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;


import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

//    @Test
//    public void joining() throws Exception {
//
//        //Given
//        Member member = new Member();
//        member.setMemberPw("21");
//        member.setMemberEmail("@");
//        member.setStudNum("221");
//        //When
//        int saveId = memberService.join(member);
//        //Then
//        assertEquals(member, memberRepository.findOne(saveId));
//    }

//    @Test
//    public void dupljoin(){
//
//        Member member1 = new Member();
//        member1.setMemberPw("21");
//        member1.setMemberEmail("@");
//        member1.setStudNum("221");
//
//        Member member = new Member();
//        member.setMemberPw("000012");
//        member.setMemberEmail("@11");
//        member.setStudNum("221");
//
//        memberService.join(member);
//        assertThrows(IllegalStateException.class, () -> {
//            memberService.join(member1);
//        });
//
//    }
}