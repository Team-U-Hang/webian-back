package uhang.uhang.posting.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByEventTypeIn(List<Integer> eventTypes);
    Page<Post> findByEventTypeIn(List<Integer> eventTypes, Pageable pageable);

}
