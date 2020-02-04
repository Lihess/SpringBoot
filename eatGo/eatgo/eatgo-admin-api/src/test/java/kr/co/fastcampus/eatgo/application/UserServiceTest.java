package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
  
    
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        userService = new UserService(this.userRepository);
    }

    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("test1@na.cc").name("test").level(1L).build());

        given(userRepository.findAll()).willReturn(mockUsers);
        
        List<User> users = userService.getUsers();
        User user = users.get(0);

        assertThat(user.getName(), is("test"));
    }

    @Test
    public void addUser(){
        String email = "asdsad@asd.com";
        String name = "test";

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser(){
        Long id = 1004L;
        String email = "asdsad@asd.com";
        String name = "test";
        Long level = 100L;

        User mockUser = User.builder().id(id).email(email).name("Admin").level(level).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
        
        User user = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(id);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void deactiveUser() {
        Long id = 1004L;
        String email = "asdsad@asd.com";
        String name = "test";
        Long level = 100L;

        User mockUser = User.builder().id(id).email(email).name(name).level(level).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
        
        User user = userService.deactiveUser(1004L);

        verify(userRepository).findById(1004L);

        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(false));
	}
}
    