package org.thoughtworks.movieBooking.users.view.model;

public class UserLoginResponse {
    private  String jwtToken;
    private  Long jwtExpirationTime;

    public UserLoginResponse(String jwtToken, Long jwtExpirationTime) {
        this.jwtToken = jwtToken;
        this.jwtExpirationTime = jwtExpirationTime;
    }

    public Long getJwtExpirationTime() {
        return jwtExpirationTime;
    }

    public String getJwtToken() {
        return jwtToken;
    }



}
