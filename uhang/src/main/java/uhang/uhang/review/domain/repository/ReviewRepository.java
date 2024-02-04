package uhang.uhang.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uhang.uhang.login.domain.Member;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public Review findByCommentId(long commentId);
    List<Review> findByMember(Member member);

//    List<Review> findByMember(Member member);
}
