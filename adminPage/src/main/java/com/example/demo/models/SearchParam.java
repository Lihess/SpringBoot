package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class SearchParam{
    private String account;
    private String email;
    private int page;
}