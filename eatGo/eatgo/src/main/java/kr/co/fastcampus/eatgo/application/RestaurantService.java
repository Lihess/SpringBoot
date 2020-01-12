package kr.co.fastcampus.eatgo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurant(Long id){
        return restaurantRepository.findById(id);
    }

    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Restaurant restaurant = restaurants.get(0);
        
        assertThat(restaurant.getId(), is(1004L));
    }
}