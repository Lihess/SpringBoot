package com.example.demo.repository;

import java.time.LocalDateTime;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.AdminUser;
import com.example.demo.models.enumclass.Status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserRepositoryTest extends DemoApplicationTests{
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser1");
        adminUser.setPassword("AdminUser1");
        adminUser.setStatus(Status.REGISTERED);
        adminUser.setRole("PARTNER");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
    }
}