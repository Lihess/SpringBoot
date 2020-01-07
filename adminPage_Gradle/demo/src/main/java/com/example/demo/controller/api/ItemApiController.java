package com.example.demo.controller.api;

import javax.annotation.PostConstruct;

import com.example.demo.controller.CrudController;
import com.example.demo.ifs.CrudInterface;
import com.example.demo.models.entity.Item;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.ItemApiRequest;
import com.example.demo.models.network.response.ItemApiResponse;
import com.example.demo.service.ItemApiLogicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log 사용을 위해
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

    //@Autowired
    //private ItemApiLogicService itemApiLogicService;
    
    //@PostConstruct
    //public void init(){ this.baseService = itemApiLogicService; }

    // -- CrudController을 상속받아서 밑에 다 필요가 없다~!! --

    //@Override
    //@PostMapping("")
    //public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) { //RequestBody : json > string
    //    log.info("{}", request);
    //    return itemApiLogicService.create(request);
    //}

    //@Override
    //@GetMapping("{id}")
    //public Header<ItemApiResponse> read(@PathVariable(name = "id") Long id) {
    //    return itemApiLogicService.read(id);
    //}

    //@Override
    //@PutMapping("")
    //public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
    //    return itemApiLogicService.update(request);
    //}

    //@Override
    //@DeleteMapping("{id}")
    //public Header delete(@PathVariable(name = "id") Long id) {
    //    // TODO Auto-generated method stub
    //    return itemApiLogicService.delete(id);
    //}
}