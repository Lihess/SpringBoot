package kr.co.fastcampus.eatgo.utils;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private Key key;

	public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String createToken(long id, String name) {
        String token = Jwts.builder()
                        .claim("userId", id)
                        .claim("name", name)
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();

		return token;
	}

	public Claims getClaims(String token) {
        return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token) //jws : 사인이 포함된 jwt
                    .getBody();
	}

}
