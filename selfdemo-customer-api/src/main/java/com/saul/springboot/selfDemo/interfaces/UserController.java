package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import com.saul.springboot.selfDemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {

        User user = userService.registerUser(resource.getEmail(), resource.getName(), resource.getPassword());

        String url = "/users/" + user.getId();

        return ResponseEntity.created(new URI(url)).body("{}");

    }

}
