package com.saul.springboot.selfDemo.applications;

public class EmailExistError extends RuntimeException {

    public EmailExistError(String email) {
        super("Email already existed: " + email);
    }

}
