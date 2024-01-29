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

    public void sendSimpleMessage(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yejin0605@sookmyung.ac.kr");
        message.setTo("yejin0605@sookmyung.ac.kr");
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        emailSender.send(message);
    }





    /*
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "yejins0605@gmail.com";

    public void mailSend(MailDto mailDto, UpgradeDto upgradeDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText("단체이름: " +upgradeDto.getGName() + mailDto.getMessage() );

        mailSender.send(message);
    }

     */
}