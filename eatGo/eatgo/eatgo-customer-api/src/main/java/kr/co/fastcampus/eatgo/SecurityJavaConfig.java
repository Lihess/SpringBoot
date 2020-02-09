package kr.co.fastcampus.eatgo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.fastcampus.eatgo.utils.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{
    @Value("${jwt.secret}")// 어떤 값을 쓸건지
    private String secret;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { // 기본적으로 Override 해야함
        http.formLogin().disable(); // 기본 로그인 창이 안 뜨도록
        // restApi에서는 불필요함
        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable(); // 없으면 h2가 안떠
    }

    // Autowired 해주기 위해서
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtUtil jwtUtil(String secret){
        return new JwtUtil(secret);
    }
}