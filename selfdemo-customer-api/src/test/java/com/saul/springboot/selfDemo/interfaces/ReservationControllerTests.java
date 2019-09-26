package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ReservationService;
import com.saul.springboot.selfDemo.domain.Reservation;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReservationService reservationService;

    @Test
    public void create() throws Exception {

        Integer partySize = 30;
        String date = "2019-09-24";
        String time = "17:00";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMzLCJ1c2VyTmFtZSI6InBvcHBvIn0.urySBGTtV3UuR45LysoDkvRoX0cASX6dE3a1KLBj0DM";

        Reservation mockReservation = Reservation.builder().id(1L).build();

        given(reservationService.makeReservation(33L, 3333L, "poppo", partySize, date, time))
                .willReturn(mockReservation);

        mvc.perform(post("/restaurants/3333/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"partySize\": 30,\n" +
                        "    \"date\": \"2019-09-24\",\n" +
                        "    \"time\": \"17:00\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/restaurants/3333/reservations/1"))
        ;

        verify(reservationService).makeReservation(
                eq(33L),
                eq(3333L),
                eq("poppo"),
                eq(partySize),
                eq(date),
                eq(time));

    }

}