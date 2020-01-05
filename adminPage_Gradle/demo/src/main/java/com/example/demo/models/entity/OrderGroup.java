package com.example.demo.models.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "orderDetailList"})
public class OrderGroup{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; // 일관 or 개별

    private String revAddress;

    private String revName;

    private String paymentType; //카드 or 현금
    
    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;
    
    private String createdBy;
    
    private LocalDateTime updatedAt;
    
    private String updatedBy;

    // orderGroup : User = n : 1
    @ManyToOne // 유저에서 설정한 매핑 변수와 이름이 동일해야 함.
    private User user;

    // orderGroup : orderDetail = 1 : n
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}

