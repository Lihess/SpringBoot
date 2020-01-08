package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.entity.AdminUser;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.AdminUserApiRequest;
import com.example.demo.models.network.response.AdminUserApiResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                                        .account(body.getAccount())
                                        .password(body.getPassword())
                                        .status(body.getStatus())
                                        .role(body.getRole())
                                        .lastLoginAt(body.getLastLoginAt())
                                        .passwordUpdatedAt(body.getPasswordUpdatedAt())
                                        .loginFailCount(body.getLoginFailCount())
                                        .registeredAt(body.getRegisteredAt())
                                        .build();
        AdminUser newAdminUser = baseRepository.save(adminUser);
        
        return Header.OK(response(newAdminUser));
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setRegisteredAt(body.getRegisteredAt());
                    
                    return adminUser;
                }).map(adminUser -> baseRepository.save(adminUser))
                .map(updateAU -> Header.OK(response(updateAU)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<AdminUserApiResponse>> search(Pageable pageable) {
        Page<AdminUser> adminUsers = baseRepository.findAll(pageable);

        List<AdminUserApiResponse> adminUserApiResponseList = adminUsers.stream()
                                                                        .map(adminUser -> response(adminUser))
                                                                        .collect(Collectors.toList()); 
        return Header.OK(adminUserApiResponseList);
    }

    public AdminUserApiResponse response(AdminUser adminUser){
        AdminUserApiResponse body = AdminUserApiResponse.builder()
                                                        .id(adminUser.getId())
                                                        .account(adminUser.getAccount())
                                                        .password(adminUser.getPassword())
                                                        .status(adminUser.getStatus())
                                                        .role(adminUser.getRole())
                                                        .lastLoginAt(adminUser.getLastLoginAt())
                                                        .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                                                        .loginFailCount(adminUser.getLoginFailCount())
                                                        .registeredAt(adminUser.getRegisteredAt())
                                                        .build();
        
        return body;
    }
  
}