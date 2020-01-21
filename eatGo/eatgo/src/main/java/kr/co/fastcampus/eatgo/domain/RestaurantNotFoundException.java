package kr.co.fastcampus.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException{
    private Long id;

    public RestaurantNotFoundException(Long id){
        super("Could not find restaurant " + id);
    }
}
