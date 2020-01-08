package com.example.demo.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.demo.models.entity.OrderDetail;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.OrderDetailApiRequest;
import com.example.demo.models.network.response.OrderDetailApiResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                                            .status(body.getStatus())
                                            .arrivalDate(body.getArrivalDate())
                                            .quantity(body.getQuantity())
                                            .totalPrice(body.getTotalPrice())
                                            .item(itemRepository.getOne(body.getItemId()))
                                            .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                                            .build();

        return Header.OK(response(orderDetail));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(orderDetail -> {
                    orderDetail.setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setItem(itemRepository.getOne(body.getItemId()))
                            .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()));
                    
                    return orderDetail;
                }).map(orderDetail -> baseRepository.save(orderDetail))
                .map(updatedOD -> Header.OK(response(updatedOD)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
        Page<OrderDetail> orderDetails = baseRepository.findAll(pageable);

        List<OrderDetailApiResponse> orderDetailApiResponseList = orderDetails.stream()
                                                                        .map(orderDetail -> response(orderDetail))
                                                                        .collect(Collectors.toList());
                    
        return Header.OK(orderDetailApiResponseList);
    }
    
    public OrderDetailApiResponse response(OrderDetail orderDetail){
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                                                            .id(orderDetail.getId())
                                                            .status(orderDetail.getStatus())
                                                            .arrivalDate(orderDetail.getArrivalDate())
                                                            .quantity(orderDetail.getQuantity())
                                                            .totalPrice(orderDetail.getTotalPrice())
                                                            .itemId(orderDetail.getItem().getId())
                                                            .orderGroupId(orderDetail.getOrderGroup().getId())
                                                            .build();
        
        return body;
    }
}