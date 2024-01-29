package uhang.uhang.upgrade.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    private String address="yejin0605@sookmyung.ac.kr";
    private String title ="등업요청합니다";
    private String content;
  //  private MultipartFile file;
}