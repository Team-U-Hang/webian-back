package uhang.uhang.comments.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.login.domain.Member;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public Comment findByCommentId(int commentId);
    List<Comment> findByMember(Member member);

}
