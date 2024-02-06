package uhang.uhang.posting.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import uhang.uhang.posting.domain.entity.PostCategory;
import uhang.uhang.review.dto.ReviewResponseDTO;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class PostResponseDto {

    private final Long eventId;
    private final String eventTitle;
    private final LocalTime eventTime;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private final LocalDate eventDate;
    private final String eventLoc;
    private final PostCategory eventType;
    private final String eventText;
    private final LocalDateTime timeStamp;
    // private Member member;
    private final Integer totalLike;
    private List<ReviewResponseDTO> reviews;

}

