package com.coworking.facade;

import com.coworking.dto.LoginUserDto;
import com.coworking.dto.RegisterUserDto;
import com.coworking.model.Users;
import com.coworking.service.AuthenticationService;
import com.coworking.service.JwtService;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthFacade(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    public Users signup(RegisterUserDto dto) {
        return authenticationService.signup(dto);
    }

    public Users authenticate(LoginUserDto dto) {
        return authenticationService.authenticate(dto);
    }

    public String generateToken(Users user) {
        return jwtService.generateToken(user);
    }

    public long getExpirationTime() {
        return jwtService.getExpirationTime();
    }
}
