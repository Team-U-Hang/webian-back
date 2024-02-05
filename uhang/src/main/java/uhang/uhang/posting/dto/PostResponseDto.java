package uhang.uhang.posting.dto;

import lombok.Builder;
import lombok.Getter;
import uhang.uhang.login.domain.Member;
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

}

