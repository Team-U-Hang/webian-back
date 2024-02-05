package uhang.uhang.review.dto;

import lombok.*;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private Post post;
    private int reviewRate;
    private String commentContent;
    private Long commentId;
    private Long memberId;
    private int eventId;

    // 생성자, 게터, 세터 생략

    @Builder
    public ReviewDTO(Long commentId, String commentContent, Long memberId, int eventId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.memberId = memberId;
        this.eventId = eventId;
    }


}
