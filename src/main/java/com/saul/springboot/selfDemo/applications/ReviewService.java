package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Review;
import com.saul.springboot.selfDemo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review resource) {

        resource.setRestaurantId(restaurantId);
        Review review = this.reviewRepository.save(resource);

        return review;
    }
}
