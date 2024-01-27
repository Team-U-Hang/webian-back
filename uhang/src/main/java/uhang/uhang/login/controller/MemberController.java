package uhang.uhang.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.login.dto.requestDto;
import uhang.uhang.login.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public int memberjoin (@RequestBody requestDto dto) {
        return memberService.join(dto);
    }
}
