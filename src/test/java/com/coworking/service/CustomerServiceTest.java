package com.coworking.service;

import com.coworking.model.Reservations;
import com.coworking.model.Spaces;
import com.coworking.repository.ReservationsRepository;
import com.coworking.repository.SpacesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private SpacesRepository spacesRepository;

    @Mock
    private ReservationsRepository reservationsRepository;

    @InjectMocks
    private CustomerService customerService;

    private Spaces space;
    private Reservations reservation;

    @BeforeEach
    void setUp() {
        space = new Spaces("Type1", 100.0);
        reservation = new Reservations("Customer", space, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
    }

    @Test
    void whenGetAvailableSpaces_thenReturnAvailableSpaces() {
        space.setAvailable(true);
        when(spacesRepository.findAll()).thenReturn(Arrays.asList(space));

        List<Spaces> availableSpaces = customerService.getAvailableSpaces();

        assertEquals(1, availableSpaces.size());
        assertTrue(availableSpaces.contains(space));
    }

    @Test
    void givenNewReservation_whenReserved_thenAddedToDb() {
        when(spacesRepository.findById(1)).thenReturn(Optional.of(space));
        when(reservationsRepository.save(any(Reservations.class))).thenReturn(reservation);

        customerService.makeReservation("Customer", 1, "2025-02-18", "10:00", "11:00");

        verify(reservationsRepository, times(1)).save(any(Reservations.class));
        assertFalse(space.isAvailable());
        verify(spacesRepository, times(1)).save(space);
    }

    @Test
    void givenExistingReservation_whenCancelled_thenRemovedFromDb() {
        when(reservationsRepository.findById(1)).thenReturn(Optional.of(reservation));

        customerService.cancelReservation(1);

        verify(reservationsRepository, times(1)).deleteById(1);
        assertTrue(space.isAvailable());
        verify(spacesRepository, times(1)).save(space);
    }

    @Test
    void whenGetAllReservations_thenReturnReservations() {
        when(reservationsRepository.findAll()).thenReturn(List.of(reservation));

        List<Reservations> reservations = customerService.getAllReservations();

        assertEquals(1, reservations.size());
    }
}
