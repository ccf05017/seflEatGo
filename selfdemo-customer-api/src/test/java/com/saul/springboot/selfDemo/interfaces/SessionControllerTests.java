package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void tryLogin() throws Exception {

        String email = "poppo@gmail.com";
        String password = "test";

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                         "    \"email\":\"poppo@gmail.com\",\n" +
                         "    \"password\":\"test\"\n" +
                         "}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKEN\"}"))
        ;

        verify(userService).authenticate(email, password);

    }

}
