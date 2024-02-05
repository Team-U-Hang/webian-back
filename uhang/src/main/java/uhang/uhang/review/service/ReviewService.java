package uhang.uhang.review.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.login.service.AuthServiceImpl;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;
import uhang.uhang.review.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    public Member getCurrentMember() {
        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member == null) {
            throw new LogInRequiredException();
        }
        return member;
    }


    public List<Review> getCommentsByCurrentMember() {
        Member currentMember = getCurrentMember();
        return reviewRepository.findByMember(currentMember);
    }
    // 후기 등록
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // 후기 조회
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    //댓글별 좋아요 개수 조회할때, 어떤 댓글인지 알기위해 작성한 메서드
    public Review findById(int commentId) {
        Optional<Review> optionalReview = reviewRepository.findById(commentId);
        return optionalReview.orElse(null);
    }

    /*
    public List<Integer> findTop3BestCommentIds() {
        return reviewRepository.findTop3BestCommentIds();
    }


     */




}