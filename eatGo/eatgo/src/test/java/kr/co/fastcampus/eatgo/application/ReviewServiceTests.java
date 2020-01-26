package kr.co.fastcampus.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;

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
    public void addReview(){
        Review review = Review.builder()
                                .name("JOKER")
                                .score(3)
                                .description("Mat-is-da")
                                .build();
        
        reviewService.addReview(1004L ,review);

        verify(reviewRepository).save(any());
    }
}