package com.saul.springboot.selfDemo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
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

    public boolean isAdmin() {

        return level >= 100;
    }

    public void setInfo(String name, String email, Integer level) {
        this.name = name;
        this.email = email;
        this.level = level;
    }

    public boolean isActive() {

        return this.level > 0;
    }

    public void deactivate() {

        this.level = 0;
    }
}
