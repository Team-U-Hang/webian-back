package uhang.uhang.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.comments.domain.CommentRepository;
import uhang.uhang.comments.domain.exception.LogInRequiredException;
import uhang.uhang.comments.dto.CommentDto;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    public Member getCurrentMember() {
        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member == null) {
            throw new LogInRequiredException();
        }
        return member;
    }
    /*

    public Member getCurrentMemberEmail(){
        Member currentMember = getCurrentMember();
        return commentRepository.findByMemberEmail(currentMember);

    }

     */

    public List<Comment> getCommentsByCurrentMember() {
        Member currentMember = getCurrentMember();
        return commentRepository.findByMember(currentMember);
    }
}