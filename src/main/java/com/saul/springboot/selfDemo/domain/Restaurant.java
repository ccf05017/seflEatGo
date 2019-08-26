package com.saul.springboot.selfDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
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

    private String name;
    private String address;
    
    @Transient
    private List<ItemMenu> itemMenus;

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
}