package com.coworking.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationID;
    private String customerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private Spaces space;

    public Reservations() {}

    public Reservations(String customerName, Spaces space, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.customerName = customerName;
        this.space = space;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getReservationID() { return reservationID; }
    public String getCustomerName() { return customerName; }
    public LocalDate getDate() { return date; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public Spaces getSpace() { return space; }
}
