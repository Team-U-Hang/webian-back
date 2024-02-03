package uhang.uhang.upgrade.dto;

import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class MailDto {
    private String address = "123forstudy321@gmail.com";
    private String title = "등업요청합니다";
    private String content;

}
