package com.saul.springboot.selfDemo.domain;


import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTests {

    @Test
    public void userDomainCheck() {

        User user = User.builder()
                .level(3)
                .name("poppo")
                .email("poppo@gmail.com")
                .build();

        assertThat(user.isAdmin()).isEqualTo(false);
        assertThat(user.isActive()).isEqualTo(true);

        user.deactivate();

        assertThat(user.isActive()).isEqualTo(false);

    }

}
