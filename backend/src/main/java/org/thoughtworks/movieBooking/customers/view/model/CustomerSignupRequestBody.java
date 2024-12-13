package org.thoughtworks.movieBooking.customers.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerSignupRequestBody {

    @JsonProperty
    @NotBlank(message = "Username must be provided")
    private String username;

    @JsonProperty
    @NotBlank(message = "Name should be provided")
    private String name;

    @JsonProperty
    @NotBlank(message = "Email must be provided")
    private String email;

    @JsonProperty
    @NotBlank(message = "Phone Number must be provided")
    private String phone;

    @NotBlank(message = "Gender must not be null")
    private String gender;

    @JsonProperty
    @NotBlank(message = "Password must be provided")
    private String password;

    public @NotBlank(message = "Email must be provided") String getEmail() {
        return email;
    }

    public @NotNull(message = "Gender must not be null") String getGender() {
        return gender;
    }

    public @NotBlank(message = "Name should be provided") String getName() {
        return name;
    }

    public @NotBlank(message = "Password must be provided") String getPassword() {
        return password;
    }

    public @NotBlank(message = "Phone Number must be provided") String getPhone() {
        return phone;
    }

    public @NotBlank(message = "Username must be provided") String getUsername() {
        return username;
    }


}
