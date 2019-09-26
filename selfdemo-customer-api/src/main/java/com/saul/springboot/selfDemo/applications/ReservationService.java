package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Reservation;
import com.saul.springboot.selfDemo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {

        this.reservationRepository = reservationRepository;
    }

    public Reservation makeReservation(Long userId, Long restaurantId, String userName,
                                       Integer partySize, String date, String time) {

        Reservation reservation = Reservation.builder()
                .userId(userId)
                .restaurantId(restaurantId)
                .userName(userName)
                .partySize(partySize)
                .date(date)
                .time(time)
                .build();

        return reservationRepository.save(reservation);
    }
}
