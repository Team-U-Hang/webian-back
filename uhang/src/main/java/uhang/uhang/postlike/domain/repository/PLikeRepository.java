package uhang.uhang.postlike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.postlike.domain.PostLike;
import uhang.uhang.login.domain.Member;

import java.util.List;
import java.util.Optional;

public interface PLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndPost(Member member, Post post);

}
