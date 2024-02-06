package uhang.uhang.posting.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.review.domain.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uhang.uhang.login.domain.Member;
//import uhang.uhang.login.domain.Member;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventTitle;
    private LocalTime eventTime;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate eventDate;
    private String eventLoc;

    private Integer eventType;
    private String eventText;
    private URI imageUrl;
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private Integer totalLike;

//    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("commentId asc")
//    private List<Review> reviews;

    // member builder에도 추가해주기

//    @Builder
//    public Post(Long eventId, String eventTitle, LocalTime eventTime, LocalDate eventDate,
//                String eventLoc, int eventType, String eventText, URI imageUrl,
//                LocalDateTime timeStamp, Integer totalLike, Member member, List<Review> reviews) {
//        this.eventId = eventId;
//        this.eventTitle = eventTitle;
//        this.eventTime = eventTime;
//        this.eventDate = eventDate;
//        this.eventLoc = eventLoc;
//        this.eventType = eventType;
//        this.eventText = eventText;
//        this.imageUrl = imageUrl;
//        this.timeStamp = timeStamp;
////        this.member = member;
//        this.totalLike = totalLike;

    @Builder
    public Post(Long eventId, String eventTitle, LocalTime eventTime, LocalDate eventDate,
                String eventLoc, Integer eventType, String eventText, URI imageUrl,
                LocalDateTime timeStamp, Member member, Integer totalLike) {
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
//        this.reviews = reviews;
//    }
    }
}