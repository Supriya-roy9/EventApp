package org.thoughtworks.movieBooking.events.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "event")

public class Event {

    public Event(String name, String location, LocalDate event_date, int capacity, int availability, double price) {
        this.availability = availability;
        this.capacity = capacity;
        this.event_date = event_date;
        this.location = location;
        this.name = name;
        this.price = price;
    }

    public Event() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    @NotBlank(message = "Event Name must be provided")
    @Column(name = "name")
    private String name;

    @JsonProperty
    @NotBlank(message = "Event Location must be provided")
    @Column(name = "location")
    private String location;

    @JsonProperty
    @NotNull(message = "Event date must be provided")
    @Column(name = "event_date")
    private LocalDate event_date;

    @JsonProperty
    @NotNull(message = "Capacity must be provided")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(name = "capacity")
    private int capacity;

    @JsonProperty
    @NotNull(message = "Availability must be provided")
    @Min(value = 0, message = "Availability must be at least 0")
    @Column(name = "availability")
    private int availability;

    @JsonProperty
    @NotNull(message = "Price must be provided")
    @Min(value = 0, message = "Price must be at least 0")
    @Column(name = "price")
    private double price;

    @NotNull(message = "Availability must be provided")
    @Min(value = 0, message = "Availability must be at least 0")
    public int getAvailability() {
        return availability;
    }

    @NotNull(message = "Capacity must be provided")
    @Min(value = 1, message = "Capacity must be at least 1")
    public int getCapacity() {
        return capacity;
    }

    public @NotNull(message = "Event date must be provided") LocalDate getEvent_date() {
        return event_date;
    }

    public @NotBlank(message = "Event Location must be provided") String getLocation() {
        return location;
    }

    public @NotBlank(message = "Event Name must be provided") String getName() {
        return name;
    }

    @NotNull(message = "Price must be provided")
    @Min(value = 0, message = "Price must be at least 0")
    public double getPrice() {
        return price;
    }
}
