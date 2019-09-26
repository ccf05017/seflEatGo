package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class ReservationServiceTests {

    @Mock
    ReservationRepository reservationRepository;

    ReservationService reservationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getList() {

        Long restaurantId = 33L;

        reservationService.getReservations(restaurantId);

        verify(reservationRepository).findAllByRestaurantId(eq(restaurantId));

    }

}