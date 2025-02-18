package com.coworking.controller;

import com.coworking.service.AdminService;
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
    private AdminService adminService;

    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @Test
    void givenAdminDashboard_whenRequested_thenReturnAdminView() {
        String view = adminController.adminDashboard(model);
        verify(adminService, times(1)).getAllSpaces();
        assertEquals("admin", view);
    }

    @Test
    void givenNewSpace_whenAdded_thenRedirectToAdmin() {
        String view = adminController.addSpace("Office", 100.0);
        verify(adminService, times(1)).addSpace("Office", 100.0);
        assertEquals("redirect:/admin", view);
    }

    @Test
    void givenExistingSpace_whenDeleted_thenRedirectToAdmin() {
        String view = adminController.deleteSpace(1);
        verify(adminService, times(1)).deleteSpace(1);
        assertEquals("redirect:/admin", view);
    }

    @Test
    void viewReservations_whenRequested_thenReturnReservationView() {
        when(adminService.getAllReservations()).thenReturn(Collections.emptyList());
        String viewName = adminController.viewReservations(model);
        assertEquals("reservations", viewName);
        verify(model).addAttribute(eq("reservations"), any());
    }
}
