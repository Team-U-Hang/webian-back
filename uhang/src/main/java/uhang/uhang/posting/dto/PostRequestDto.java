package uhang.uhang.posting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String eventTitle;
    private LocalTime eventTime;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate eventDate;
    private String eventLoc;
    private int eventType;
    private String eventText;
    private URI imageUrl;
    private Long memberId;
    private LocalDateTime timeStamp;

    public Post toEntity() {
        return Post.builder()
                .eventTitle(eventTitle)
                .eventTime(eventTime)
                .eventDate(eventDate)
                .eventLoc(eventLoc)
                .eventType(eventType)
                .eventText(eventText)
                .imageUrl(imageUrl)
                .timeStamp(timeStamp)
                .build();
    }
}
