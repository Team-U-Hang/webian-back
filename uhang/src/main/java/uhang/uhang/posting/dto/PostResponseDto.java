package uhang.uhang.posting.dto;

import lombok.Builder;
import lombok.Getter;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
public class PostResponseDto {

    private final Long eventId;
    private final String eventTitle;
    private final LocalTime eventTime;
    private final LocalDate eventDate;
    private final String eventLoc;
    private final int eventType;
    private final String eventText;
    private final URI imageUrl;
    private final LocalDateTime timeStamp;
    // private Member member;
    private final Integer totalLike;

//    @Builder
//    public PostResponseDto(Post post) {
//        this.eventId = post.getEventId();
//        this.eventTitle = post.getEventTitle();
//        this.eventTime = post.getEventTime();
//        this.eventDate = post.getEventDate();
//        this.eventLoc = post.getEventLoc();
//        this.eventType = post.getEventType();
//        this.eventText = post.getEventText();
//        this.imageUrl = post.getImageUrl();
//        this.timeStamp = post.getTimeStamp();
//        // this.member = member;
//        this.totalLike = post.getTotalLike();
//    }

}

