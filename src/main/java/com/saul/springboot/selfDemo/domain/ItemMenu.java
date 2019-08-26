package com.saul.springboot.selfDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemMenu {

    @Id
    @GeneratedValue
    private Long Id;

    private Long restaurantId;

    @Getter
    private String name;
}
