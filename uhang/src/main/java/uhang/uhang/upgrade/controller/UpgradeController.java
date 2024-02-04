package uhang.uhang.upgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.upgrade.domain.Upgrade;

import uhang.uhang.upgrade.dto.MailDto;
import uhang.uhang.upgrade.dto.UpgradeDto;
import uhang.uhang.upgrade.service.MailService;
import uhang.uhang.upgrade.service.UpgradeService;

@RestController
public class UpgradeController {
    private final UpgradeService upgradeService;
    private final MailService emailService;

    private MemberRepository memberRepository;

    @Autowired
    public UpgradeController(UpgradeService upgradeService, MailService emailService, MemberRepository memberRepository) {
        this.upgradeService = upgradeService;
        this.emailService = emailService;
        this.memberRepository = memberRepository; // 추가된 부분
    }
    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }


    @PostMapping("/mypage/upgrade-and-send-mail")
    public ResponseEntity<String> createUpgradeAndSendMail(@RequestBody UpgradeDto upgradeDto) {
        // Upgrade 저장

        Member currentMember = getCurrentMember();
        upgradeDto.setMemberId(currentMember.getMemberId());

        Upgrade savedUpgrade = upgradeService.saveUpgrade(upgradeDto);

        if (savedUpgrade == null) {
            // Upgrade 저장에 실패할 경우 예외처리 혹은 응답 처리
            return ResponseEntity.badRequest().body("Failed to save upgrade");
        }

        // MailDto 생성 및 메일 전송
        MailDto mailDto = MailDto.builder()
                .address("123forstudy321@gmail.com")
                .title("[등업요청] 등업요청합니다")
                .content("[새로운 업그레이드 요청]\nmemberId:" +savedUpgrade.getMember().getMemberId() +"\n단체명: " + savedUpgrade.getGName() + "\n내용: " + savedUpgrade.getGIntro())
                .build();
        emailService.sendMessage(mailDto);

        return ResponseEntity.ok("Upgrade and Mail Sent Successfully");
    }


}
