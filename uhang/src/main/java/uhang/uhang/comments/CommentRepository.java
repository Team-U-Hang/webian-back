package uhang.uhang.comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public Comment findByCommentId(int commentId);

}
