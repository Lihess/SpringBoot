package com.example.demo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import com.example.demo.*;
import com.example.demo.models.entity.User;

    
public class UserRepositoryTest extends DemoApplicationTests{
    @Autowired 
    
    // Dependency Injection. 생성자를 사용하여 객체를 생성하지 않음. 이는 스프링부트가 알아서
    private UserRepository userRepository; // 스프링부트를 하나의 클래스만 생성하여 모든 메소드가 이에 대해 적용됬다는 가
  
   @Test
    public void create(){
        User user = new User(); // 매번 새로 추가해야하는 엔터티이므로.

        user.setAccount("TestUser02");
        user.setEmail("email@maisn.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("test");

        User newUser = userRepository.save(user); // DB에 저장 후, 저장된 엔터티를 반환
        System.out.println("User : " + newUser );
    }
}
    