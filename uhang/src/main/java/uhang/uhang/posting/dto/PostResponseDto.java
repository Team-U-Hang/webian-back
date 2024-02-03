package uhang.uhang.posting.dto;

import lombok.Builder;
import lombok.Getter;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.dto.ReviewResponseDTO;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<ReviewResponseDTO> reviews;

    @Builder
    public PostResponseDto(Post post) {
        this.eventId = post.getEventId();
        this.eventTitle = post.getEventTitle();
        this.eventTime = post.getEventTime();
        this.eventDate = post.getEventDate();
        this.eventLoc = post.getEventLoc();
        this.eventType = post.getEventType();
        this.eventText = post.getEventText();
        this.imageUrl = post.getImageUrl();
        this.timeStamp = post.getTimeStamp();
        // this.member = member;
        this.totalLike = post.getTotalLike();
        this.reviews = post.getReviews().stream().map(ReviewResponseDTO::new).collect(Collectors.toList());
    }

}

