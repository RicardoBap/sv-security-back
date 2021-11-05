package com.ricbap.salvavidas.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ricbap.salvavidas.domain.model.enums.Encargo;


@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotBlank
	//@JsonIgnore
	private String senha;
	
	@NotBlank
	@Size(max = 15)
	private String telefone;
	
	@NotBlank
	@Email
	//@Column(unique = true)
	private String email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="ENCARGOS")
	private Set<Integer> encargos = new HashSet<>();
	
	@NotNull
	private Boolean ativo;
	
	//--------METODO INPLEMENTANDO EM LancamentoService
	@JsonIgnore
	@Transient // import javax.persistence
	public boolean isInativo() {
		return !this.ativo;
	}
	
	// -------------------------
	public Set<Encargo> getEncargos() {
		return encargos.stream().map(x -> Encargo.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addEncargo(Encargo encargo) {
		encargos.add(encargo.getCod());
	}
	
	
// ************************************************************************************** CONSTRUCTOR	
	public Pessoa() {} // Contrutor vazio necessario para fazer login
	
	public Pessoa(Long codigo,String nome, String senha, String telefone,
			String email, String encargo,  Boolean ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
		this.ativo = ativo;
		addEncargo(Encargo.MEMBRO);
	}
//*****************************************************************************************GETTERS AND SETTERS
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
//*******************************************************************************************EQUALS AND HASHCODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
		
}