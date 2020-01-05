package com.example.demo.controller.api;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface{

    @Override
    @PostMapping("") // C => POST
    public Header create() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("") // R => GET
    public Header read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @PutMapping("") // U => PUT
    public Header update() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @DeleteMapping("") // D => DELETE
    public Header delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}