package com.saul.springboot.selfDemo.interfaces;


import com.saul.springboot.selfDemo.applications.ReviewService;
import com.saul.springboot.selfDemo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
                @PathVariable(name = "restaurantId") Long restaurantId,
                @RequestBody Review resource
            ) throws URISyntaxException {

        Review review = this.reviewService.addReview(resource);

        URI uri = new URI("/restaurants/" + restaurantId + "/reviews/" + review.getId());

        return ResponseEntity.created(uri).body("{}");
    }
}
