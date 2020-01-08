package com.example.demo.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.models.enumclass.Status;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder // User.bulider().account(accuont).status(status).bulid() 와 같이 사용하여 객체생성! 이럴 경우, 생성자가 많아지는 문제를 해결할 수 있다.
@Accessors(chain = true) // user.setAccut().setStatus()..와 같이 한번에 데이터 변경 가능
@Data
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Entity
//@Table(name= "user") 테이블의 이름 명시. 클래스 명과 동일하다면 선언 안해도 됨.
@EntityListeners(AuditingEntityListener.class) // 해당 리스너에 의해서 createdBy 등의 어노테이션에 대해 적용됨
public class AdminUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql이므로.
    private Long id;
    
    private String account;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String role;
    
    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdatedAt;

    private int loginFailCount;
    
    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    
    @CreatedBy
    private String createdBy;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @LastModifiedBy // 설정한 AdminServer로 반환됨
    private String updatedBy;
}