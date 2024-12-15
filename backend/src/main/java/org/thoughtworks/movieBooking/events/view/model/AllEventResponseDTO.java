package org.thoughtworks.movieBooking.events.view.model;

import java.time.LocalDate;

public class AllEventResponseDTO {

    private String name;

    private String location;

    private LocalDate date;

    private int capacity;

    private double price;

    private int availibity;

    public AllEventResponseDTO(String name, String location, LocalDate eventDate, int capacity, double price, int availability) {
        this.availibity=availability;
        this.date=date;
        this.capacity=capacity;
        this.name=name;
        this.price=price;
        this.location=location;
    }

    public int getAvailibity() {
        return availibity;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}