
package uhang.uhang.login.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.security.core.userdetails.UserDetails;


@Entity (name="member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(updatable = false, length = 8)
    private String studNum;

    @Column( updatable = false, length = 50)
    private String memberEmail;

    @Column(updatable = false, length = 20)
    private String memberPw;

    @Column(nullable = false)
    @ColumnDefault("'YES'")
    private int writeAuth;


    //yejin
  //  public Member(int memberId) {
    //    this.memberId = memberId;
    //}
    /*
    @OneToMany(mappedBy = "member")
    private List<MemberCategory> memberProducts= new ArrayList<>();;
*/

    public interface MemberDetails extends UserDetails {
        Long getMemberId();
    }
}
