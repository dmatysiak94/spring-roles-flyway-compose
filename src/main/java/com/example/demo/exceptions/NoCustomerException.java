package com.example.demo.exceptions;

public class NoCustomerException extends RuntimeException {

    public NoCustomerException(String s) {
        super(s);
    }
}
