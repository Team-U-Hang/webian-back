package uhang.uhang.review.domain;

import jakarta.persistence.*;
import uhang.uhang.login.domain.Member;

public class CommentLike {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "clike_id")
    private Long interestCategoryId;

    @ManyToOne
    @JoinColumn(name = "member_id")//, insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "comment_id")//, insertable = false, updatable = false)
    private Review review;
}
