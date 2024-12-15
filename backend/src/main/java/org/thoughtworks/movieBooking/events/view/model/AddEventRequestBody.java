package org.thoughtworks.movieBooking.events.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddEventRequestBody {

    public AddEventRequestBody() {
    }

    public AddEventRequestBody(String name, String location, String capacity, String price, String event_date) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.price = price;
        this.event_date = event_date;
    }

    @JsonProperty
    private String name;

    @JsonProperty
    private String location;

    @JsonProperty
    private String capacity;

    @JsonProperty
    private String price;

    @JsonProperty
    private String event_date;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getPrice() {
        return price;
    }

    public String getEvent_date() {
        return event_date;
    }
}
