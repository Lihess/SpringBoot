package kr.co.fastcampus.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean // 가짜 빈으로 등록함. DB에 영향 x, 수많은 의존성을 고려하지 않아도 됨.
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                                    .id(1004L)
                                    .name("Bob Zip")
                                    .address("Seoul")
                                    .build()); // 가짜 객체 생성.
        
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        // 반환되는 데이터. JSON 형태로 출력되도록 함.
        mvc.perform(get("/restaurants"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"id\":1004")
            ))
            .andExpect(content().string(
                containsString("\"name\":\"Bob Zip\"")
            ));
    }

    @Test
    public void detailWithExisted() throws Exception{
        Restaurant restaurant =  Restaurant.builder()
                                            .id(1004L)
                                            .name("Bob Zip")
                                            .address("Seoul")
                                            .build();
        restaurant.addMenuItem(MenuItem.builder().name("Kimchi").build());
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"id\":1004")
        ))
        .andExpect(content().string(
            containsString("\"name\":\"Bob Zip\"")
        ))
        .andExpect(content().string(
            containsString("Kimchi")
        ));
    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        
        mvc.perform(get("/restaurants/404"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValidData() throws Exception{
        mvc.perform(post("/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"BeRyong\",\"addresdd\":\"Seoul\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/restaurants/1234"))
            .andExpect(content().string("{}"));
    
        // 생성되었는지 확인하는 과정.
        verify(restaurantService).addRestaurant(any()); 
    }

    @Test
    public void createWithInvalidData() throws Exception{
        mvc.perform(post("/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"\",\"addresdd\":\"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("\"name\":\"Bo Bar\",\"address\":\"Seoul\""))
            .andExpect(status().isOk());
        
            verify(restaurantService).updateRestaurant(1004L, "Bo Bar", "Seoul");
    }

    @Test
    public void updateWithInValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("\"name\":\"\",\"address\":\"\""))
            .andExpect(status().isBadRequest());
    }
}