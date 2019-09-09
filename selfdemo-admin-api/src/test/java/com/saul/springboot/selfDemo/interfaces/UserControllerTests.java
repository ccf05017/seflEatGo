package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import com.saul.springboot.selfDemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void list() throws Exception {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .name("테스터")
                .id(1L)
                .email("test@gmail.com")
                .level(1)
                .build());

        given(userService.getUsers()).willReturn(mockUsers);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"테스터\"")))
        ;

    }

    @Test
    public void create() throws Exception {

        String name = "Administrator";
        String email = "admin@gmail.com";

        User mockUser = User.builder().name(name).email(email).id(1L).build();

        given(userService.addUser(name, email)).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Administrator\",\"email\":\"admin@gmail.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/users/1"))
                .andExpect(content().string(containsString("{}")))
        ;

    }

    @Test
    public void update() throws Exception {

        Long id = 1L;
        String name = "poppo";
        String email = "poppo@gmail.com";
        Integer level = 3;

        mvc.perform(patch("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"poppo\",\"email\":\"poppo@gmail.com\",\"level\":3}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{}")))
        ;

        verify(userService).updateUser(id, name, email, level);

    }

}
