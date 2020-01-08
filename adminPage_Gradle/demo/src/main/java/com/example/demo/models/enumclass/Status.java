package com.example.demo.models.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status{
    REGISTERED(0, "등록", "등록 상태"),
    UNREGISTERED(1, "해지", "해지 상태"),
    WATITNG(2, "검수(대기)", "검수 상태");

    private Integer id;
    private String title;
    private String description;
}