package org.thoughtworks.movieBooking.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thoughtworks.movieBooking.exceptions.EmailAlreadyExistsException;
import org.thoughtworks.movieBooking.exceptions.PhoneNumberAlreadyTakenException;
import org.thoughtworks.movieBooking.exceptions.UserNameAlreadyExistsException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<String> handlerUserNameAlreadyExistsException(){
        return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(){
        return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhoneNumberAlreadyTakenException.class)
    public ResponseEntity<String> handlePhoneNumberAlreadyExistsException(){
        return new ResponseEntity<>("Phone Number Already Taken",HttpStatus.CONFLICT);
    }

}
