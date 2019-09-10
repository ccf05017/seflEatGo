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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void create() throws Exception {

        User mockUser = User.builder().id(123L).build();

        given(userService.registerUser(any())).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"name\":\"poppo\",\n" +
                        "        \"email\":\"poppo@gmail.com\",\n" +
                        "        \"password\":\"password\"\n" +
                        "    }"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/users/123"))
                .andExpect(content().string(containsString("{}")))
        ;

    }

}
