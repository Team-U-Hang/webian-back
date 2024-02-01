package uhang.uhang.posting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
}
