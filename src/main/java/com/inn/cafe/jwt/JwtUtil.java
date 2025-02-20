package com.inn.cafe.jwt;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	private String secret = "btechdays";

	public String extractUsername(String token) {
		return extractClamis(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClamis(token, Claims::getExpiration);
	}

	public <T> T extractClamis(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExprired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		
	}
}
