package uhang.uhang.postlike.domain;

import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "postlike")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pLikeId;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @OneToOne(fetch = LAZY)
    @JoinColumn(name="event_id")
    private Post post;
}
