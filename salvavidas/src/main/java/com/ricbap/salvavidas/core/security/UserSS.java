package com.ricbap.salvavidas.core.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ricbap.salvavidas.domain.model.enums.Encargo;


public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSS() {}
		
	public UserSS(String nome, String email, String senha, Set<Encargo> encargos) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.authorities = encargos.stream()
				.map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	
	
	// Metodo para verificar se o usuario e o que ele diz ser -> pessoaService
	public boolean hasRole(Encargo encargo) {
		return getAuthorities().contains(new SimpleGrantedAuthority(encargo.getDescricao()));
	}


	public Long getCodigo() {
		return codigo;
	}	
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}	

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
		

}
