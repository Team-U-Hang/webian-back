package uhang.uhang.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class requestDto {
    private String memberEmail;
    private String memberPw;
    private String studNum;
    @Builder
    public requestDto(String memberEmail, String memberPw, String studNum)
    {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.studNum = studNum;
    }


}
