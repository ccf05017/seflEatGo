package com.saul.springboot.selfDemo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemMenuRepository extends CrudRepository<ItemMenu, Long> {
    List<ItemMenu> findAllByRestaurantId(Long restaurantId);
}
