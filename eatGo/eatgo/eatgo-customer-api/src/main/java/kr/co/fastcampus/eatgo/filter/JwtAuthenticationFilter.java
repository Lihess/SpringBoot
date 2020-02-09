package kr.co.fastcampus.eatgo.filter;

import java.rmi.ServerException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import kr.co.fastcampus.eatgo.utils.JwtUtil;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        // TODO Auto-generated constructor stub
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);

        if(authentication == null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            // 보안 context를 우리가 쓰는거로 설정 가능
        }

        chain.doFilter(request, response); // 기본적인 형태로, 작업이 연결되도록 함?
        //super.doFilterInternal(request, response, chain);
    }
    // Authentication은 스프링부트 내부에서 사용되는 거. 외부에 노출 x
    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        
        if(token == null)
            return null;

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
        // Authorization: Baarer TOKEN 형태이므로 필요한거만 뽑자

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);
        // 원리, 자격증?
        return authentication;
    }
}