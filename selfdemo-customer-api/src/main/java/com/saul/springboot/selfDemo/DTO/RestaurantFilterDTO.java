package com.saul.springboot.selfDemo.DTO;

import com.saul.springboot.selfDemo.applications.*;
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

        if (this.region != null & this.categoryId != null) {
            return new RestaurantFilterBoth();
        }

        if (this.region != null) {
            return new RestaurantFilterRegion();
        }

        if (this.categoryId != null) {
            return new RestaurantFilterCategoryId();
        }

        return new RestaurantFilterDefault();

    }

}
