package br.com.tubaraoof.model;

import br.com.tubaraoof.model.interfaceFactory.Usuario;

public class AdministradorVO implements Usuario {
	
	Integer codigo;
	String nome;
	String login;
	String senha;
	String tipo;
	String status;
	
	public AdministradorVO(String tipo) {
		gravarTipoUsuario(tipo);
		status = "A";
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void gravarTipoUsuario(String tipo) {
		setTipo(tipo);
	}

	@Override
	public Usuario retornarUsuario() {
		
		return this;
	}
}
