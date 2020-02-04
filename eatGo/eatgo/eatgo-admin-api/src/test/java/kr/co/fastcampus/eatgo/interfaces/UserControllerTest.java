package kr.co.fastcampus.eatgo.interfaces;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Before;    
    
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mvc;
 
    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder().email("test1@na.cc").name("test").level(1L).build());
        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
            .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception { // 회원가입이랑 다름
        String email = "asdsad@asd.com";
        String name = "test";

        User user = User.builder().email(email).name(name).build();

        given(userService.addUser(email, name)).willReturn(user);
        
        mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"asdsad@asd.com\",\"name\":\"test\"}"))
            .andExpect(status().isCreated());

        verify(userService).addUser(email, name);
    }

    @Test
    public void update() throws Exception { // 회원가입이랑 다름
        mvc.perform(patch("/users/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"asdsad@asd.com\",\"name\":\"test\",\"level\":100}"))
            .andExpect(status().isOk());

        Long id = 1004L;
        String email = "asdsad@asd.com";
        String name = "test";
        Long level = 100L;

        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/user/1004"))
            .andExpect(status().isOk());

        verify(userService).deactiveUser(1004L);
    }

}
    