package uhang.uhang.upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uhang.uhang.upgrade.dto.MailDto;
import uhang.uhang.upgrade.service.MailService;
@Controller
public class MailController {
    private final MailService emailService;

    public MailController(MailService emailService) {
        this.emailService = emailService;
    }
    @GetMapping("/mail/send")
    public String main() {
        return "SendMail";
    }

/*
    @PostMapping("/upgrade")
    public String execMail(MailDto mailDto, UpgradeDto upgradeDto) {
        mailService.mailSend(mailDto, upgradeDto);
        return "/upgrade"; // 메일 발송시 다시 유저 관리창으로
    }

    */

    @PostMapping("/mail/send")
    public String sendMail(MailDto mailDto) {
        emailService.sendSimpleMessage(mailDto);
        System.out.println("메일 전송 완료");
        return "AfterMail";
    }
}

