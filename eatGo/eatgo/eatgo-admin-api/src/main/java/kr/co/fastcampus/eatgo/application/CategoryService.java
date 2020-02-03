package kr.co.fastcampus.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;

@Service
public class CategoryService{
    private CategoryRepository categoryRepository;

    @Autowired
	public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

	public List<Category> getCategories() {
		List<Category> regions = categoryRepository.findAll();
        return regions;
	}

	public Category addRegion(String name) {
        Category region = Category.builder().name(name).build();
        
        categoryRepository.save(region);
        
        return region;
	}
}