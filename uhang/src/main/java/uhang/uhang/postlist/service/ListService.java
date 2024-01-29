package uhang.uhang.postlist.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uhang.uhang.postlist.domain.Post;
import uhang.uhang.postlist.domain.repository.PostRepository;
import uhang.uhang.postlist.dto.PostDto;


@Service
@RequiredArgsConstructor
public class ListService {
    private final PostRepository postRepository;

    public Page<PostDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // 페이지 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 6; // 한 페이지에 보여줄 글 개수

        // 한 페이지당 6개식 글을 보여주고 정렬 기준은 ID 기준으로 내림차순
        Page<Post> postsPages = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "eventId")));

        // 목록: id, title, content, author
        Page<PostDto> postDtoPage = postsPages.map(PostDto::new); // PostDto의 생성자 이용

        return postDtoPage;
    }
}