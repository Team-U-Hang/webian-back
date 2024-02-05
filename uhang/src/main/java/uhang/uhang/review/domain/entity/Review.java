package uhang.uhang.review.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "comments")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="event_id")
    private Post post;

    @Column
    private String commentContent;

    @Column
    private Integer reviewRate;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;





}
