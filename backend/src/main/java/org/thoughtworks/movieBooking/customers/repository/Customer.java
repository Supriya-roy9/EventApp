package org.thoughtworks.movieBooking.customers.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.thoughtworks.movieBooking.users.repository.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    @NotBlank(message = "Name should be provided")
    private String name;

    @JsonProperty
    @NotBlank(message = "Email must be provided")
    private String email;

    @NotBlank(message = "Gender should not be null")
    private String gender;

    @JsonProperty
    @NotBlank(message = "Phone Number must be provided")
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Customer() {
    }

    public Customer(String name, String email, String gender, String phone, User user) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.user = user;
    }
}
