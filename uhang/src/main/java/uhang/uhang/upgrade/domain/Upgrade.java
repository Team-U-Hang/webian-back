package uhang.uhang.upgrade.domain;


import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;

import java.net.URI;



@NoArgsConstructor
@Data
@Entity
@Getter@Setter
@Table(name="upgrade")
public class Upgrade {

    @Id
    @Column(name = "gid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gId;

    @Column(name = "gname")
    private String gName;

    /*
    @OneToOne
    @JoinColumn(name = "write_auth", referencedColumnName = "write_auth", unique = true)
    private Member members;
*/
    @Column(name = "g_intro")
    private String gIntro;

    @Column(name = "g_image")
    private URI gImageUrl;

    @Builder
    public Upgrade(int gId, String gName, String gIntro, URI gImageUrl) {
        this.gId=gId;
        this.gName=gName;
        this.gIntro=gIntro;
        this.gImageUrl = gImageUrl;


    }




}
