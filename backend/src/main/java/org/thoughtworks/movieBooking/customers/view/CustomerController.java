package org.thoughtworks.movieBooking.customers.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thoughtworks.movieBooking.customers.CustomerService;
import org.thoughtworks.movieBooking.customers.repository.Customer;
import org.thoughtworks.movieBooking.customers.view.model.CustomerSignupRequestBody;
import org.thoughtworks.movieBooking.exceptions.EmailAlreadyExistsException;
import org.thoughtworks.movieBooking.exceptions.PhoneNumberAlreadyTakenException;
import org.thoughtworks.movieBooking.exceptions.UserNameAlreadyExistsException;
import org.thoughtworks.movieBooking.jwt.JwtService;
import org.thoughtworks.movieBooking.users.repository.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    private final JwtService jwtService;

    public CustomerController(CustomerService customerService, JwtService jwtService) {
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerSignupRequestBody customerSignupRequestBody) throws UserNameAlreadyExistsException, EmailAlreadyExistsException, PhoneNumberAlreadyTakenException {
       Customer registeredUser= customerService.add(customerSignupRequestBody);
       return new ResponseEntity<>("User registered successfully",HttpStatus.CREATED);
    }




}
