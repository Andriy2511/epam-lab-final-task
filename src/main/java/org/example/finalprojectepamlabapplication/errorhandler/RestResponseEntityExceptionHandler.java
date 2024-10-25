package org.example.finalprojectepamlabapplication.errorhandler;

import org.example.finalprojectepamlabapplication.exception.UnauthorizedException;
import org.example.finalprojectepamlabapplication.exception.UserAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleEntityNotFoundException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        StringBuilder errorDetails = new StringBuilder();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errorDetails.append(error.getField())
                .append(" : ")
                .append(error.getDefaultMessage())
                .append(System.lineSeparator()));

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
