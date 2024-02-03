package uhang.uhang.upgrade.dto;

import lombok.*;
import uhang.uhang.upgrade.domain.Upgrade;

import java.net.URI;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpgradeDto {
    private int gId;
    private String gName;
    private String gIntro;
    private URI gImageUrl;

    //private String gImage;
    public Upgrade toEntity(){
        Upgrade build = Upgrade.builder()
                .gId(gId)
                .gName(gName)
                .gIntro(gIntro)
                .gImageUrl(gImageUrl)
                .build();
        return build;
    }

    @Builder
    public UpgradeDto(int gId, String gName, String gIntro, URI gImageUrl) {
        this.gId = gId;
        this.gName = gName;
        this.gIntro = gIntro;
        this.gImageUrl = gImageUrl;
    }

    @Override
    public String toString() {
        return "upgrade{" +
                "gId= '"+ gId + '\'' +
                "gName='" + gName + '\'' +
                ", gIntro='" + gIntro + '\'' +
                ", gImageUr='" + gImageUrl + '\'' +

                '}';
    }
/*
    public Article toEntity() {
        return new Article(null, title, content);
    }
    */





}
