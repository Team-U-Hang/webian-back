package uhang.uhang.posting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostLike;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    public List<PostLike> findByMember(Member member);

    public Integer countByPost(Post post);
}