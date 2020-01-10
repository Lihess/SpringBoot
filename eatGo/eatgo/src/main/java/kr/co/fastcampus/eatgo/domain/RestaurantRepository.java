package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import kr.co.fastcampus.eatgo.domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}