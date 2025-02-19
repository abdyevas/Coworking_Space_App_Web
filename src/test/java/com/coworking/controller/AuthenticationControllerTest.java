package com.coworking.controller;

import com.coworking.dto.LoginUserDto;
import com.coworking.dto.RegisterUserDto;
import com.coworking.facade.AuthFacade;
import com.coworking.model.Users;
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
    private AuthFacade authFacade;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void register_whenValidRequest_thenReturnRegisteredUser() {
        RegisterUserDto dto = new RegisterUserDto();
        Users user = new Users();
        when(authFacade.signup(dto)).thenReturn(user);

        ResponseEntity<Users> response = authenticationController.register(dto);

        assertNotNull(response.getBody());
        verify(authFacade, times(1)).signup(dto);
    }

    @Test
    void authenticate_whenUserIsValid_thenReturnJwtToken() {
        LoginUserDto dto = new LoginUserDto();
        Users user = new Users();
        when(authFacade.authenticate(dto)).thenReturn(user);
        when(authFacade.generateToken(user)).thenReturn("token");

        ResponseEntity<LoginResponse> response = authenticationController.authenticate(dto);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getToken());
        verify(authFacade, times(1)).authenticate(dto);
    }
}
