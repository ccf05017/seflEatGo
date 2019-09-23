package com.saul.springboot.selfDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotNull
    private Integer level;

    private String password;

    private Long restaurantId;

    public boolean isAdmin() {

        return level >= 100;
    }

    public void setInfo(String name, String email, Integer level) {
        this.name = name;
        this.email = email;
        this.level = level;
    }

    public void setRestaurantId(Long restaurantId) {

        this.restaurantId = restaurantId;
        this.level = 50;
    }

    public boolean isActive() {

        return this.level > 0;
    }

    public boolean isRestaurantOwner() {

        return this.level == 50;
    }

    public void deactivate() {

        this.level = 0;
    }

    public String getAccessToken() {

        if (this.password == null) {
            return "";
        }

        return this.password.substring(0, 10);
    }
}
