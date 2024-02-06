package uhang.uhang.posting.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByEventTypeIn(List<Integer> eventTypes);
    Page<Post> findByEventTypeIn(List<Integer> eventTypes, Pageable pageable);

    public Post findByEventId(long eventId);
//    List<Review> findByMember(Member member);

    //bestpost 를 위해 추가된 부분
    List<Post> findByEventIdIn(List<Long> eventIds);
    // 여기까지 추가된 부분
}
