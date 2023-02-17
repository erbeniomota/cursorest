package br.com.kennobi.cursorest.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.kennobi.cursorest.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;


public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório !")
	@Length(min=5,max=50,message="Campo ontém de 5 a 50 caracteres")
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
