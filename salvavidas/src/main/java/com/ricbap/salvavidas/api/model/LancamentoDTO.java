package com.ricbap.salvavidas.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ricbap.salvavidas.domain.model.Lancamento;
import com.ricbap.salvavidas.domain.model.enums.TipoLancamento;

public class LancamentoDTO {
	
	private Long codigo;
	private String descricao;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private TipoLancamento tipo;
	private String categoria;
	private String pessoa;
	
	public LancamentoDTO() {}
	
	public LancamentoDTO(Lancamento obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		dataVencimento = obj.getDataVencimento();
		dataPagamento = obj.getDataPagamento();
		valor = obj.getValor();
		tipo = obj.getTipo();
		categoria = obj.getCategoria().getNome();
		pessoa = obj.getPessoa().getNome();
	}

	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPessoa() {
		return pessoa;
	}
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}	
	

}
