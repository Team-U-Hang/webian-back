package uhang.uhang.login.service;

import uhang.uhang.Auth.TokenDto;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.dto.loginDto;
import uhang.uhang.login.dto.signDto;

public interface AuthService {
    void signUpUser(signDto signDto);

    TokenDto loginUser(loginDto loginDto);


}
