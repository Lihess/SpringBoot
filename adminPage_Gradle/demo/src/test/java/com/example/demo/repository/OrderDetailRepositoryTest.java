package com.example.demo.repository;

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
        orderDetail.setOrderAt(LocalDateTime.now());
        //orderDetail.setUser(1L);
        //orderDetail.setItemId(1L);

        OrderDetail newOrder = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrder);
    }
}