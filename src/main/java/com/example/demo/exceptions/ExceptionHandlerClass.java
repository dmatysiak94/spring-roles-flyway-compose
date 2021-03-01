package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler
    public ResponseEntity<NoCustomer> throwNoCustomer(NoCustomerException ex) {

        NoCustomer noCustomer = new NoCustomer();

        noCustomer.setMessage(ex.getMessage());
        noCustomer.setStatus(HttpStatus.BAD_REQUEST.value());
        noCustomer.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(noCustomer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<NoCustomer> throwNoCustomer(Exception ex) {

        NoCustomer noCustomer = new NoCustomer();

        noCustomer.setMessage(ex.getMessage());
        noCustomer.setStatus(HttpStatus.BAD_REQUEST.value());
        noCustomer.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(noCustomer, HttpStatus.BAD_REQUEST);
    }
}
