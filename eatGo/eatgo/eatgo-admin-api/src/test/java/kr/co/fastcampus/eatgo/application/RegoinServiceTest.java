package kr.co.fastcampus.eatgo.application;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

    
    
public class RegoinServiceTest {
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp(RegionRepository regionRepository){
        MockitoAnnotations.initMocks(this);

        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder().name("Seoul").build());
        
        given(regionService.getRegions()).willReturn(mockRegions);


        List<Region> regions = regionService.getRegions();
        Region region = regions.get(0);

        assertThat(region.getName(), is("Seoul"));
    }

    @Test
    public void addRegion(){
        Region region = regionService.addRegion("Seoul");
        
        verify(regionRepository).save(any());
        
        assertThat(region.getName(), is("Seoul"));
    }
        
}
    