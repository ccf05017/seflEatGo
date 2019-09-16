package com.saul.springboot.selfDemo.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSearchDto {

    private String region;
    private Long categoryId;

    public String isNullString(String target) {

        if (target != null) {
            return target;
        }

        return "";
    }

}
