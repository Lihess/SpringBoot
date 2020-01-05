package com.example.demo.repository;

import java.time.LocalDateTime;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.Partner;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class PartnerRepositoryTest extends DemoApplicationTests{
    @Autowired
    PartnerRepository partnerRepository;

    @Test
    public void create(){
        String name = "Partner1";
        String status = "REGISTED";
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        //partner.setCategoryId(categoryId);
        
        Partner newPartner = partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);

    }
}