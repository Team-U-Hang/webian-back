package uhang.uhang.commentlike.domain;

import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.comments.domain.Review;
import uhang.uhang.login.domain.Member;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "commentlike")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clikeId;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @OneToOne(fetch = LAZY)
    @JoinColumn(name="comment_id")
    private Review review;
}
