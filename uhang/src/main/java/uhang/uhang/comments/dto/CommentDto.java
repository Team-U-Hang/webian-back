package uhang.uhang.comments.dto;

import lombok.Builder;

public class CommentDto {

    private int commentId;
    private String commentContent;
    private Long memberId;
    private int eventId;

    // 생성자, 게터, 세터 생략

    public CommentDto() {
        // 기본 생성자
    }

    @Builder
    public CommentDto(int commentId, String commentContent, Long memberId, int eventId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.memberId = memberId;
        this.eventId = eventId;
    }

    // 게터, 세터 메서드
    // 생략
}