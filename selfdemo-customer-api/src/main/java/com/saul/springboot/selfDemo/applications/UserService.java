package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User registerUser(String email, String name, String password) {

        // email 중복 여부 확인
        Optional<User> existed = userRepository.findByEmail(email);
        if (existed.isPresent()) {
            throw new EmailExistError(email);
        }

        // Password Encryption
        String encryptedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encryptedPassword)
                .level(1)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNonExistError(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {

            throw new WrongPasswordError();
        }

        return user;

    }
}
