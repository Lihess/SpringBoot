package kr.co.fastcampus.eatgo.application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.EmailExistedException;
import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }
        
    @Test
    public void registerUser() {
        String email = "test@example.com";
        String name = "Tester";
        String password = "test";
        
        userService.registerUser(email, name, password);
   
        verify(userRepository).save(any());
    }

    @Test(expected = EmailExistedException.class)
    public void registerUserWithExistedEmail() {

        String email = "test@example.com";
        String name = "Tester";
        String password = "test";
        
        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        userService.registerUser(email, name, password);
   
        verify(userRepository, never()).save(any());
    }

    
    @Test
    public void authenticateWithValidAttributes(){
        String email = "test@example.com";
        String password = "test";
        
        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        User user = userService.authenticate(email, password);
        // 성공할 경우 user를 반환하고, 아니면 x

        assertThat(user.getEmail(), is(email));
    }

    @Test(expected = EmailNotExistedExcption.class)
    public void authenticateWithInvalidAttributes(){
        String email = "x@example.com";
        String password = "test";
        
        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        userService.authenticate(email, password);
        // 없으므로 반환불가, 에러 호출
    }

    @Test(expected = PasswordWrongExcption.class)
    public void authenticateWithWrongPassword(){
        String email = "test@example.com";
        String password = "x";
        
        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        User user = userService.authenticate(email, password);
        // 성공할 경우 user를 반환하고, 아니면 x

        assertThat(user.getEmail(), is(email));
    }
}
    