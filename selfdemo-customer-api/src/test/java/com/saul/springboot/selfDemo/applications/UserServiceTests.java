package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

public class UserServiceTests {

    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);

    }

    @Test
    public void registerUser() {
        String email = "poppo@gmail.com";
        String name = "poppo";
        String password = "password";

        User savedMock = User.builder().id(1L).build();

        given(userRepository)

        User user = userService.registerUser(email, name, password);

        assertThat(user.getId()).isEqualTo(1L);

    }

}
