package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ReservationService;
import com.saul.springboot.selfDemo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @RequestBody ReservationRequestDto resource,
            Authentication authentication
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        Reservation saved = reservationService.makeReservation(
            claims.get("userId", Long.class),
            restaurantId,
            claims.get("userName", String.class),
            resource.getPartySize(),
            resource.getDate(),
            resource.getTime()
        );

        String url = "/restaurants/"+ restaurantId +"/reservations/" + saved.getId();

        return ResponseEntity.created(new URI(url)).body("{}");

    }
}
