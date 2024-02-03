package uhang.uhang.upgrade.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uhang.uhang.upgrade.dto.MailDto;
import uhang.uhang.upgrade.dto.UpgradeDto;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender emailSender;

    public void sendMessage(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("123forstudy321@gmail.com");
        message.setTo("123forstudy321@gmail.com");
        message.setSubject(mailDto.getTitle());

        message.setText(mailDto.getContent());
        emailSender.send(message);
    }

}