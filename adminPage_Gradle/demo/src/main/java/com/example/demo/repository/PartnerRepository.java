package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.models.entity.Category;
import com.example.demo.models.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
	List<Partner> findByCategory(Category category);
    
}