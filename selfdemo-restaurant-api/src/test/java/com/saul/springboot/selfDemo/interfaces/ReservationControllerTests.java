package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ReservationControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReservationService reservationService;

    @Test
    public void getList() throws Exception {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMzLCJ1c2VyTmFtZSI6InBvcHBvIiwicmVzdGF1cmFudElkIjozM30.A3kiYg8SvyzPkPN6By_1BG7tBhiwWEkt8yhchP8wNi8";

        mvc.perform(get("/reservations")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(reservationService).getReservations(33L);
    }

}