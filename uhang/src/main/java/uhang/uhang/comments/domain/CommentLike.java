package uhang.uhang.comments.domain;

import jakarta.persistence.*;
import uhang.uhang.comments.domain.Comment;
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
    private Comment comment;
}
