package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.entity.Category;
import com.example.demo.models.network.Header;
import com.example.demo.models.network.request.CategoryApiRequest;
import com.example.demo.models.network.response.CategoryApiResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                                    .type(body.getType())
                                    .title(body.getTitle())
                                    .build();

        Category newCategory = baseRepository.save(category);

        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(category -> {
                    category.setType(body.getType())
                            .setTitle(body.getTitle());
                
                    return category;
                }).map(category -> baseRepository.save(category))
                .map(updateCategory -> Header.OK(response(updateCategory)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<CategoryApiResponse>> search(Pageable pageable) {
        Page<Category> categorys = baseRepository.findAll(pageable);

        List<CategoryApiResponse> categoryApiResponseList = categorys.stream()
                                                                    .map(category -> response(category))
                                                                    .collect(Collectors.toList());
        return Header.OK(categoryApiResponseList);
    }
    
    public CategoryApiResponse response(Category category){
        CategoryApiResponse body = CategoryApiResponse.builder()
                                                        .id(category.getId())
                                                        .type(category.getType())
                                                        .title(category.getTitle())
                                                        .build();

        return body;
    }
}