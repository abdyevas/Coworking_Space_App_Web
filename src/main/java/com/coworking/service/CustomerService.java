package com.coworking.service;

import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import com.coworking.repository.ReservationsRepository;
import com.coworking.repository.SpacesRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CustomerService {
    private final SpacesRepository spacesRepository;
    private final ReservationsRepository reservationsRepository;

    public CustomerService(SpacesRepository spacesRepository, ReservationsRepository reservationsRepository) {
        this.spacesRepository = spacesRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<Spaces> getAvailableSpaces() {
        return spacesRepository.findAll().stream().filter(Spaces::isAvailable).toList();
    }

    public void makeReservation(String customerName, int spaceID, String date, String startTime, String endTime) {
        Spaces space = spacesRepository.findById(spaceID)
            .orElseThrow(() -> new IllegalArgumentException("Space not found with ID: " + spaceID));
        Reservations reservation = new Reservations(customerName, space, LocalDate.parse(date), LocalTime.parse(startTime), LocalTime.parse(endTime));
        reservationsRepository.save(reservation);
        space.setAvailable(false);
        spacesRepository.save(space);
    }

    public void cancelReservation(int id) {
        Reservations reservation = reservationsRepository.findById(id).orElseThrow();
        Spaces space = reservation.getSpace();

        reservationsRepository.deleteById(id);
        space.setAvailable(true);
        spacesRepository.save(space);
    }

    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }
}
