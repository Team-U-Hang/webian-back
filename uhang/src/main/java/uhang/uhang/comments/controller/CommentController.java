package uhang.uhang.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.comments.domain.Comment;
import uhang.uhang.comments.service.CommentService;
import uhang.uhang.login.domain.Member;

import java.util.List;

@RestController
@RequestMapping("/mypage")
public class CommentController {



    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/my-comments")
    public List<Comment> getCommentsByCurrentMember() {
        return commentService.getCommentsByCurrentMember();
    }
}