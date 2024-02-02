package uhang.uhang.posting.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryIdIn(List<Integer> eventTypes);
    Page<Post> findByCategoryIdIn(List<Integer> eventTypes, Pageable pageable);
}
