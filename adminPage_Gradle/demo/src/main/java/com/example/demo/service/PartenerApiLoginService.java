package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.demo.models.entity.Partner;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.PartnerApiRequest;
import com.example.demo.models.network.response.PartnerApiResponse;
import com.example.demo.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PartenerApiLoginService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                                .name(body.getName())
                                .status(body.getStatus())
                                .address(body.getAddress())
                                .callCenter(body.getCallCenter())
                                .partnerNumber(body.getPartnerNumber())
                                .businessNumber(body.getBusinessNumber())
                                .ceoName(body.getCeoName())
                                .registeredAt(LocalDateTime.now())
                                .category(categoryRepository.getOne(body.getCategoryId()))
                                .build();
        Partner newPartner = baseRepository.save(partner);

        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(partner -> {
                    partner.setName(body.getName()).setStatus(body.getStatus())
                            .setAddress(body.getAddress())
                            .setCallCenter(body.getCallCenter())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCeoName(body.getCeoName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setCategory(categoryRepository.getOne(body.getCategoryId()));

                    return partner;
                }).map(partner -> baseRepository.save(partner))
                .map(updatePartner -> Header.OK(response(updatePartner)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id).map(partner -> {
            baseRepository.delete(partner);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner) {
        PartnerApiResponse body = PartnerApiResponse.builder()
                                                    .id(partner.getId())
                                                    .name(partner.getName())
                                                    .status(partner.getStatus())
                                                    .address(partner.getAddress())
                                                    .callCenter(partner.getCallCenter())
                                                    .partnerNumber(partner.getPartnerNumber())
                                                    .businessNumber(partner.getBusinessNumber())
                                                    .ceoName(partner.getCeoName())
                                                    .registeredAt(partner.getRegisteredAt())
                                                    .unregisteredAt(partner.getUnregisteredAt())
                                                    .categoryId(partner.getCategory().getId())
                                                    .build();

        return body;
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<Partner> partners = baseRepository.findAll(pageable);

        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                                                                    .map(partner -> response(partner))
                                                                    .collect(Collectors.toList());
        return Header.OK(partnerApiResponseList);
    }
}