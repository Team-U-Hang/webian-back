package uhang.uhang.posting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
