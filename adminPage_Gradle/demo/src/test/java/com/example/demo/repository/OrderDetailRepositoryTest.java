package com.example.demo.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.OrderDetail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailRepositoryTest extends DemoApplicationTests{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        //orderDetail.setOrderGroupId(1L);
        //orderDetail.setItemId(1L);
        orderDetail.setStatus("WATING");
        orderDetail.setArrivalDate(LocalDate.now().plusDays(2)); 
        // 현재에서 이틀 추가
         // 날짜이므로 Date 가 맞음
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(1000000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("Admin");

        OrderDetail newOrder = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrder);
    }
}