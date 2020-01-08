package com.example.demo.models.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.models.entity.OrderGroup.OrderGroupBuilder;
import com.example.demo.models.enumclass.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder// User.bulider().account(accuont).status(status).bulid() 와 같이 사용하여 객체생성! 이럴 경우, 생성자가 많아지는 문제를 해결할 수 있다.
@Accessors(chain = true) // user.setAccut().setStatus()..와 같이 한번에 데이터 변경 가능
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

    @Enumerated(EnumType.STRING)
    private Status status;

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

    // orderDetail : Item : N : 1
    @ManyToOne
    private Item item;
}