package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = regionService.getRegions();

        return regions;
    }

}
