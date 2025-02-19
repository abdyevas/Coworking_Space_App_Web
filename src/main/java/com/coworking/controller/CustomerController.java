package com.coworking.controller;

import com.coworking.facade.CustomerFacade;
import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerFacade customerFacade;

    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping
    public String customerDashboard(Model model) {
        List<Spaces> availableSpaces = customerFacade.getAvailableSpaces();
        List<Reservations> reservations = customerFacade.getAllReservations();
        
        model.addAttribute("spaces", availableSpaces);
        model.addAttribute("reservations", reservations);
        
        return "customer";
    }

    @PostMapping("/reserve")
    public String makeReservation(
            @RequestParam String customerName, 
            @RequestParam int id, 
            @RequestParam String reservationDate, 
            @RequestParam String startTime, 
            @RequestParam String endTime) {
        
        customerFacade.makeReservation(customerName, id, reservationDate, startTime, endTime);
        return "redirect:/customer";
    }

    @PostMapping("/cancel")
    public String cancelReservation(@RequestParam int id) {
        customerFacade.cancelReservation(id);
        return "redirect:/customer";
    }
}
