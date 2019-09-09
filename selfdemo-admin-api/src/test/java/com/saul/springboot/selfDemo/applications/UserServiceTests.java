package com.saul.springboot.selfDemo.applications;


import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class UserServiceTests {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);

    }

    @Test
    public void getUsers() {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().name("테스터").build());
        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> resultUsers = userService.getUsers();

        assertThat(resultUsers.get(0).getName()).isEqualTo("테스터");

    }

    @Test
    public void addUser() {

        String name = "Administrator";
        String email = "test@gmail.com";

        User mockUser = User.builder()
                .name(name)
                .email(email)
                .level(3)
                .id(1L)
                .build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(name, email);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getLevel()).isEqualTo(3);
    }

    @Test
    public void updateUser() {

        Long id = 1L;
        String name = "poppo";
        String email = "poppo@gmail.com";
        Integer level = 3;

        given(userRepository.findById(id))
                .willReturn(java.util.Optional.ofNullable(
                        User.builder()
                                .id(id)
                                .name("Administrator")
                                .email(email)
                                .level(1)
                                .build())
                );

        User updated = userService.updateUser(id, name, email, level);

        assertThat(updated.getName()).isEqualTo(name);
        assertThat(updated.getLevel()).isEqualTo(level);

    }

}
