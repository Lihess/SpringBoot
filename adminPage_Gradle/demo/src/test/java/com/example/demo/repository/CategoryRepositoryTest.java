package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.Category;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryRepositoryTest extends DemoApplicationTests{
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminUser";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read(){
        String type = "COMPUTER";
        Optional<Category> optionalCategory = categoryRepository.findByType(type);
        // optional : 있을수도 없을 수도
        
        optionalCategory.ifPresent(c -> {
           Assert.assertEquals(c.getType(), type);
        });
    }
}