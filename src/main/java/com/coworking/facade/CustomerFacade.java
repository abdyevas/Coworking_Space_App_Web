package com.coworking.facade;

import com.coworking.model.Spaces;
import com.coworking.model.Reservations;
import com.coworking.service.CustomerService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerFacade {

    private final CustomerService customerService;

    public CustomerFacade(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Spaces> getAvailableSpaces() {
        return customerService.getAvailableSpaces();
    }

    public List<Reservations> getAllReservations() {
        return customerService.getAllReservations();
    }

    public void makeReservation(String username, int spaceId, String date, String startTime, String endTime) {
        customerService.makeReservation(username, spaceId, date, startTime, endTime);
    }

    public void cancelReservation(int reservationId) {
        customerService.cancelReservation(reservationId);
    }
}
