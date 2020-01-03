package com.example.demo.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "item"}) // 상호참조할 경우. 문자열로 두번 바꾸게 되어 오버플로우 발생.
// 되도록 연관관계를 가지는 변수는 배제하자!
public class OrderDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private LocalDateTime orderAt;
   
    @ManyToOne // N : 1, 본인을 기준으로~!
    private User user; // 연관설정을 위해 객체로 지정
   
    @ManyToOne
    private Item item;
}