package com.example.demo.ifs;

import com.example.demo.models.network.Header;

// 반드시 작성해야하는 메소드의 명시를 위해
public interface CrudInterface<Req, Res>{
    Header<Res> create(Header<Req> request);
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header delete(Long id);
}