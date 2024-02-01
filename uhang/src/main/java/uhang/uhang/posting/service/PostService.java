package uhang.uhang.posting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostDTO;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 이벤트 게시물 등록
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 이벤트 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 상세 정보 조회
    public PostDTO getPostDTOById(Long eventId) {
        Post post = postRepository.findById(eventId).orElse(new Post());
        return convertToDTO(post);
    }

    private PostDTO convertToDTO(Post post) {
        return PostDTO.builder()
                .eventId(post.getEventId())
                .eventTitle(post.getEventTitle())
                .eventTime(post.getEventTime())
                .eventDate(post.getEventDate())
                .eventLoc(post.getEventLoc())
                .eventType(post.getEventType())
                .eventText(post.getEventText())
                .imageUrl(post.getImageUrl())
                .timeStamp(post.getTimeStamp())
                .totalLike(post.getTotalLike())
                .build();
    }
}