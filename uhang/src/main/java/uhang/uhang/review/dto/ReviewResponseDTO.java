package uhang.uhang.review.dto;

import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

@Getter
@Builder
public class ReviewResponseDTO {

    private Integer commentId;
    private String commentContent;
    private int reviewRate;
}
