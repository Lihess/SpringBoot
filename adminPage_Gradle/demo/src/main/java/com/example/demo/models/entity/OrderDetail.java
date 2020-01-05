package com.example.demo.models.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroup", "item"})
//@ToString(exclude = {"user", "item"}) // 상호참조할 경우. 문자열로 두번 바꾸게 되어 오버플로우 발생.
// 되도록 연관관계를 가지는 변수는 배제하자!
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDate arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;
    
    @CreatedBy
    private String createdBy;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @LastModifiedBy // 설정한 AdminServer로 반환됨
    private String updatedBy;

    //@ManyToOne  N : 1, 본인을 기준으로~!
    //private User user;  연관설정을 위해 객체로 지정
   
    //@ManyToOne
    //private Item item;

    // orderDetail : orderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

    // orderDetail : Item : 1 : N
    @ManyToOne
    private Item item;
}