package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository);
    }

    public void mockRestaurantRepository(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                                        .id(1004L)
                                        .categoryId(1L)
                                        .name("Bob Zip")
                                        .address("Seoul")
                                        .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
    
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        // findById 는 Optional 형태로 객체를 반환하므로, null이 아닌 Optional 객체를 생성하는 of 메소드 이용!
    }

    @Test
    public void getRestaurantsWithExisted(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantsWithNotExisted(){
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
       
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                                            .name("BeRyong")
                                            .categoryId(1L)
                                            .address("Seoul")
                                            .build();

        Restaurant newR = restaurantService.addRestaurant(restaurant);
        assertThat(newR.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant =Restaurant.builder()
                                            .id(1004L)
                                            .name("Bob Zip")
                                            .categoryId(2L)
                                            .address("Seoul")
                                            .build();
        
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        
        restaurantService.updateRestaurant(1004L, "Bo Bar", "Seoul");
        
        assertThat(restaurant.getName(), is("Bo Bar"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }
}