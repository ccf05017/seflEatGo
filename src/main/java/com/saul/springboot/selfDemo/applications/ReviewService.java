package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    public Review addReview(Review resource) {
        // TODO
        // change to real logic
        // now just fake data

        Review review = Review.builder().build();

        return review;
    }
}
