package org.thoughtworks.movieBooking.customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thoughtworks.movieBooking.customers.repository.CustomerRepository;
import org.thoughtworks.movieBooking.customers.repository.Customer;
import org.thoughtworks.movieBooking.customers.view.model.CustomerSignupRequestBody;
import org.thoughtworks.movieBooking.exceptions.EmailAlreadyExistsException;
import org.thoughtworks.movieBooking.exceptions.PhoneNumberAlreadyTakenException;
import org.thoughtworks.movieBooking.exceptions.UserNameAlreadyExistsException;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.repository.UserRepository;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerRepository customerRepository;

    private CustomerSignupRequestBody customerSignupRequestBody;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private User user;

    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void beforeEach() {
        customerRepository = mock(CustomerRepository.class);
        userRepository = mock(UserRepository.class);
        customerSignupRequestBody = mock(CustomerSignupRequestBody.class);
        passwordEncoder = mock(PasswordEncoder.class);
        user = mock(User.class);
        authenticationManager = mock(AuthenticationManager.class);
    }

    @Test
    public void shouldPostTheCustomerDetailsSuccessfullyWhenTheCustomerSignsUp() throws UserNameAlreadyExistsException, EmailAlreadyExistsException, PhoneNumberAlreadyTakenException {

        CustomerService customerService = new CustomerService(customerRepository, userRepository, passwordEncoder);
        customerService.add(customerSignupRequestBody);

        verify(customerRepository, times(1)).save(any(Customer.class));

    }


    @Test
    public void shouldReturnTheUserByUsernameWhenUserAlreadyExists() throws UserNameAlreadyExistsException {
        String username = "testuser";
        CustomerService customerService = new CustomerService(customerRepository, userRepository,passwordEncoder);

        customerService.validateUserName("testuser");

        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void shouldReturnTheCustomerWhenCustomerEmailAlreadyExists() throws EmailAlreadyExistsException {
        String emailId = "testemail@gmail.com";

        CustomerService customerService = new CustomerService(customerRepository, userRepository, passwordEncoder);

        customerService.validateEmail(emailId);

        verify(customerRepository, times(1)).findByEmail(emailId);
    }

    @Test
    public void shouldReturnTheCustomerWhenCustomerPhoneAlreadyExists() throws PhoneNumberAlreadyTakenException {
        String phone = "9876543210";

        CustomerService customerService = new CustomerService(customerRepository, userRepository, passwordEncoder);

        customerService.validatePhoneNumber(phone);

        verify(customerRepository, times(1)).findByPhone(phone);
    }
}
