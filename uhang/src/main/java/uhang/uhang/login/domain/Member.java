
package uhang.uhang.login.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity (name="member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
