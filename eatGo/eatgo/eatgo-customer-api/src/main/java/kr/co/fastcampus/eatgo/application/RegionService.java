package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    @Autowired
	public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

	public List<Region> getRegions() {
		List<Region> regions = regionRepository.findAll();
        return regions;
	}
}