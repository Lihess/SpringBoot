package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

	public User addUser(String email, String name) {
        User user = User.builder().email(email).name(name).build();
        
        return userRepository.save(user);
    }

	public User updateUser(Long id, String email, String name, Long level) {
        User user = userRepository.findById(id).orElse(null);
        
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setLevel(level);
        
        return user;
	}

	public User deactiveUser(long id) {
        User user = userRepository.findById(id).orElse(null);

        user.deativate();
        return user;
	}
}