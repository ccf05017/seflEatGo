package com.saul.springboot.selfDemo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemMenu {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;

    private String name;

    @Transient // JAVA 에서만 사용되는 속성값
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
}
