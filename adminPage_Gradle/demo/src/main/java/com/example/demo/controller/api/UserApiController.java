package com.example.demo.controller.api;

import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.controller.CrudController;
import com.example.demo.models.entity.User;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.UserApiRequest;
import com.example.demo.models.network.response.UserApiResponse;
import com.example.demo.service.UserApiLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User>{
    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init(){ this.baseService = userApiLogicService; }

    // 페이지 처리를 위함
    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Direction.DESC, size = 15) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }
}