package uhang.uhang.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String eventTitle;
    private LocalTime eventTime;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate eventDate;
    private String eventLoc;
//    private PostCategory eventType;
    private String eventText;
    private Long memberId;
    private LocalDateTime timeStamp;

    public Post toEntity() {
        return Post.builder()
                .eventTitle(eventTitle)
                .eventTime(eventTime)
                .eventDate(eventDate)
                .eventLoc(eventLoc)
//                .eventType(eventType)
                .eventText(eventText)
                .timeStamp(timeStamp)
                .build();
    }
}

