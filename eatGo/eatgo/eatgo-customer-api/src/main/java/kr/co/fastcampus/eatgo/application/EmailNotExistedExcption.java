package kr.co.fastcampus.eatgo.application;

public class EmailNotExistedExcption extends RuntimeException{
    EmailNotExistedExcption(String email) {
        super("Email is not resitered" + email);
    }
}
