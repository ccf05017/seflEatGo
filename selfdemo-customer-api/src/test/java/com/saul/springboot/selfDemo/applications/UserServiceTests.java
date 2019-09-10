package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);

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

        // TODO
        // 여기서는 원래 given 사용 불가능한 것인지 질문
         given(userRepository.save(any())).willReturn(mockUser);

        User registered = userService.registerUser(email, name, password);

        assertThat(registered.getId()).isEqualTo(1L);
        assertThat(registered.getName()).isEqualTo("poppo");

        verify(userRepository).save(any());

    }

}
