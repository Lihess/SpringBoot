package com.example.demo.controller.api;

import javax.annotation.PostConstruct;

import com.example.demo.controller.CrudController;
import com.example.demo.models.network.request.OrderGroupApiRequest;
import com.example.demo.models.network.response.OrderGroupApiResponse;
import com.example.demo.service.OrderGroupApiLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {
    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @PostConstruct
    public void init(){ this.baseService = orderGroupApiLogicService; }
}