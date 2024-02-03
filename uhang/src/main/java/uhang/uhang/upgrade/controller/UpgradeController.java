package uhang.uhang.upgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.upgrade.domain.Upgrade;
import uhang.uhang.upgrade.domain.repository.UpgradeRepository;
import uhang.uhang.upgrade.dto.MailDto;
import uhang.uhang.upgrade.dto.UpgradeDto;
import uhang.uhang.upgrade.service.MailService;
import uhang.uhang.upgrade.service.UpgradeService;

@RestController
public class UpgradeController {
    private final UpgradeService upgradeService;
    private final MailService emailService;

    @Autowired
    public UpgradeController(UpgradeService upgradeService, MailService emailService) {
        this.upgradeService = upgradeService;
        this.emailService = emailService;
    }

    @PostMapping("/mypage/upgrade-and-send-mail")
    public ResponseEntity<String> createUpgradeAndSendMail(@RequestBody UpgradeDto upgradeDto) {
        // Upgrade 저장
        Upgrade savedUpgrade = upgradeService.saveUpgrade(upgradeDto.toEntity());

        // MailDto 생성 및 메일 전송
        MailDto mailDto = MailDto.builder()
                .address("123forstudy321@gmail.com")
                .title("등업요청합니다")
                .content("[새로운 업그레이드 요청] 단체명: " + savedUpgrade.getGName() + ", 내용: " + savedUpgrade.getGIntro())
                .build();
        emailService.sendMessage(mailDto);

        return ResponseEntity.ok("Upgrade and Mail Sent Successfully");
    }


}
