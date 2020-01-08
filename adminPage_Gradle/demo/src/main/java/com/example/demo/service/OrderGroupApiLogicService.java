package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.entity.OrderGroup;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.OrderGroupApiRequest;
import com.example.demo.models.network.response.OrderGroupApiResponse;
import com.example.demo.repository.OrderGroupRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                                            .status(body.getStatus())
                                            .orderType(body.getOrderType())
                                            .revAddress(body.getRevAddress())
                                            .revName(body.getRevName())
                                            .paymentType(body.getPaymentType())
                                            .totalPrice(body.getTotalPrice())
                                            .totalQuantity(body.getTotalQuantity())
                                            .orderAt(LocalDateTime.now())
                                            .user(userRepository.getOne(body.getUserId()))
                                            .build();
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        Optional<OrderGroup> optional = baseRepository.findById(id);

        return optional.map(this::response).orElseGet(() -> Header.ERROR("데이터가 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        return baseRepository.findById(orderGroupApiRequest.getId()).map(orderGroup -> {
            orderGroup.setStatus(orderGroupApiRequest.getStatus()).setOrderType(orderGroupApiRequest.getOrderType())
                    .setRevAddress(orderGroupApiRequest.getRevAddress()).setRevName(orderGroupApiRequest.getRevName())
                    .setPaymentType(orderGroupApiRequest.getPaymentType())
                    .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                    .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                    .setOrderAt(orderGroupApiRequest.getOrderAt())
                    .setUser(userRepository.getOne(orderGroupApiRequest.getId()));

                return orderGroup;
            })
            .map(orderGroup -> baseRepository.save(orderGroup))
            .map(updataOG -> response(updataOG))
            .orElseGet(() -> Header.ERROR("데이터가 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터가 없음"));
    }

    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                                                          .id(orderGroup.getId())
                                                          .status(orderGroup.getStatus())
                                                          .orderType(orderGroup.getOrderType())
                                                          .revAddress(orderGroup.getRevAddress())
                                                          .revName(orderGroup.getRevName())
                                                          .paymentType(orderGroup.getPaymentType())
                                                          .totalPrice(orderGroup.getTotalPrice())
                                                          .totalQuantity(orderGroup.getTotalQuantity())
                                                          .orderAt(orderGroup.getOrderAt())
                                                          .arrivalDate(orderGroup.getArrivalDate())
                                                          .userId(orderGroup.getUser().getId())
                                                          .build();
        return Header.OK(body);
    }
    
}