package uhang.uhang.comments.dto;

import lombok.Builder;

public class ReviewDto {

    private int commentId;
    private String commentContent;
    private Long memberId;
    private int eventId;

    // 생성자, 게터, 세터 생략

    public ReviewDto() {
        // 기본 생성자
    }

    @Builder
    public ReviewDto(int commentId, String commentContent, Long memberId, int eventId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.memberId = memberId;
        this.eventId = eventId;
    }

    // 게터, 세터 메서드
    // 생략
}