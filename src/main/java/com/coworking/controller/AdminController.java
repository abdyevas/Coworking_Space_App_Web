package com.coworking.controller;

import com.coworking.facade.AdminFacade;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminFacade adminFacade;

    public AdminController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("spaces", adminFacade.getAllSpaces());
        return "admin";
    }

    @PostMapping("/add")
    public String addSpace(@RequestParam String type, @RequestParam double price) {
        adminFacade.addSpace(type, price);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteSpace(@RequestParam int id) {
        adminFacade.deleteSpace(id);
        return "redirect:/admin";
    }

    @GetMapping("/reservations")
    public String viewReservations(Model model) {
        model.addAttribute("reservations", adminFacade.getAllReservations() != null 
            ? adminFacade.getAllReservations() : Collections.emptyList());
        return "reservations";
    }
}
