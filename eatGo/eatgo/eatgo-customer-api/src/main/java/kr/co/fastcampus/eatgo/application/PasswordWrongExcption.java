package kr.co.fastcampus.eatgo.application;

public class PasswordWrongExcption extends RuntimeException{
    PasswordWrongExcption(){
        super("Password is wrong");
    }
}
