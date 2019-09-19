package com.saul.springboot.selfDemo.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Before
    public void setup() {

        String secret = "12345678901234567890123456789012";

        jwtUtil = new JwtUtil(secret);
    }

    @Test
    public void createToken() {

        Long userId = 33L;
        String userName = "poppo";

        String jwtToken = jwtUtil.createToken(userId, userName);

        assertThat(jwtToken).contains(".");
    }

}
