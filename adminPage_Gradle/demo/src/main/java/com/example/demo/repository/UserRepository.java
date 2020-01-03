package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.demo.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    //Optional<User> findByAccount(String account);
    // select * form user where account = ? << test3, test4
    // 위와 같이 다른 속성을 이용하여 검색하는 쿼리 메소드

    //Optional<User> findByAccountAndEmail(String account, String email);
    // 위와 같이 두가지의 속성을 이용하여서도 검색가능!
}
