package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;

@ControllerAdvice // 각종 예외를 처리하기 위한 어드바이스로 지정.
public class RestaurantErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException.class)
    // 예외 처리기 등록
    public String handleNotFount(){
        return "{}";
    }
}