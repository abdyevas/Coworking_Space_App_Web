package com.coworking.controller;

import com.coworking.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("spaces", adminService.getAllSpaces());
        return "admin";
    }

    @PostMapping("/add")
    public String addSpace(@RequestParam String type, @RequestParam double price) {
        adminService.addSpace(type, price);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteSpace(@RequestParam int id) {
        adminService.deleteSpace(id);
        return "redirect:/admin";
    }
}
