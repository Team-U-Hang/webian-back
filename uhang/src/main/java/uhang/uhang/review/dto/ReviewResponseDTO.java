package uhang.uhang.review.dto;

import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

public class ReviewResponseDTO {

    private int commentId;
    private String commentContent;
    private Post post;
    private Integer reviewRate;
    private Member member;

    public ReviewResponseDTO(Review review) {
        this.commentId = review.getCommentId();
        this.commentContent = review.getCommentContent();
        this.post = review.getPost();
        this.reviewRate = review.getReviewRate();
        this.member = review.getMember();

    }
}
