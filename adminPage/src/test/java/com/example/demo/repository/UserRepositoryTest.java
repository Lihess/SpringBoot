package com.example.demo.repository;

import java.time.LocalDateTime;
import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// test를 위한 공간
@SpringBootTest
public class UserRepositoryTest extends DemoApplicationTests { 
   
    @Autowired 
    @Qualifier("userRepository")
    // Dependency Injection. 생성자를 사용하여 객체를 생성하지 않음. 이는 스프링부트가 알아서
    private UserRepository userRepository; // 스프링부트를 하나의 클래스만 생성하여 모든 메소드가 이에 대해 적용됬다는 가정 하에 적용함
  
    @Test
    public void create(){
        User user = new User(); // 매번 새로 추가해야하는 엔터티이므로.

        user.setAccount("TestUser01");
        user.setEmail("email@maisn.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user); // DB에 저장 후, 저장된 엔터티를 반환
        System.out.println("newUser : " + newUser);
    }
    public void read(){}
    public void update(){}
    public void delete(){}
}