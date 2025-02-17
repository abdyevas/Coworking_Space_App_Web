package com.coworking.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "id", nullable = false)    
    private Spaces space;

    public Reservations() {}

    public Reservations(String customerName, Spaces space, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.customerName = customerName;
        this.space = space;
        this.reservationDate = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public LocalDate getReservationDate() { return reservationDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public Spaces getSpace() { return space; }
}
