package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }

    public void mockRestaurantRepository(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                                        .id(1004L)
                                        .name("Bob Zip")
                                        .address("Seoul")
                                        .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
    
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        // findById 는 Optional 형태로 객체를 반환하므로, null이 아닌 Optional 객체를 생성하는 of 메소드 이용!
    }

    public void mockMenuItemRepository(){
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    public void mockReviewRepository(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                            .name("BeRyong")
                            .score(1)
                            .description("Bad")
                            .build());

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    @Test
    public void getRestaurantsWithExisted(){
        List<Restaurant> restaurants = restaurantService.getRestaurants("서울");
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
        
        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));
        
        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName()).isEqualTo("Kimchi");

        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription()).isEqualTo("Bad");
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
                                            .address("Seoul")
                                            .build();
        
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        
        restaurantService.updateRestaurant(1004L, "Bo Bar", "Seoul");
        
        assertThat(restaurant.getName(), is("Bo Bar"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }
}