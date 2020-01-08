package com.example.demo.service;

import java.util.List;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.response.UserApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// 앞서 레파지토리를 지정해주고, Service를 등록해주는 과정 또한 줄이기 위함
@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

}