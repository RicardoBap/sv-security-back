package com.ricbap.salvavidas.api.model;

import java.time.LocalDate;

import com.ricbap.salvavidas.domain.model.enums.TipoLancamento;

public class LancamentoResumoModel {
	
	private Long codigo;
	private TipoLancamento tipo;
	private String descricao;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private Double valor;	
	private PessoaResumoModel pessoa;
	
	
	public LancamentoResumoModel() {}
	

	public LancamentoResumoModel(Long codigo, TipoLancamento tipo, String descricao, LocalDate dataVencimento,
			LocalDate dataPagamento, Double valor, PessoaResumoModel pessoa) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.pessoa = pessoa;
	}


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
	public PessoaResumoModel getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaResumoModel pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
