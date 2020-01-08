package com.example.demo.models.network.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiRequest{
    private Long id;
    private String status;
    private LocalDate arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long itemId;
    private Long orderGroupId;
}