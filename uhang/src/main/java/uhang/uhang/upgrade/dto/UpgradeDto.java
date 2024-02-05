package uhang.uhang.upgrade.dto;

import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.upgrade.domain.Upgrade;

import java.net.URI;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpgradeDto {
    private int gId;
    private Long memberId;
    private String gName;
    private String gIntro;
    private URI gImageUrl;


    //private String gImage;

    @Builder
    public Upgrade toEntity() {
        Upgrade build = Upgrade.builder()
                .gId(gId)
                .member(Member.builder().memberId(memberId).build()) // Member 엔티티를 직접 넣어줍니다.
                .gName(gName)
                .gIntro(gIntro)
                .gImageUrl(gImageUrl)
                .build();
        return build;
    }

    @Builder
    public UpgradeDto(int gId, Long memberId, String gName, String gIntro, URI gImageUrl) { // int에서 Long으로 변경
        this.gId = gId;
        this.memberId = memberId;
        this.gName = gName;
        this.gIntro = gIntro;
        this.gImageUrl = gImageUrl;
    }

    @Override
    public String toString() {
        return "upgrade{" +
                "gId= '" + gId + '\'' +
                "gName='" + gName + '\'' +
                ", gIntro='" + gIntro + '\'' +
                ", gImageUrl='" + gImageUrl + '\'' +
                '}';
    }
}
