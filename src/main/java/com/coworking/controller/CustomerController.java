package com.coworking.controller;

import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import com.coworking.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String customerDashboard(Model model) {
        List<Spaces> availableSpaces = customerService.getAvailableSpaces();
        List<Reservations> reservations = customerService.getAllReservations();
        
        model.addAttribute("spaces", availableSpaces);
        model.addAttribute("reservations", reservations);
        
        return "customer";
    }

    @PostMapping("/reserve")
    public String makeReservation(
            @RequestParam String customerName, 
            @RequestParam int spaceID, 
            @RequestParam String date, 
            @RequestParam String startTime, 
            @RequestParam String endTime) {
        
        customerService.makeReservation(customerName, spaceID, date, startTime, endTime);
        return "redirect:/customer";
    }

    @PostMapping("/cancel")
    public String cancelReservation(@RequestParam int id) {
        customerService.cancelReservation(id);
        return "redirect:/customer";
    }
}
