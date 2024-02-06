package uhang.uhang.posting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostLike;
import uhang.uhang.commentlike.domain.CommentLike;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostLike;
import uhang.uhang.review.domain.entity.Review;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    public List<PostLike> findByMember(Member member);

    public Integer countByPost(Post post);

    Optional<PostLike> findByMemberAndPost(Member member, Post post);

    //bestpost 를 위해 추가된 부분
    @Query
            ("SELECT pl.post.eventId, COUNT(DISTINCT pl.member.memberId) " +
            "FROM postlike pl " +
            "GROUP BY pl.post.eventId " +
            "ORDER BY COUNT(DISTINCT pl.member.memberId) DESC")
    List<Object[]> findTopThreeEventIdsWithUniqueMemberCounts();
}
