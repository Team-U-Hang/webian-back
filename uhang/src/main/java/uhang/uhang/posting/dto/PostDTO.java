package uhang.uhang.posting.dto;

import lombok.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long eventId;
    private String eventTitle;
    private LocalTime eventTime;
    private LocalDate eventDate;
    private String eventLoc;
    private int eventType;
    private String eventText;
    private URI imageUrl;
    private LocalDateTime timeStamp;
    private Integer totalLike;

}
