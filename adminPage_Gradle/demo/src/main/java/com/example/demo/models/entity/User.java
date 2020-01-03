package com.example.demo.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Entity
//@Table(name= "user") 테이블의 이름 명시. 클래스 명과 동일하다면 선언 안해도 됨.
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql이므로.
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;
    // sql에서는 phone_number과 같이 표기하는 데, java에서늬 표기법을 보고 자동으로 sql과 매칭해줌
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}