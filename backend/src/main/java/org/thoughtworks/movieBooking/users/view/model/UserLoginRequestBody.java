package org.thoughtworks.movieBooking.users.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class UserLoginRequestBody {

    public UserLoginRequestBody() {
    }

    @JsonProperty
    @NotBlank(message = "Username must be provided")
    private String username;

    @JsonProperty
    @NotBlank(message = "Password must be provided")
    private String password;

    public UserLoginRequestBody(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public @NotBlank(message = "Password must be provided") String getPassword() {
        return password;
    }

    public @NotBlank(message = "Username must be provided") String getUsername() {
        return username;
    }
}
