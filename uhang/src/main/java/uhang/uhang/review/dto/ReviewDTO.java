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
@AllArgsConstructor
public class ReviewDTO {
    private Post post;
    private int reviewRate;
    private String commentContent;

    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(
                review.getPost(),
                review.getReviewRate(),
                review.getCommentContent() );
    }
}
