package uhang.uhang.review.dto;

import lombok.*;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;

@Getter
@NoArgsConstructor
public class ReviewRequestDTO {
    private Long eventId;
    private Long memberId;
    private String commentContent;
    private Integer reviewRate;

}


