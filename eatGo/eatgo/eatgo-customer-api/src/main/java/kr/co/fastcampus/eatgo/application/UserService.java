package kr.co.fastcampus.eatgo.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.EmailExistedException;
import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(String email, String name, String password){
        Optional<User> existed = userRepository.findByEmail(email);
        
        if(existed.isPresent()){
            throw new EmailExistedException(email);
        }

        // password 암호화
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder().name(name).email(email).password(encodedPassword).level(1L).build();

        userRepository.save(user);
        return null;
    }

	public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                                    .orElseThrow(() -> new EmailNotExistedExcption(email));

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new PasswordWrongExcption();

        return null;
    }
}