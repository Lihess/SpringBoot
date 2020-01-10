package kr.co.fastcampus.eatgo.interfaces;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.DemoApplicationTests;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class RestaurantControllerTest extends DemoApplicationTests{
    @Autowired
    private MockMvc mvc;

    @SpyBean // 빈으로 등록
    private RestaurantRepository restaurantRepository;
    
    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurant"))
            .andExpect(status().isOk());
    }
}