package com.ricbap.salvavidas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ricbap.salvavidas.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Transactional(readOnly = true)
	public Pessoa findByEmail(String email);


}
