package org.example.finalprojectepamlabapplication.exceptionhandler;

import org.example.finalprojectepamlabapplication.errorhandler.RestResponseEntityExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestResponseEntityExceptionHandlerTest {

    private RestResponseEntityExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new RestResponseEntityExceptionHandler();
    }

    @Test
    public void testHandleEntityNotFoundException() {
        NoSuchElementException exception = new NoSuchElementException("Entity not found");

        ResponseEntity<String> response = exceptionHandler.handleEntityNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody());
    }

    @Test
    public void testHandleValidationException() {
        BindingResult bindingResult = mock(BindingResult.class);
        MethodParameter methodParameter = mock(MethodParameter.class);

        FieldError fieldError = new FieldError("object", "field", "Field error message");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);
        ResponseEntity<String> response = exceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("field : Field error message", response.getBody() != null ? response.getBody().trim() : null);
    }

    @Test
    public void testHandleDataIntegrityViolationException() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Data integrity violation");

        ResponseEntity<String> response = exceptionHandler.handleDataIntegrityViolationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Data integrity violation", response.getBody());
    }
}
