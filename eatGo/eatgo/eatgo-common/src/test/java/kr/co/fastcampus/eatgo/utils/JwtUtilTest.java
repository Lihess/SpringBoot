package  kr.co.fastcampus.eatgo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.eatgo.utils.JwtUtil;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;



public class JwtUtilTest {
    private static final String secret = "12345678901234567890123456798012";
    
    private JwtUtil jwtUtil;

    @Before
    public void setUp(){
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    public void createToken(){
        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("..."));
    }

    @Test
    public void getCalims(){
        String token = "..";
        Claims claims =  jwtUtil.getClaims(token);

        assertThat(claims.get("userIs", Long.class), is(1004L));
        assertThat(claims.get("name"), is("John"));
    }
}
    