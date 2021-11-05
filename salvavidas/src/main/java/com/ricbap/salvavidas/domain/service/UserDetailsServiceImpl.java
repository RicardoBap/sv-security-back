package com.ricbap.salvavidas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.core.security.UserSS;
import com.ricbap.salvavidas.domain.model.Pessoa;
import com.ricbap.salvavidas.domain.repository.PessoaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepository.findByEmail(email);
		if(pessoa == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(pessoa.getNome(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getEncargos()) ;
	}

}

