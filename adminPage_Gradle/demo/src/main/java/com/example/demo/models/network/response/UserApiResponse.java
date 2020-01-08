package com.example.demo.models.network.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.models.enumclass.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 응답과 요청의 데이터가 달라질 수 있음으로 따로따로 관리함.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiResponse{
    private Long id;
    private String account;
    private String password; 
    private Status status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private List<OrderGroupApiResponse> orderGroupApiResponseList;
}