package uhang.uhang.postlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.postlike.dto.PLikeDto;
import uhang.uhang.postlike.service.PLikeService;
import uhang.uhang.response.Response;

import static uhang.uhang.response.Message.POST_LIKE_SUCCESS;
import static uhang.uhang.response.Response.success;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PLikeController {
    private final PLikeService pLikeService;
    MemberRepository memberRepository;
    PostRepository postRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> postLike(@PathVariable Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        Member member = getCurrentMember();
        boolean liked = pLikeService.postLike(post, member);

        if (liked) {
            return ResponseEntity.ok("Post liked successfully!");
        } else {
            return ResponseEntity.ok("Post like canceled successfully!");
        }
    }

}
