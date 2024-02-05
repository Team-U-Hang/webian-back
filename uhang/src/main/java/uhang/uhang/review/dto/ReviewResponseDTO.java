package uhang.uhang.review.dto;

import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long commentId;
    private String commentContent;
    private Integer reviewRate;
}
