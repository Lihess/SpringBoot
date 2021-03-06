package com.example.demo.models.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.models.enumclass.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse{
    private Long id;
    private Status status;
    private String name;
    private String title;
    private String content;
    private BigDecimal price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private Long partnerId;
}