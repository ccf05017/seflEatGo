package com.saul.springboot.selfDemo.domain;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

//    Reservation save(Reservation reservation);

}
