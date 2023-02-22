package br.com.kennobi.cursorest.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.kennobi.cursorest.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório !")
	@Length(min=5,max=100,message="Campo ontém de 5 a 100 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatório !")
	@Email(message="Email Inválido!")
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
