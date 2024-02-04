package uhang.uhang.comments.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.login.domain.Member;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    public Review findByCommentId(int commentId);
    List<Review> findByMember(Member member);
    /*

    public  Member findByMemberEmail(Member member);
*/
}
