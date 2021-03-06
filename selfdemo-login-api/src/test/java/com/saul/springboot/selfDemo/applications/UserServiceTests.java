package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

public class UserServiceTests {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);

    }

    @Test
    public void authenticateWithValidData() {

        String email = "poppo@gmail.com";
        String password = "password";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail()).isEqualTo(email);

    }

    @Test(expected = EmailNonExistError.class)
    public void authenticateWithInvalidEmail() {

        String email = "x@gmail.com";
        String password = "password";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        userService.authenticate(email, password);

    }

    @Test(expected = WrongPasswordError.class)
    public void authenticateWithInvalidPassword() {

        String email = "poppo@gmail.com";
        String password = "x";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));

        given(passwordEncoder.matches(eq(email), eq(password))).willThrow(WrongPasswordError.class);

        userService.authenticate(email, password);

    }

}
