package kr.co.fastcampus.eatgo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.co.fastcampus.eatgo.domain.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    List<Restaurant> findAll();
    List<Restaurant> findAllByAddressContaining(String region);
    Optional<Restaurant> findById(Long id);
	List<Restaurant> findAllByAddressContainingAndCategoryId(String region, Long categoryId);
    // 2개를 이용해서 찾을 경우, 중간에 and여야 함!
}