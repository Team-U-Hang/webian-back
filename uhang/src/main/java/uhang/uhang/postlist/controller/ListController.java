package uhang.uhang.postlist.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import uhang.uhang.postlist.domain.Post;
import uhang.uhang.postlist.dto.PostDto;
import uhang.uhang.postlist.service.ListService;

@Controller
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    @GetMapping("/posts/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<PostDto> postsPages = listService.paging(pageable);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }
}