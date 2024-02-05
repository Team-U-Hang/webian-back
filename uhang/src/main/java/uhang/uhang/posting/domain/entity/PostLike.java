package uhang.uhang.posting.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;

@Entity(name = "postlike")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "member_id")//, insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "event_id")//, insertable = false, updatable = false)
    private Post post;
}
