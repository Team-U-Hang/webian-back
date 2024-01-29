package uhang.uhang.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uhang.uhang.Auth.TokenDto;
import uhang.uhang.login.dto.loginDto;
import uhang.uhang.login.dto.signDto;
import uhang.uhang.login.service.AuthService;
import uhang.uhang.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static uhang.uhang.response.Message.LOG_IN_SUCCESS;
import static uhang.uhang.response.Message.SIGN_UP_SUCCESS;
import static uhang.uhang.response.Response.failure;
import static uhang.uhang.response.Response.success;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final AuthService authService;


    @ResponseStatus(OK)
    @PostMapping("/sign-up")
    public Response signUp(@RequestBody signDto signDto) {
        authService.signUpUser(signDto);
        return success(SIGN_UP_SUCCESS);
    }
    @ResponseStatus(OK)
    @PostMapping("/log-in")
    public Response logIn(@RequestBody loginDto loginDto) {
        TokenDto response = authService.loginUser(loginDto);
        return success(LOG_IN_SUCCESS,response);
    }

}


