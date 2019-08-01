package com.saul.springboot.selfDemo.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestaurantTests {

    // Test용 restaurant object
    Restaurant restaurant = new Restaurant("Nandos", "Seoul");

    @Test
    public void create() {
        Restaurant restaurant = new Restaurant("Nandos", "Seoul");
    }

    @Test
    public void getName() {
        assertThat(this.restaurant.getName(), is("Nandos"));
    }

    @Test
    public void getAddress() {
        assertThat(this.restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void getInfo() {
        assertThat(this.restaurant.getInfo(), is("Nandos in Seoul"));
    }
}
