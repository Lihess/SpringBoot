package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.models.entity.Item;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.ItemApiRequest;
import com.example.demo.models.network.response.ItemApiResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                        .name(body.getName())
                        .status(body.getStatus())
                        .title(body.getTitle())
                        .content(body.getContent())
                        .price(body.getPrice())
                        .brandName(body.getBrandName())
                        .registeredAt(LocalDateTime.now())
                        .partner(partnerRepository.getOne(body.getPartnerId()))
                        .build();
        Item newItem = baseRepository.save(item);

        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> optional = baseRepository.findById(id);

        return optional.map(item -> Header.OK(response(item)))
                        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();
        Optional<Item> optional = baseRepository.findById(body.getId());

        return optional.map(item -> {
            item.setName(body.getName()).setStatus(body.getStatus()).setTitle(body.getTitle())
                    .setContent(body.getContent()).setPrice(body.getPrice()).setBrandName(body.getBrandName())
                    .setRegisteredAt(body.getRegisteredAt()).setUnregisteredAt(body.getUnregisteredAt());
            return item;
        }).map(updateItem -> baseRepository.save(updateItem))
        .map(item -> Header.OK(response(item)))
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);

        return optional.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터가 없음"));
    }

    public ItemApiResponse response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                                                        .id(item.getId())
                                                        .status(item.getStatus())
                                                        .name(item.getName())
                                                        .title(item.getTitle())
                                                        .content(item.getContent())
                                                        .price(item.getPrice())
                                                        .brandName(item.getBrandName())
                                                        .registeredAt(item.getRegisteredAt())
                                                        .unregisteredAt(item.getUnregisteredAt())
                                                        .partnerId(item.getPartner().getId())
                                                        .build();
        
        return itemApiResponse;
    }

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = items.stream()
                                                        .map(item -> response(item))
                                                        .collect(Collectors.toList());
        return Header.OK(itemApiResponseList);
    }
}