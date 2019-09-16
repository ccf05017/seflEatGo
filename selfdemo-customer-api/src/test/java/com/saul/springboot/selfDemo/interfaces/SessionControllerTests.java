package com.saul.springboot.selfDemo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void tryLogin() throws Exception {

        mvc.perform(post("/session"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKEN\"}"))
        ;

    }

}
