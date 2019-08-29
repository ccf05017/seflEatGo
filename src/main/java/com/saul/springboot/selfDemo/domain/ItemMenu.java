package com.saul.springboot.selfDemo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemMenu {

    @Id
    @GeneratedValue
    private Long Id;

    @Setter
    private Long restaurantId;

//    @Getter
    private String name;
}
