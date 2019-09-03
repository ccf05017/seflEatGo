package com.saul.springboot.selfDemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAllByRestaurantId(long restaurantId);

    List<Review> findAll();
}
