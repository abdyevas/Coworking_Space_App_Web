package com.coworking.controller;

import com.coworking.dto.LoginUserDto;
import com.coworking.dto.RegisterUserDto;
import com.coworking.model.Users;
import com.coworking.service.AuthenticationService;
import com.coworking.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void register_whenValidRequest_thenReturnRegisteredUser() {
        RegisterUserDto dto = new RegisterUserDto();
        Users user = new Users();
        when(authenticationService.signup(dto)).thenReturn(user);

        ResponseEntity<Users> response = authenticationController.register(dto);

        assertNotNull(response.getBody());
        verify(authenticationService, times(1)).signup(dto);
    }

    @Test
    void authenticate_whenUserIsValid_thenReturnJwtToken() {
        LoginUserDto dto = new LoginUserDto();
        Users user = new Users();
        when(authenticationService.authenticate(dto)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");

        ResponseEntity<LoginResponse> response = authenticationController.authenticate(dto);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getToken());
        verify(authenticationService, times(1)).authenticate(dto);
    }
}
