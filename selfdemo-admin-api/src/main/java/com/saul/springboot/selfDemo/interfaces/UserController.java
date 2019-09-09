package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import com.saul.springboot.selfDemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> list() {

        return userService.getUsers();

    }

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {

        User user = userService.addUser(resource.getName(), resource.getEmail());

        URI url = new URI("/users/" + user.getId());

        return ResponseEntity.created(url).body("{}");
    }

    @PatchMapping("users/{userId}")
    public String update(
            @PathVariable(name = "userId") Long userId,
            @RequestBody User resource
    ) {
        userService.updateUser(userId, resource.getName(), resource.getEmail(), resource.getLevel());

        return "{}";
    }

    @DeleteMapping("users/{userId}")
    public String delete(
            @PathVariable(name = "userId") Long userId
    ) {
        userService.deactivateUser(userId);

        return "{}";
    }

}
