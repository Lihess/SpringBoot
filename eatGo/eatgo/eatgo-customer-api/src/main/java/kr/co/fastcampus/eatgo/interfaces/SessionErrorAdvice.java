package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.fastcampus.eatgo.application.EmailNotExistedExcption;
import kr.co.fastcampus.eatgo.application.PasswordWrongExcption;

@ControllerAdvice
public class SessionErrorAdvice{
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordWrongExcption.class)
    // 예외 처리기 등록
    public String handlePasswordWrong(){
        return "{}";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotExistedExcption.class)
    // 예외 처리기 등록
    public String handleEmailNotExistedException(){
        return "{}";
    }
}