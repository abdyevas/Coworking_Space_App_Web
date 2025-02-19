package com.coworking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coworking.dto.LoginUserDto;
import com.coworking.dto.RegisterUserDto;
import com.coworking.facade.AuthFacade;
import com.coworking.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthFacade authFacade;

    public AuthenticationController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody RegisterUserDto registerUserDto) {
        Users registeredUser = authFacade.signup(registerUserDto);
        logger.info("Successfully registered!");

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        logger.info("Login started...");
        Users authenticatedUser = authFacade.authenticate(loginUserDto);
        if (authenticatedUser != null) {
            logger.info("User authenticated successfully: " + authenticatedUser.getUsername());
        } else {
            logger.error("Authentication failed.");
        }

        String jwtToken = authFacade.generateToken(authenticatedUser);
        logger.info("JWT token generated: " + jwtToken);
                
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(authFacade.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}