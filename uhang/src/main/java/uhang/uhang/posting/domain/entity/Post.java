package uhang.uhang.posting.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
//import uhang.uhang.login.domain.Member;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "posts")
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Column(name = "event_time", nullable = false)
    private LocalTime eventTime;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event_loc", nullable = false)
    private String eventLoc;

    @Column(name = "event_type", nullable = false)
    private int eventType;

    @Column(name = "event_text", nullable = false)
    private String eventText;

    @Column(name = "image_url", nullable = false)
    private URI imageUrl;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
    private Integer totalLike;

    // member builder에도 추가해주기
    @Builder
    public Post(Long eventId, String eventTitle, LocalTime eventTime, LocalDate eventDate,
                String eventLoc, int eventType, String eventText, URI imageUrl,
                LocalDateTime timeStamp, Integer totalLike) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.eventLoc = eventLoc;
        this.eventType = eventType;
        this.eventText = eventText;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
//        this.member = member;
        this.totalLike = totalLike;
    }
}
