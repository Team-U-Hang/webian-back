package uhang.uhang.comments.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

@Entity(name = "comments")
@Data
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "event_id")//, insertable = false, updatable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")//, insertable = false, updatable = false)
    private Member member;





    private String commentContent;


}
