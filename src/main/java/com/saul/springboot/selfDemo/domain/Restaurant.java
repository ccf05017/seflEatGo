package com.saul.springboot.selfDemo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;
    
    @Transient // JAVA 에서는 사용하고, DB 에서는 사용하지 않는 column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemMenu> itemMenus;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getInformation() {

        return this.name + " in " + this.address;
    }

    public void setItemMenus(List<ItemMenu> itemMenus) {

        this.itemMenus = new ArrayList<>(itemMenus);

        // 이게 안되는 이유?
        // 객체의 참조가 전달되기 때문에
        // 아래와 같이 하면 이 함수가 시행된 뒤의 모든 itemMenus 가 영향을 받는다
//        this.itemMenus = itemMenus;
    }

    public void updateRestaurantInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setReviews(List<Review> reviews) {

        this.reviews = new ArrayList<>(reviews);
    }
}