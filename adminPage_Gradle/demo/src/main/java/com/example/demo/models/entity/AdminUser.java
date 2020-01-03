package com.example.demo.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Entity
//@Table(name= "user") 테이블의 이름 명시. 클래스 명과 동일하다면 선언 안해도 됨.
public class AdminUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql이므로.
    private Long id;
    
    private String account;
    
    private String password;

    private String status;

    private String role;
    
    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdatedAt;

    private int loginFailCount;
    
    private LocalDateTime registeredAt;

    private LocalDateTime unregistered;

    private LocalDateTime createdAt;
    
    private String createdBy;
    
    private LocalDateTime updatedAt;
    
    private String updatedBy;
}