package com.example.demo.models.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

// status의 값을 enum으로 지정하여, 미리 정의된 값 중에서만 지정이 가능하도록.
@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0, "등록", "사용자 등록상태"),
    UNREGISTERED(1, "해지", "사용자 해지상태");

    private Integer id;
    private String title;
    private String description;
}