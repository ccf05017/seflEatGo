package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.DTO.RestaurantFilterDTO;
import com.saul.springboot.selfDemo.domain.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantFilterType {

    List<Restaurant> getRestaurantsByFilter(RestaurantFilterDTO filterDTO);

}
