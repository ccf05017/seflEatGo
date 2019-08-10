package com.saul.springboot.selfDemo.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));
        Assert.assertThat(restaurant.getName(), CoreMatchers.is("Bob zip"));
        Assert.assertThat(restaurant.getAddress(), CoreMatchers.is("Seoul"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Assert.assertThat(restaurant.getInformation(), CoreMatchers.is("Bob zip in Seoul"));
    }
}