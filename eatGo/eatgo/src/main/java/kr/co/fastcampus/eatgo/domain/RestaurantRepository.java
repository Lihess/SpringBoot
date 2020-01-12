package kr.co.fastcampus.eatgo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.co.fastcampus.eatgo.domain.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
}