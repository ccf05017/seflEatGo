package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Reservation;
import com.saul.springboot.selfDemo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations(Long restaurantId) {

        return reservationRepository.findAllByRestaurantId(restaurantId);
    }

}
