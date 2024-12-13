package org.thoughtworks.movieBooking.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.repository.UserRepository;
import org.thoughtworks.movieBooking.users.view.model.UserLoginRequestBody;

import java.util.Optional;

@Service
public class UserService{

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User authenticate(UserLoginRequestBody input){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword()));

        Optional<User> user= userRepository.findByUsername(input.getUsername());

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User doesn't exist");
        }

        return user.get();
    }
}
