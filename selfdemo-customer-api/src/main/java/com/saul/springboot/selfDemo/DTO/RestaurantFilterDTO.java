package com.saul.springboot.selfDemo.DTO;

import com.saul.springboot.selfDemo.applications.RestaurantFilterDefault;
import com.saul.springboot.selfDemo.applications.RestaurantFilterType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantFilterDTO {

    private String region;
    private Long categoryId;

    private RestaurantFilterType restaurantFilterType;

    public RestaurantFilterType getRestaurantFilterType() {

//        if (this.region != null & this.categoryId != null) {
//            return new RestaurantFilterBoth();
//        }
//
//        if (this.region != null) {
//            restaurants = restaurantRepository.findAllByAddressContaining(region);
//        }
//
//        if (this.categoryId != null) {
//            restaurants = restaurantRepository.findAllByCategoryId(categoryId);
//        }

        return new RestaurantFilterDefault();

    }

}
