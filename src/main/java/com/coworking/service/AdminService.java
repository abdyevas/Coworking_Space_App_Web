package com.coworking.service;

import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import com.coworking.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    private final SpacesRepository spacesRepository;
    private final ReservationsRepository reservationsRepository;

    public AdminService(SpacesRepository spacesRepository, ReservationsRepository reservationsRepository) {
        this.spacesRepository = spacesRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public void addSpace(String type, double price) {
        Spaces space = new Spaces(type, price);
        spacesRepository.save(space);
    }

    public void deleteSpace(int id) {
        spacesRepository.deleteById(id);
    }

    public List<Spaces> getAllSpaces() {
        return spacesRepository.findAll().stream().filter(Spaces::isAvailable).toList();
    }

    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll(); 
    }
}
