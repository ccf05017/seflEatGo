package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Region;
import com.saul.springboot.selfDemo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {

        return regionRepository.findAll();
    }

    public Region addRegion(Region resource) {

        return regionRepository.save(resource);
    }

}
