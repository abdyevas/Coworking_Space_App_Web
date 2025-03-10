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
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private SpacesRepository spacesRepository;

    @Mock
    private ReservationsRepository reservationsRepository;

    @InjectMocks
    private AdminService adminService;

    private Spaces space1;
    private Spaces space2;

    @BeforeEach
    void setUp() {
        space1 = new Spaces("Type1", 100.0);
        space2 = new Spaces("Type2", 150.0);
    }

    @Test
    void givenNewSpace_whenAdded_thenSaveToDb() {
        when(spacesRepository.save(any(Spaces.class))).thenReturn(space1);  

        adminService.addSpace("Type1", 100.0);

        verify(spacesRepository, times(1)).save(any(Spaces.class));  
    }

    @Test
    void givenExistingSpace_whenDeleted_thenRemoveFromDb() {
        adminService.deleteSpace(1);

        verify(spacesRepository, times(1)).deleteById(1); 
    }

    @Test
    void whenGetAllSpaces_thenReturnAvailableSpaces() {
        space1.setAvailable(true);
        space2.setAvailable(false);
        when(spacesRepository.findAll()).thenReturn(Arrays.asList(space1, space2));  

        List<Spaces> spaces = adminService.getAllSpaces();

        assertEquals(1, spaces.size());  
        assertTrue(spaces.contains(space1));  
    }

    @Test
    void whenGetAllReservations_thenReturnReservations() {
        Reservations reservation = new Reservations();
        when(reservationsRepository.findAll()).thenReturn(List.of(reservation));  

        List<Reservations> reservations = adminService.getAllReservations();

        assertEquals(1, reservations.size());  
    }

    @Test
    void whenAddInvalidSpace_thenThrowException() {
        when(spacesRepository.save(any(Spaces.class))).thenThrow(new IllegalArgumentException("Invalid space"));

        assertThrows(IllegalArgumentException.class, () -> adminService.addSpace("Invalid", -100.0));
    }
}
