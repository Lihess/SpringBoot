package kr.co.fastcampus.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @SpyBean // 빈으로 등록
    private RestaurantRepository restaurantRepository;
    
    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;
    
    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurant"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"id\" : 1004")
            ))
            .andExpect(content().string(
                containsString("\"name\" : \"bob zip\"")
            ));
    }

    @Test
    public void detail() throws Exception{
        mvc.perform(get("/restaurant/1004"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"id\" : 1004")
        ))
        .andExpect(content().string(
            containsString("\"name\" : \"bob zip\"")
        ))
        .andExpect(content().string(
            containsString("Kimchi")
        ));

        mvc.perform(get("/restaurant/2020"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"id\" : 2020")
        ))
        .andExpect(content().string(
            containsString("\"name\" : \"Cyber Food\"")
        ));
    }
}