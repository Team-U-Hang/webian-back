package uhang.uhang.review.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostResponseDto;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;
import uhang.uhang.review.dto.ReviewRequestDTO;
import uhang.uhang.review.dto.ReviewResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Member getCurrentMember() {
        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (member == null) {
            throw new LogInRequiredException();
        }
        return member;
    }


    public List<Review> getCommentsByCurrentMember() {
        Member currentMember = getCurrentMember();
        return reviewRepository.findByMember(currentMember);
    }
    // 후기 등록
    @Transactional
    public Integer reviewSave(Long eventId, ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();
        review.setCommentContent(reviewRequestDTO.getCommentContent());
        review.setReviewRate(reviewRequestDTO.getReviewRate());

        // 외래 키에 해당하는 Post와 Member를 조회하여 설정
        Post post = postRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트를 찾을 수 없습니다: " + reviewRequestDTO.getEventId()));
        review.setPost(post);

        Member member = getCurrentMember();
        memberRepository.findById(member.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다: " + reviewRequestDTO.getMemberId()));
        review.setMember(member);

        // Review 엔티티를 저장하고 ID를 반환
        reviewRepository.save(review);
        return review.getCommentId();
    }


    // 후기 조회
    public List<ReviewResponseDTO> getReviews(Long eventId) {
        // 포스트 넘버 *인 후기 리스트로 조회
        List<Review> reviews = reviewRepository.findByPostEventId(eventId);
        return reviews.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}

    private ReviewResponseDTO convertToDTO(Review review) {
        return ReviewResponseDTO.builder()
                .commentId(review.getCommentId())
                .commentContent(review.getCommentContent())
                .reviewRate(review.getReviewRate())
                .build();
    }
    //댓글별 좋아요 개수 조회할때, 어떤 댓글인지 알기위해 작성한 메서드
    public Review findById(Integer commentId) {
        Optional<Review> optionalReview = reviewRepository.findById(commentId);
        return optionalReview.orElse(null);
    }
    /*
    public List<Integer> findTop3BestCommentIds() {
        return reviewRepository.findTop3BestCommentIds();
    }


     */




}