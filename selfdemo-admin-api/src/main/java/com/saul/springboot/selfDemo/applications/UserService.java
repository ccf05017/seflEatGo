package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User addUser(String name, String email) {

        // TODO
        // 아래 분기문 OOP 스럽게 바꿀 궁리 해보자
        User user;

        if (name.equals("Administrator")) {
            user = User.builder()
                    .name(name)
                    .email(email)
                    .level(100)
                    .build();
        } else {
            user = User.builder()
                    .name(name)
                    .email(email)
                    .level(1)
                    .build();
        }

        User saved = userRepository.save(user);

        return saved;
    }

    @Transactional
    public User updateUser(Long id, String name, String email, Integer level) {

        // TODO
        // Exception 처리할 것

        User updated = userRepository.findById(id).orElse(null);
        updated.setInfo(name, email, level);

        return updated;
    }

    @Transactional
    public User deactivateUser(Long userId) {

        // TODO
        // 예외 처리 수정할 것
        User user = userRepository.findById(userId).orElse(null);
        user.deactivate();

        return user;
    }
}
