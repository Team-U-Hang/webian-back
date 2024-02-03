package uhang.uhang.review.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.review.domain.entity.Review;
import uhang.uhang.review.domain.repository.ReviewRepository;
import uhang.uhang.review.dto.ReviewRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, PostRepository postRepository) {
        this.reviewRepository = reviewRepository;
        this.postRepository = postRepository;
    }

    // 후기 등록
    @Transactional
    public Long reviewSave(String nickname, Long id, ReviewRequestDTO dto) {
        User user = userRepository.findByNickname(nickname);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        dto.setUser(user);
        dto.setPost(post);

        Review review = dto.toEntity();
        reviewRepository.save(review);

        return dto.getPost().getEventId();
    }
//    public Review saveReview(Review review) {
//        return reviewRepository.save(review);
//    }
//
//    // 후기 조회
//    public List<Review> getAllReviews() {
//        return reviewRepository.findAll();
//    }

    //댓글별 좋아요 개수 조회할때, 어떤 댓글인지 알기위해 작성한 메서드
    public Review findById(int commentId) {
        Optional<Review> optionalReview = reviewRepository.findById(commentId);
        return optionalReview.orElse(null);
    }
}