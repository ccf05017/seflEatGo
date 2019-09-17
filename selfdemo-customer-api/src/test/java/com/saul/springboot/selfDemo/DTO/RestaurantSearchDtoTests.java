package com.saul.springboot.selfDemo.DTO;


import com.saul.springboot.selfDemo.interfaces.RestaurantSearchDto;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RestaurantSearchDtoTests {

    @Test
    public void isNullNullTest() {

        String region = "";
        RestaurantSearchDto restaurantSearchDto = RestaurantSearchDto.builder().build();

        assertThat(restaurantSearchDto.isNullString(region)).isEqualTo("");

    }

    @Test
    public void isNullNotNullTest() {

        String region = "서울";
        RestaurantSearchDto restaurantSearchDto = RestaurantSearchDto.builder().region(region).build();

        assertThat(restaurantSearchDto.isNullString(region)).isEqualTo("서울");

    }

    @Test
    public void isNullNotNullAndNullTest() {

        String region = "서울";
        String category = "";
        RestaurantSearchDto restaurantSearchDto = RestaurantSearchDto.builder().region(region).build();

        assertThat(restaurantSearchDto.isNullString(region)).isEqualTo("서울");
        assertThat(restaurantSearchDto.isNullString(category)).isEqualTo("");

    }

}
