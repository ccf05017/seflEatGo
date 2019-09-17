package com.saul.springboot.selfDemo.applications;

public class EmailNonExistError extends RuntimeException {

    public EmailNonExistError(String email) {

        super("Email Not Registered: " + email);

    }

}
