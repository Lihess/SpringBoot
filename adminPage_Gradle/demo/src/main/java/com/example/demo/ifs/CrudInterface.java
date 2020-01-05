package com.example.demo.ifs;

import com.example.demo.models.network.Header;

// 반드시 작성해야하는 메소드의 명시를 위해
public interface CrudInterface{
    Header create();
    Header read(Long id);
    Header update();
    Header delete(Long id);
}