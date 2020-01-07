package com.example.demo.controller.api;

import javax.annotation.PostConstruct;

import com.example.demo.controller.CrudController;
import com.example.demo.models.entity.User;
import com.example.demo.models.network.request.UserApiRequest;
import com.example.demo.models.network.response.UserApiResponse;
import com.example.demo.service.UserApiLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User>{
    //@Autowired
    //private UserApiLogicService userApiLogicService;

    //@PostConstruct
    //public void init(){ this.baseService = userApiLogicService; }
}