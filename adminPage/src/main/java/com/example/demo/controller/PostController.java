package com.example.demo.controller;

import com.example.demo.controller.model.SearchParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
// 클래스가 다르므로 매핑되는 주소가 같아도 됨
public class PostController{
// post 메소드는 html의 form 태그, ajax 검색에서 주로 사용됨. 즉 데이터가 많다!
// post 메소드는 body 필드에 데이터가 들어가게 됨.
    @PostMapping(value = "/postMethod")
    // produce = {""} 로 해당 데이터를 어떤 형식으로 받을건지 지정 가능. 기본은 JSON
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
}