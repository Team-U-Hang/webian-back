package uhang.uhang.bestpost;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.service.HeartPostService;
import uhang.uhang.posting.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/best")
public class BestPostController {

    private final HeartPostService heartPostService;

    @GetMapping("/top3/posts")
    public ResponseEntity<List<Post>> getTopThreePosts() {
        List<Post>  topThreePosts = heartPostService.findTopThreePosts();
        return ResponseEntity.ok(topThreePosts);
    }
}
