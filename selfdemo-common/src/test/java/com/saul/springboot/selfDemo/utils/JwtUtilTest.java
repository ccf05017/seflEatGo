package com.saul.springboot.selfDemo.utils;

import io.jsonwebtoken.Claims;
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

        String jwtToken = jwtUtil.createToken(userId, userName, null);

        assertThat(jwtToken).contains(".");
    }

    @Test
    public void getClaims() {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMzLCJ1c2VyTmFtZSI6InBvcHBvIn0.urySBGTtV3UuR45LysoDkvRoX0cASX6dE3a1KLBj0DM";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class)).isEqualTo(33L);
        assertThat(claims.get("userName")).isEqualTo("poppo");

    }

}
