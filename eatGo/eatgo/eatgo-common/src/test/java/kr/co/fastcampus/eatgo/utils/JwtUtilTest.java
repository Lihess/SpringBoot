package  kr.co.fastcampus.eatgo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.eatgo.utils.JwtUtil;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class JwtUtilTest {

    @Test
    public void createToken(){
        String secret = "12345678901234567890123456798012";
        JwtUtil jwtUtil = new JwtUtil(secret);
        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }
}
    