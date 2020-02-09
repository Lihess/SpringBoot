package kr.co.fastcampus.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.application.EmailNotExistedExcption;
import kr.co.fastcampus.eatgo.application.PasswordWrongExcption;
import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.utils.JwtUtil;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void createWithValidAttributes() throws Exception {
        Long id = 1004L;
        String name = "John";

        User mockUser = User.builder().id(id).name(name).build();
        
        mvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test@example.com\",\"password\":\"test\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/session"))
            .andExpect(content().string(containsString("{\"accessToken\":\"ACCESSTOKEN\"}")))
            .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq("test@example.com"),eq("test"));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("x@example.com", "test")).willThrow(EmailNotExistedExcption .class);
        
        mvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"x@example.com\",\"password\":\"x\"}"))
            .andExpect(status().isBadRequest());
            
        verify(userService).authenticate(eq("x@example.com"),eq("x"));
    }

    
    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("test@example.com", "x")).willThrow(PasswordWrongExcption.class);

        mvc.perform(post("/session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test@example.com\",\"password\":\"x\"}"))
            .andExpect(status().isBadRequest());
            
        verify(userService).authenticate(eq("test@example.com"),eq("x"));
    }
}