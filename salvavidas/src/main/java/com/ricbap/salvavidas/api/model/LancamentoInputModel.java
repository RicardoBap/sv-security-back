package com.ricbap.salvavidas.api.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ricbap.salvavidas.domain.model.enums.TipoLancamento;

public class LancamentoInputModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private TipoLancamento tipo;
	
	@NotBlank
	@Size(min = 5, max = 50)
	private String descricao;
	
	@NotNull
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	@NotNull
	private Double valor;
	
	private String observacao;
	
	@Valid
	@NotNull
	private CategoriaInputId categoria;
	
	@Valid
	@NotNull
	private PessoaInputId pessoa;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public CategoriaInputId getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaInputId categoria) {
		this.categoria = categoria;
	}
	public PessoaInputId getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaInputId pessoa) {
		this.pessoa = pessoa;
	}
		

}
