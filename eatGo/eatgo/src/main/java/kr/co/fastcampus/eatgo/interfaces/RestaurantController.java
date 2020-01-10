package kr.co.fastcampus.eatgo.interfaces;

import java.util.ArrayList;
import java.util.List;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController{
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantRepository.findById(id);

        return restaurant;
    }
}