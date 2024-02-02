package uhang.uhang.commentlike.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.commentlike.domain.repository.ClikeRepository;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.comments.domain.CommentRepository;
import uhang.uhang.comments.domain.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClikeService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final ClikeRepository clikeRepository;

    public Member getCurrentMember() {

        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member==null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    @Transactional
    public void clikeinsert(ClikeDto clikeDto){

        Member member = getCurrentMember();
        Comment comment = commentRepository.findByCommentId(clikeDto.getComment());


        // 이미 좋아요되어있으면 제거하고, 좋아요 없으면 만들기
        Optional<CommentLike> findLike = clikeRepository.findByMemberAndComment(member, comment);
        if(findLike.isEmpty()){

            CommentLike commentLike = CommentLike.builder()
                    .member(member)
                    .comment(comment)
                    .build();
            clikeRepository.save(commentLike);
        } else clikeRepository.delete(findLike.get());
    }

//    @Transactional
//    public void countLike(ClikeDto clikeDto) {
//        Comment comment = commentRepository.findByCommentId(clikeDto.getComment());
//        Optional<CommentLike> countedlike = clikeRepository.countByComment(comment);
//    }

}
