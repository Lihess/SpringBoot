package com.example.demo.controller.api;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.UserApiRequest;
import com.example.demo.models.network.response.UserApiResponse;
import com.example.demo.service.UserApiLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse>{
    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // C => POST
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request); // 동작과정을 log로 남김, {} 자리에 request가!
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // R => GET
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // U => PUT
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // D => DELETE
    public Header delete(@PathVariable(name = "id") Long id) {
        return userApiLogicService.delete(id);
    }
}