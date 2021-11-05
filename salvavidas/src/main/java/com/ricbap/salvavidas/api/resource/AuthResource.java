package com.ricbap.salvavidas.api.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.core.security.JWTUtil;
import com.ricbap.salvavidas.core.security.UserSS;
import com.ricbap.salvavidas.domain.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		
		String refresh_token = jwtUtil.generateRefreshToken(user.getUsername(), user.getNome(), user.getAuthorities());
		
		response.addHeader("Authorization", "Bearer " + refresh_token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();				
	}

}


//// create a cookie
//Cookie cookie = new Cookie("username", "Jovan");
//cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//cookie.setSecure(true);
//
////add cookie to response
//response.addCookie(cookie);
