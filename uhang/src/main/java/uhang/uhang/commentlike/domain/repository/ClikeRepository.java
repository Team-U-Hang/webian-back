package uhang.uhang.commentlike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.login.domain.Member;

import java.util.Optional;

public interface ClikeRepository extends JpaRepository<CommentLike, Integer> {
    public Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);

    public Integer countByComment(Comment comment);
}
