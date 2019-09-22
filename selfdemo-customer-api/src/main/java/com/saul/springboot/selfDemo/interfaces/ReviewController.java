package com.saul.springboot.selfDemo.interfaces;


import com.saul.springboot.selfDemo.applications.ReviewService;
import com.saul.springboot.selfDemo.domain.Review;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
                Authentication authentication,
                @PathVariable(name = "restaurantId") Long restaurantId,
                @Valid @RequestBody Review resource
            ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        String writer = claims.get("userName", String.class);
        Integer score = resource.getScore();
        String description = resource.getDescription();

        Review review = this.reviewService.addReview(restaurantId, writer, score, description);

        URI uri = new URI("/restaurants/" + restaurantId +
                "/reviews/" + review.getId());

        return ResponseEntity.created(uri).body("{}");
    }
}
