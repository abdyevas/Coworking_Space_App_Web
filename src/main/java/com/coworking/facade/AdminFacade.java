package com.coworking.facade;

import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import com.coworking.service.AdminService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminFacade {

    private final AdminService adminService;

    public AdminFacade(AdminService adminService) {
        this.adminService = adminService;
    }

    public List<Spaces> getAllSpaces() {
        return adminService.getAllSpaces();
    }

    public void addSpace(String type, double price) {
        adminService.addSpace(type, price);
    }

    public void deleteSpace(int id) {
        adminService.deleteSpace(id);
    }

    public List<Reservations> getAllReservations() {
        return adminService.getAllReservations();
    }
}
