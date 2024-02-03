package uhang.uhang.postlike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.postlike.domain.PostLike;
import uhang.uhang.postlike.domain.repository.PLikeRepository;
import uhang.uhang.postlike.dto.PLikeDto;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PLikeService {

    private final MemberRepository memberRepository;
    private final PLikeRepository pLikeRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }
    @Transactional
    public boolean postLike(Post post, Member member) {
        member = getCurrentMember();

        Optional<PostLike> existingLike = pLikeRepository.findByMemberAndPost(member,post);

        if (existingLike.isPresent()) {
            // 이미 찜한 경우, 찜을 취소합니다.
            pLikeRepository.delete(existingLike.get());
            return false; // 찜이 취소되었음을 나타냄
        } else {
            // 찜하지 않은 경우, 찜을 추가합니다.
            PostLike newLike = new PostLike();
            newLike.setPost(post);
            newLike.setMember(member);
            pLikeRepository.save(newLike);
            return true; // 찜이 추가되었음을 나타냄
        }
    }

//    @Transactional
//    public void countLike(ClikeDto clikeDto) {
//        Comment comment = commentRepository.findByCommentId(clikeDto.getComment());
//        Optional<CommentLike> countedlike = clikeRepository.countByComment(comment);
//    }

}
