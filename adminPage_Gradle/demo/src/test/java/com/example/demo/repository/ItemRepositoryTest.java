package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.DemoApplicationTests;
import com.example.demo.models.entity.Item;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemRepositoryTest extends DemoApplicationTests{
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(1000000);
        item.setContent("삼성");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());
    }
}