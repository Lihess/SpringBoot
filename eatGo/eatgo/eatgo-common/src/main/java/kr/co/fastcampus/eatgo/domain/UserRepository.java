package kr.co.fastcampus.eatgo.domain;

import java.util.List;
import java.util.Optional;

import kr.co.fastcampus.eatgo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();

	Optional<User> findByEmail(String email);
}