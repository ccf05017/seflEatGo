package com.saul.springboot.selfDemo.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();

        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));
        Assert.assertThat(restaurant.getName(), CoreMatchers.is("Bob zip"));
        Assert.assertThat(restaurant.getAddress(), CoreMatchers.is("Seoul"));
    }

    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();

        Assert.assertThat(restaurant.getInformation(), CoreMatchers.is("Bob zip in Seoul"));
    }
}