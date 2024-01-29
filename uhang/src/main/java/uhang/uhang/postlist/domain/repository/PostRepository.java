package uhang.uhang.postlist.domain.repository;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.postlist.domain.Post;
import uhang.uhang.postlist.dto.ListDto;
import uhang.uhang.postlist.dto.PostDto;

public interface PostRepository extends JpaRepository<Post, Long> {
}
