package com.ricbap.salvavidas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	

}
