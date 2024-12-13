package org.thoughtworks.movieBooking.users.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thoughtworks.movieBooking.jwt.JwtService;
import org.thoughtworks.movieBooking.users.UserService;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.view.model.UserLoginRequestBody;
import org.thoughtworks.movieBooking.users.view.model.UserLoginResponse;

import javax.validation.Valid;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final JwtService jwtService;

    private final UserService userService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponse> authenticate(@Valid @RequestBody UserLoginRequestBody userLoginRequestBody) {


        User authenticatedUser = userService.authenticate(userLoginRequestBody);


        String jwtToken = jwtService.generateToken(authenticatedUser);


        UserLoginResponse response = new UserLoginResponse(jwtToken, jwtService.getJwtExpirationTime());


        return ResponseEntity.ok(response);
    }
}
