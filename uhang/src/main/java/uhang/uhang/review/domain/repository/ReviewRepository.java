package uhang.uhang.review.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uhang.uhang.login.domain.Member;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public Review findByCommentId(Long commentId);

    List<Review> findByPostEventId(Long eventId);
    List<Review> findByMember(Member member);

    //comments!!
    /*
    @Query("SELECT r.commentId FROM comments r LEFT JOIN r.commentLikes cl GROUP BY r.commentId ORDER BY COUNT(cl) DESC")
    List<Integer> findTop3BestCommentIds();

     */

//    List<Review> findByMember(Member member);
}
