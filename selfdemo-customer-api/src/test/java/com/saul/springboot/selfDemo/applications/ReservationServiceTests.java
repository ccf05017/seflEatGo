package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Reservation;
import com.saul.springboot.selfDemo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTests {

    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void saveReservation() {

        Integer partySize = 30;
        String date = "2019-09-24";
        String time = "17:00";

        given(reservationRepository.save(any())).will(invocation -> {
            return invocation.getArgument(0);
        });

        Reservation reservation = reservationService.makeReservation(
                33L,
                3333L,
                "poppo",
                partySize,
                date,
                time
        );

        assertThat(reservation.getUserId()).isEqualTo(33L);

        verify(reservationRepository).save(any(Reservation.class));

    }

}