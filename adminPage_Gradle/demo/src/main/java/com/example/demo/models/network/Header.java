// 패킷을 통해 보내는 정보에 해당함
package com.example.demo.models.network;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {
    // 공통부분
    // api 통신시간
    private LocalDateTime transactionTime;
    // api 응답코드
    private String resultCode;
    // api 부가 설명
    private String description;

    // 개별적인 데이터
    private T data; 

    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK").build();
    }

    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK").data(data).build();
    }

    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description(description).build();
    }
}
