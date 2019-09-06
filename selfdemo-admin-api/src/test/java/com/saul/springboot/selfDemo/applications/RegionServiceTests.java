package com.saul.springboot.selfDemo.applications;


import com.saul.springboot.selfDemo.domain.Region;
import com.saul.springboot.selfDemo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RegionServiceTests {

    RegionService regionService;

    @Mock
    RegionRepository regionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions() {
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder().name("서울").build());

        given(regionRepository.findAll()).willReturn(mockRegions);

        List<Region> regions = regionService.getRegions();

        assertThat(regions.get(0).getName()).isEqualTo("서울");
    }

    @Test
    public void addRegion() {
        Region mockRegion = Region.builder().id(1L).name("서울").build();
        given(regionRepository.save(any())).willReturn(mockRegion);

        Region region = Region.builder().name("서울").build();

        Region saved = regionService.addRegion(region);

        assertThat(saved.getId()).isEqualTo(1L);
    }

}
