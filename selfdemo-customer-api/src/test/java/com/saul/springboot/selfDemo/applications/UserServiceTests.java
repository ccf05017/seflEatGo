package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
    public void registerTest() {

        String email = "poppo@gmail.com";
        String name = "poppo";
        String password = "password";

        User mockUser = User.builder()
                .id(1L)
                .email(email)
                .name(name)
                .password(password)
                .build();

         given(userRepository.save(any())).willReturn(mockUser);

        User registered = userService.registerUser(email, name, password);

        assertThat(registered.getId()).isEqualTo(1L);
        assertThat(registered.getName()).isEqualTo("poppo");

        verify(userRepository).save(any());

    }

    @Test(expected = EmailExistError.class)
    public void registerTestWithExistedEmail() {

        String email = "poppo@gmail.com";
        String name = "poppo";
        String password = "password";

        User mockUser = User.builder()
                .id(1L)
                .email(email)
                .name(name)
                .password(password)
                .build();

        given(userRepository.findByEmail(any())).willReturn(java.util.Optional.ofNullable(mockUser));

        userService.registerUser(email, name, password);

        verify(userRepository, never()).save(any());

    }

}
