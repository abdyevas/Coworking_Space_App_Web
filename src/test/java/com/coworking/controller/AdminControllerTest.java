package com.coworking.controller;

import com.coworking.facade.AdminFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private AdminFacade adminFacade;
    
    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @Test
    void givenAdminDashboard_whenRequested_thenReturnAdminView() {
        when(adminFacade.getAllSpaces()).thenReturn(Collections.emptyList());
        
        String view = adminController.adminDashboard(model);
        verify(adminFacade, times(1)).getAllSpaces();
        assertEquals("admin", view);
    }

    @Test
    void givenNewSpace_whenAdded_thenRedirectToAdmin() {
        String view = adminController.addSpace("Office", 100.0);
        verify(adminFacade, times(1)).addSpace("Office", 100.0);
        assertEquals("redirect:/admin", view);
    }

    @Test
    void givenExistingSpace_whenDeleted_thenRedirectToAdmin() {
        String view = adminController.deleteSpace(1);
        verify(adminFacade, times(1)).deleteSpace(1);
        assertEquals("redirect:/admin", view);
    }

    @Test
    void viewReservations_whenRequested_thenReturnReservationView() {
        when(adminFacade.getAllReservations()).thenReturn(Collections.emptyList());
        String viewName = adminController.viewReservations(model);
        assertEquals("reservations", viewName);
        verify(model).addAttribute(eq("reservations"), any());
    }
}
