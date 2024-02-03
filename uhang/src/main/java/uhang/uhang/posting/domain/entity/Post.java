package uhang.uhang.posting.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "posts")
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventTitle;
    private LocalTime eventTime;
    private LocalDate eventDate;
    private String eventLoc;
    private int eventType;
    private String eventText;
    private URI imageUrl;
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