package com.ricbap.salvavidas.domain.model.enums;

public enum Encargo {
	
	ADMIN(1, "ROLE_ADMIN"),
	MEMBRO(2, "ROLE_MEMBRO"),
	RSG(3, "ROLE_RSG"),
	SUPLENTE_RSG(4, "ROLE_SUPLENTE_RSG"),
	TESOUREIRO(5, "ROLE_TESOUREIRO"),
	SUPLENTE_TESOUREIRO(6, "ROLE_SUPLENTE_TESOUREIRO"),
	SECRETARIO(7, "ROLE_SECRETARIO"),
	SUPLENTE_SECRETARIO(8, "ROLE_SUPLENTE_SECRETARIO")
	
	;
	
	private int cod;
	private String descricao;
	
	private Encargo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}	
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Encargo toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Encargo x : Encargo.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}	

}
