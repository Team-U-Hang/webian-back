package uhang.uhang.posting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;

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
}