package com.ricbap.salvavidas.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.model.PessoaResumoModel;
import com.ricbap.salvavidas.core.security.UserSS;
import com.ricbap.salvavidas.domain.exception.AuthorizationException;
import com.ricbap.salvavidas.domain.model.Pessoa;
import com.ricbap.salvavidas.domain.model.enums.Encargo;
import com.ricbap.salvavidas.domain.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;	
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	private BCryptPasswordEncoder bCrypt;
	
	
	public Pessoa find(Long codigo) {
		return buscarPeloCodigo(codigo);
	}
	
	// Carrega pessoa no cadastro de lancamento
	public List<PessoaResumoModel> findAll() {
		return toCollectionModel(pessoaRepository.findAll()); 
	}
	
	// Inserindo pessoa
	public Pessoa insert(Pessoa pessoa) {
//		String senha = bCrypt.encode(pessoa.getSenha());
//		pessoa.setSenha(senha);
		//Pessoa pessoa = pessoa.addEncargo(Encargo.ADMIN);
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);	
		return pessoaSalva;
	}

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}	
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {		
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	public void excluir(Long codigo) {
		pessoaRepository.deleteById(codigo);		
	}

	
	private Pessoa buscarPeloCodigo(Long codigo) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Encargo.ADMIN) && !codigo.equals(user.getCodigo())) {
			throw new AuthorizationException();
		}
		
		Pessoa pessoa = pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoa;
	}	
	
	
	
	
	private List<PessoaResumoModel> toCollectionModel(List<Pessoa> pessoasModel) {
		return pessoasModel.stream()
				.map(pessoaModel -> toModel(pessoaModel))
				.collect(Collectors.toList());
	}

	private PessoaResumoModel toModel(Pessoa pessoaModel) {		
		return modelMapper.map(pessoaModel, PessoaResumoModel.class) ;
	}

}
