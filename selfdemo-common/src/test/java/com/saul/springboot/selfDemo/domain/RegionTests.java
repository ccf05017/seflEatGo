package com.saul.springboot.selfDemo.domain;


import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RegionTests {

    @Test
    public void creation() {

        Region region = Region.builder().name("서울").build();

        assertThat(region.getName()).isEqualTo("서울");

    }

}
