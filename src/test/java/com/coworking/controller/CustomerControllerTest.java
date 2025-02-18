package com.coworking.controller;

import com.coworking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void givenCustomerDashboard_whenRequested_thenReturnCustomerView() {
        when(customerService.getAvailableSpaces()).thenReturn(Collections.emptyList());
        when(customerService.getAllReservations()).thenReturn(Collections.emptyList());

        String view = customerController.customerDashboard(model);

        verify(model, times(1)).addAttribute(eq("spaces"), any(List.class));
        verify(model, times(1)).addAttribute(eq("reservations"), any(List.class));
        assertEquals("customer", view);
    }

    @Test
    void givenNewReservation_whenSuccessful_thenRedirectToCustomer() {
        String view = customerController.makeReservation("John", 1, "2025-03-01", "10:00", "12:00");
        verify(customerService, times(1)).makeReservation("John", 1, "2025-03-01", "10:00", "12:00");
        assertEquals("redirect:/customer", view);
    }

    @Test
    void givenExistingReservation_whenCancelled_thenRedirectToCustomer() {
        String view = customerController.cancelReservation(1);
        verify(customerService, times(1)).cancelReservation(1);
        assertEquals("redirect:/customer", view);
    }
}
