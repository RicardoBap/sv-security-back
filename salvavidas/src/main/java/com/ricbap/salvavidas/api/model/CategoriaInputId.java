package com.ricbap.salvavidas.api.model;

import javax.validation.constraints.NotNull;

public class CategoriaInputId {
	
	@NotNull
	private Long codigo;

	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	

}
