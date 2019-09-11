package com.saul.springboot.selfDemo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContaining(String region);

    List<Restaurant> findAllByCategoryId(Long categoryId);

    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, Long categoryId);

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);
}