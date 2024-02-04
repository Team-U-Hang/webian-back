package uhang.uhang.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.comments.domain.Review;
import uhang.uhang.comments.domain.ReviewRepository;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;

import java.util.List;

@Service
public class ReviewService {


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository commentRepository;

    @Autowired
    public ReviewService(ReviewRepository commentRepository, MemberRepository memberRepository) {
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

    public List<Review> getCommentsByCurrentMember() {
        Member currentMember = getCurrentMember();
        return commentRepository.findByMember(currentMember);
    }
}