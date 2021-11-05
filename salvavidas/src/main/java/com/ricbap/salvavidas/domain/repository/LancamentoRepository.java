package com.ricbap.salvavidas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.domain.model.Lancamento;
import com.ricbap.salvavidas.domain.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
