package org.thoughtworks.movieBooking.customers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thoughtworks.movieBooking.customers.repository.CustomerRepository;
import org.thoughtworks.movieBooking.customers.repository.Customer;
import org.thoughtworks.movieBooking.customers.view.model.CustomerSignupRequestBody;
import org.thoughtworks.movieBooking.exceptions.EmailAlreadyExistsException;
import org.thoughtworks.movieBooking.exceptions.PhoneNumberAlreadyTakenException;
import org.thoughtworks.movieBooking.exceptions.UserNameAlreadyExistsException;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer add(CustomerSignupRequestBody customerSignupRequestBody) throws UserNameAlreadyExistsException, EmailAlreadyExistsException, PhoneNumberAlreadyTakenException {

        validate(customerSignupRequestBody);

        User user=new User(customerSignupRequestBody.getUsername(),passwordEncoder.encode(customerSignupRequestBody.getPassword()),"ROLE_USER");

        String encodedPassword=passwordEncoder.encode(customerSignupRequestBody.getPassword());
        Customer customer=new Customer(customerSignupRequestBody.getName(), customerSignupRequestBody.getEmail(), customerSignupRequestBody.getGender(), customerSignupRequestBody.getPhone(), user);

        userRepository.save(user);
        return customerRepository.save(customer);
    }

    public void validate(CustomerSignupRequestBody customerSignupRequestBody) throws UserNameAlreadyExistsException, EmailAlreadyExistsException, PhoneNumberAlreadyTakenException {

        String username=customerSignupRequestBody.getUsername();
        validateUserName(username);

        String emailId=customerSignupRequestBody.getEmail();
        validateEmail(emailId);

        String phone= customerSignupRequestBody.getPhone();
        validatePhoneNumber(phone);
    }

    public void validatePhoneNumber(String phone) throws PhoneNumberAlreadyTakenException {
        Optional<Customer> customer= customerRepository.findByPhone(phone);

        if(customer.isPresent()){
            throw new PhoneNumberAlreadyTakenException();
        }
    }

    public void validateEmail(String emailId) throws EmailAlreadyExistsException {
        Optional<Customer> customer= customerRepository.findByEmail(emailId);

        if(customer.isPresent()){
            throw new EmailAlreadyExistsException();
        }
    }

    public void validateUserName(String username) throws UserNameAlreadyExistsException {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent()){
            throw new UserNameAlreadyExistsException();
        }
    }

}
