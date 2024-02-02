package uhang.uhang.posting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> getPostsByCategoryIds(List<Integer> eventTypes) {
        return postRepository.findByCategoryIdIn(eventTypes);
    }

    public Page<Post> getPostsByCategoryIds(List<Integer> eventTypes, Pageable pageable) {
        return postRepository.findByCategoryIdIn(eventTypes, pageable);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}