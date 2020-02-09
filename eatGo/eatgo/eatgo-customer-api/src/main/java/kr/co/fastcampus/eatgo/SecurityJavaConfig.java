package kr.co.fastcampus.eatgo;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.fastcampus.eatgo.filter.JwtAuthenticationFilter;
import kr.co.fastcampus.eatgo.utils.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{
    @Value("${jwt.secret}")// 어떤 값을 쓸건지
    private String secret;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { // 기본적으로 Override 해야함
        Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil()); 
        // 둘다 빈이여서 메소드처럼 가능.
        http.formLogin().disable(); // 기본 로그인 창이 안 뜨도록
        // restApi에서는 불필요함
        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable(); // 없으면 h2가 안떠
        http.addFilter(filter); // 인가관련 필터 추가
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // session에 대한 정책 추가. 따로 세션으로 등록 안하고 매번 받아서 할거여서 STATELESS
    }

    // Autowired 해주기 위해서
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(secret);
    }
}