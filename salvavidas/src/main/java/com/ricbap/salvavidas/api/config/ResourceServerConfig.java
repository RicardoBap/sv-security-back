//package com.ricbap.salvavidas.api.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//	
////    protected void configure(AuthenticationManagerBuilder auth)  throws Exception {
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        auth.inMemoryAuthentication()
////          .withUser("admin")
////          .password(encoder.encode("admin"))
////          .roles("ROLE");
////    }
//	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/categorias").permitAll()
//				.anyRequest().authenticated().and()
//			//.httpBasic().and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			
//	}
//	
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources.stateless(true);
//	}
//
//}
