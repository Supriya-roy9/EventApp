package org.thoughtworks.movieBooking.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.repository.UserRepository;
import org.thoughtworks.movieBooking.users.view.model.UserLoginRequestBody;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void beforeEach(){
        authenticationManager=mock(AuthenticationManager.class);
        userRepository=mock(UserRepository.class);
        user=mock(User.class);
    }

    @Test
    public void shouldLoginSuccessfullyWhenTheUserEntersValidCredential(){

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.ofNullable(user));
        UserService userService=new UserService(authenticationManager,userRepository);
        UserLoginRequestBody requestBody=new UserLoginRequestBody("testuser","testpassword");

        userService.authenticate(requestBody);

        verify(userRepository).findByUsername("testuser");
    }

    @Test
    public void shouldThrowUserDoesntExistWhenTheUsernameIsInvalid(){
        UserService userService=new UserService(authenticationManager,userRepository);
        UserLoginRequestBody requestBody=new UserLoginRequestBody("testuser","testpassword");


        Class<UsernameNotFoundException> usernameNotFoundExceptionClass = UsernameNotFoundException.class;
        assertThrows(usernameNotFoundExceptionClass,()->userService.authenticate(requestBody));

    }




}
