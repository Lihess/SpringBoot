package com.example.demo.controller;
import com.example.demo.controller.model.SearchParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // api 주소 매핑. 현재 localhost:8080/api 와 매핑됨

public class GetController{
    
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMehod와 매핑됨. 
    public String getRequest(){
        return "Hi getMothod";
    }

    @GetMapping("/getParameters") 
    // RequestMapping과 달리 메소드 작성 x
    // localhost:8080/api/getParameters?id=**&password=**와 매핑됨. 
    public String getParameters(@RequestParam String id, @RequestParam String password){
         // @RequestParam(name = "password")와 같이 입력하면 파라미터와 변수의 이름을 다르게 설정할 수 있음
        System.out.println("id : " + id);
        System.out.println("password : " + password);

        return id + password;
    }

    @GetMapping("/getMultiParameters") 
    public SearchParam getMultiPararmeter(SearchParam searchParam){ // 파라미터를 객체로 받음
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
        // 스프링 라이브러리 자체에서 객체를 JSON 객체의 형태로 변환해줌
    }
}