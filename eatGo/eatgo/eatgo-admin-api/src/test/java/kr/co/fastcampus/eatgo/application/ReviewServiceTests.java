package kr.co.fastcampus.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class ReviewServiceTests {
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    
    @Before
    public void setUp(ReviewRepository reviewRepository){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }
    @Test
    public void getReviews(){
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder().description("cool!").build());
        
        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();
        Review review = reviews.get(0);

        assertThat(review.getDescription(), is("cool"));
    }
}