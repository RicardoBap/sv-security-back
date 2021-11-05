package com.ricbap.salvavidas.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.model.LancamentoInputModel;
import com.ricbap.salvavidas.api.model.LancamentoResumoModel;
import com.ricbap.salvavidas.domain.exception.PessoaInexistenteOuInativaException;
import com.ricbap.salvavidas.domain.model.Lancamento;
import com.ricbap.salvavidas.domain.model.Pessoa;
import com.ricbap.salvavidas.domain.repository.LancamentoRepository;
import com.ricbap.salvavidas.domain.repository.PessoaRepository;
import com.ricbap.salvavidas.domain.repository.filter.LancamentoFilter;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;	
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public List<LancamentoResumoModel> findAll(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<Lancamento> lan = lancamentoRepository.filtrar(lancamentoFilter, pageable);
		return  toCollectionModel(lan);
	}
	
	
	public LancamentoInputModel insert(LancamentoInputModel lancamentoInputModel) {
		Lancamento lancamento = toEntity(lancamentoInputModel);
		Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
		if (!pessoaRepository.existsById(lancamento.getPessoa().getCodigo()) || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
		return toModelPost(lancamentoSalvo);
	}
	
//	public Lancamento insert(@Valid Lancamento lancamento) {
//		//Lancamento lancamento = toEntity(lancamentoInputModel);
//		Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
//		if (!pessoaRepository.existsById(lancamento.getPessoa().getCodigo()) || pessoa.isInativo()) {
//			throw new PessoaInexistenteOuInativaException();
//		}
//		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
//		return lancamentoSalvo;
//	}
	
	public LancamentoInputModel findById(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return toModelPost(lancamento);
	}
	
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	
	public void excluir(Long codigo) {
		findById(codigo);
		lancamentoRepository.deleteById(codigo);
	}	
	
	
	
//	public Page<Lancamento> findPage(LancamentoFilter lancamentoFilter, Integer page, Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//		return lancamentoRepository.filtrar(lancamentoFilter, pageRequest);
//	}
	
	
	private Lancamento buscarPeloCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lancamento;
	}
	
	
	
	
	// Tranforma o LancamentoInputModel para Lancamento POST
	private Lancamento toEntity(LancamentoInputModel lancamentoInputModel) {
		return modelMapper.map(lancamentoInputModel, Lancamento.class);
	}
	// Retorna POST
	private LancamentoInputModel toModelPost(Lancamento lancamentoModel) {		
		return modelMapper.map(lancamentoModel, LancamentoInputModel.class) ;
	}
	
	//Recebe um Array do metodo GET
	private List<LancamentoResumoModel> toCollectionModel(Page<Lancamento> list) {
		return list.stream()
				.map(lancamentoModel -> toModel(lancamentoModel))
				.collect(Collectors.toList());
	}
	//Complemento do metodo roCllectionModel
	private LancamentoResumoModel toModel(Lancamento lancamentoModel) {		
		return modelMapper.map(lancamentoModel, LancamentoResumoModel.class) ;
	}

}
