package com.example.demo.controller.api;

import com.example.demo.controller.CrudController;
import com.example.demo.models.entity.OrderDetail;
import com.example.demo.models.network.request.OrderDetailApiRequest;
import com.example.demo.models.network.response.OrderDetailApiResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail>{
    
}