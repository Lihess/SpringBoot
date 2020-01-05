package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.User;

import org.junit.Test; 
import org.junit.Assert;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryTest extends DemoApplicationTests{
    @Autowired // Dependency Injection. 생성자를 사용하여 객체를 생성하지 않음. 이는 스프링부트가 알아서
    private UserRepository userRepository; // 스프링부트를 하나의 클래스만 생성하여 모든 메소드가 이에 대해 적용됬다는 가
  
   @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test@gmail.com";
        String phoneNumber = "010-1111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User(); // 매번 새로 추가해야하는 엔터티이므로.

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user); // DB에 저장 후, 저장된 엔터티를 반환
        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        
        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("---------- 주문 묶음 -------------");
            System.out.println("수령인 : " + orderGroup.getRevName());

            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("주문인 상태 : " + orderDetail.getStatus());
                System.out.println("주문상품 : " + orderDetail.getItem().getName());
            });
        });
        
        Assert.assertNotNull(user);
    
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        
        user.ifPresent(selectUser -> { 
            selectUser.setAccount("PPPPP");
            selectUser.setEmail("email@did.com");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("admin");

            userRepository.save(selectUser);
            // 해당 아이디가 존재한다면 UPDATE 진행. selectUser의 ID는 변경불가하므로, 다른 id로 지정한다면 지정된 id의 엔터티가 업데이트됨.
        });
    }

    @Test
    @Transactional // 실행을 시키더라도 마지막에 롤백시켜서 데이터, 테스트할 때 유용하다!
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); // 유저가 존재함을 보증함.

        user.ifPresent(selectUser -> { 
           userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);
        Assert.assertFalse(deleteUser.isPresent());
    }
}
    