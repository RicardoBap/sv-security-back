package com.ricbap.salvavidas.core.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.expiration_refresh_token}")
	private Long expiration_refresh_token;
	
	
	public String generateToken(String email, String nome, Collection<? extends GrantedAuthority> authorities) {
		return Jwts.builder()
				.setSubject(email) 
				.claim("nome", nome)
				.claim("roles", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public String generateRefreshToken(String email, String nome, Collection<? extends GrantedAuthority> authorities) {
		return Jwts.builder()
				.setSubject(email) 
				.claim("nome", nome)
				.claim("roles", authorities) 
				.setExpiration(new Date(System.currentTimeMillis() + expiration_refresh_token))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	
	// Metodos da classe JWTAuthorizationFilter => Autorização
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}	
		
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
				

	private Claims getClaims(String token) {		
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch(Exception e) {
			return null;
		}		
	}
		
}


