package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.RegionService;
import com.saul.springboot.selfDemo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/regions")
    public ResponseEntity<?> create(
            @RequestBody Region resource
    ) throws URISyntaxException {

        Region region = regionService.addRegion(resource);

        URI uri = new URI("/regions/" + region.getId());

        return ResponseEntity.created(uri).body("{}");
    }

}
