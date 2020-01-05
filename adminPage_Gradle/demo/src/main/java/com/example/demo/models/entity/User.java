package com.example.demo.models.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Entity
//@Table(name= "user") 테이블의 이름 명시. 클래스 명과 동일하다면 선언 안해도 됨.
@ToString(exclude = {"orderGroupList"})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql이므로.
    private Long id;
    
    private String account;
    
    private String password;

    private String status;

    private String email;
    
    private String phoneNumber;
    // sql에서는 phone_number과 같이 표기하는 데, java에서의 표기법을 보고 자동으로 sql과 매칭해줌
    
    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
    
    private LocalDateTime createdAt;
    
    private String createdBy;
    
    private LocalDateTime updatedAt;
    
    private String updatedBy;

    // User : orederGroup = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    // fetch 타입과 매핑될 속성 지정
    // lazy : 지연로딩으로 선택한 속성에 대해서만 조인이 일어남 > 지연이 적음으로 추천
    // eager : 즉시 로딩으로 즉시 모든 연관 속성에 대해 조인이 일어남 > 1 : 1일 경우에는 사용
    //private List<OrderDetail> orderDetailList;
}