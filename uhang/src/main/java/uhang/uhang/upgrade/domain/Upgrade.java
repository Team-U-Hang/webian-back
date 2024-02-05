package uhang.uhang.upgrade.domain;


import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;

import java.net.URI;

import static jakarta.persistence.FetchType.LAZY;


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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

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
    public Upgrade(int gId, Member member, String gName, String gIntro, URI gImageUrl) {
        this.gId = gId;
        this.member = member;
        this.gName = gName;
        this.gIntro = gIntro;
        this.gImageUrl = gImageUrl;
    }




}
