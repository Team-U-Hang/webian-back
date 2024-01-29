package uhang.uhang.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.login.dto.loginDto;
import uhang.uhang.login.dto.signDto;
import uhang.uhang.login.service.AuthService;
import uhang.uhang.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static uhang.uhang.response.Message.SIGN_UP_SUCCESS;
import static uhang.uhang.response.Response.failure;
import static uhang.uhang.response.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final AuthService authService;


    @ResponseStatus(OK)
    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(
            @RequestBody signDto signDto
    ) {
        return ResponseEntity.ok(authService.signUpUser(signDto));
    }


