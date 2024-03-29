package uhang.uhang.review.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @ManyToOne
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
