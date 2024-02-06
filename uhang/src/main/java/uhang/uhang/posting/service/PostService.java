package uhang.uhang.posting.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uhang.uhang.exception.LogInRequiredException;
import uhang.uhang.interest.domain.Category;
import uhang.uhang.interest.domain.InterestCategory;
import uhang.uhang.interest.domain.repository.CategoryRepository;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.posting.domain.entity.Post;
import uhang.uhang.posting.domain.entity.PostCategory;
import uhang.uhang.posting.domain.entity.PostLike;
import uhang.uhang.posting.domain.repository.PostCategoryRepository;
import uhang.uhang.posting.domain.repository.PostLikeRepository;
import uhang.uhang.posting.domain.repository.PostRepository;
import uhang.uhang.posting.dto.PostRequestDto;
import uhang.uhang.posting.dto.PostResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final PostLikeRepository postLikeRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public PostService(PostRepository postRepository, PostCategoryRepository postCategoryRepository, PostLikeRepository postLikeRepository,
                       CategoryRepository categoryRepository, MemberRepository memberRepository) {

        this.postRepository = postRepository;
        this.postCategoryRepository = postCategoryRepository;
        this.postLikeRepository = postLikeRepository;
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
    }

    public Member getCurrentMember() {
        Member member = memberRepository.findByMemberEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(member == null) {
            throw new LogInRequiredException();
        }
        return member;
    }

    // 이벤트 게시물 등록 -> 이벤트 아이디값 넘기기
    @Transactional
    public Long savePost(PostRequestDto postDto, List<Long> categories) {

        Post post = postRepository.save(postDto.toEntity());
        Member member = getCurrentMember();

        for (long category : categories) {
            Category categoryList = categoryRepository.findById(category)
                    .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

            PostCategory postCategory = new PostCategory();
            postCategory.setPost(post);
            postCategory.setCategory(categoryList);

            postCategoryRepository.save(postCategory);
        }
        post.setMember(member);
        return post.getEventId();
    }

    public List<Long> getPostCategories(Long eventId){
        List<PostCategory> postCategories = postCategoryRepository.findByPost_eventId(eventId);

        return postCategories.stream()
                    .map(PostCategory::getPostcategoryId)
                    .collect(Collectors.toList());
        }


    // 이벤트 게시물 상세 조회 -> 이벤트 아이디값 받아와서
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        assert post != null;

        return convertToDTO(post);
    }

    private PostResponseDto convertToDTO(Post post) {
        return PostResponseDto.builder()
                .eventId(post.getEventId())
                .eventTitle(post.getEventTitle())
                .eventTime(post.getEventTime())
                .eventDate(post.getEventDate())
                .eventLoc(post.getEventLoc())
//                .eventType(post.getEventType())
                .eventText(post.getEventText())
                .timeStamp(post.getTimeStamp())
                .totalLike(post.getTotalLike())
                .build();
    }


    public Page<Post> getPostsByEventTypes(List<Integer> eventTypes, Pageable pageable) {
        return postRepository.findByEventTypeIn(eventTypes, pageable);
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getLikedPostsByCurrentMember() {
        Member currentMember = getCurrentMember();
        List<PostLike> postLikes = postLikeRepository.findByMember(currentMember);
        // Extract the posts from the liked entities
        return postLikes.stream()
                .map(PostLike::getPost)
                .toList();
    }

}