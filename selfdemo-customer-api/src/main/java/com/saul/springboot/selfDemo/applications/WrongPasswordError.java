package com.saul.springboot.selfDemo.applications;

public class WrongPasswordError extends RuntimeException {

    public WrongPasswordError() {
        super("Wrong Password Requested");
    }

}
