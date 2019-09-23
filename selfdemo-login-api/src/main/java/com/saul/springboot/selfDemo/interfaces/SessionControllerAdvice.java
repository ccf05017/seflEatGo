package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.EmailNonExistError;
import com.saul.springboot.selfDemo.applications.WrongPasswordError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPasswordError.class)
    public String handleWrongPasswordException() {

        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNonExistError.class)
    public String handleNonExistEmailException() {

        return "{}";
    }

}
