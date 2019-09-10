package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    public User registerUser(String email, String name, String password) {

        User user = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .level(3)
                .build();

        return userRepository.save(user);
    }
}
