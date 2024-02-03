package uhang.uhang.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequestDTO {
    private int commentId;
    private String commentContent;
    private Post post;
    private Integer reviewRate;
    private Member member;

    public Review toEntity() {
    Review reviews = Review.builder()
            .commentId(commentId)
            .commentContent(commentContent)
            .post(post)
            .reviewRate(reviewRate)
            .member(member)
            .build();

    return reviews;
    }
}


