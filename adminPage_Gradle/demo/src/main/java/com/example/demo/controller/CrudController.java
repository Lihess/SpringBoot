package com.example.demo.controller;

import java.util.List;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;
import com.example.demo.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
// 추상 클래스를 생성하여 반복적으로 작성하는 과정 감소.
public abstract  class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService; // 상속된 클래스에서만 적용됨

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }

    @GetMapping("")
    public Header<List<Res>> search(@PageableDefault(sort = "id", direction = Direction.DESC, size = 15) Pageable pageable) {
        return baseService.search(pageable);
    }
}