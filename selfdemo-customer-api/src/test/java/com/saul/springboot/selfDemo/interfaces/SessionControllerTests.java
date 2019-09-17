package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.EmailNonExistError;
import com.saul.springboot.selfDemo.applications.UserService;
import com.saul.springboot.selfDemo.applications.WrongPasswordError;
import com.saul.springboot.selfDemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
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
    public void tryLoginWithValidData() throws Exception {

        String email = "poppo@gmail.com";
        String password = "ACCESSTOKEN";

        User mockUser = User.builder().password(password).build();

        given(userService.authenticate(eq(email), eq(password))).willReturn(mockUser);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                         "    \"email\":\"poppo@gmail.com\",\n" +
                         "    \"password\":\"ACCESSTOKEN\"\n" +
                         "}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKE\"}"))
        ;

        verify(userService).authenticate(email, password);

    }

    @Test
    public void tryLoginWithWrongPassword() throws Exception {

        String email = "poppo@gmail.com";
        String password = "x";

        given(userService.authenticate(eq(email), eq(password))).willThrow(WrongPasswordError.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"email\":\"poppo@gmail.com\",\n" +
                        "    \"password\":\"x\"\n" +
                        "}"))
                .andExpect(status().isBadRequest())
        ;

    }

    @Test
    public void tryLoginWithNonExistEmail() throws Exception {

        String email = "x";
        String password = "test";

        given(userService.authenticate(eq(email), eq(password))).willThrow(EmailNonExistError.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"email\":\"x\",\n" +
                        "    \"password\":\"test\"\n" +
                        "}"))
                .andExpect(status().isBadRequest())
        ;

    }

}
