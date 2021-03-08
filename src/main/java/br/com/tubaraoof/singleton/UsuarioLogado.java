package br.com.tubaraoof.singleton;

import br.com.tubaraoof.model.interfaceFactory.Usuario;


public class UsuarioLogado {
	
	public static UsuarioLogado instancia;
	public Usuario usuario;
	
	private UsuarioLogado() {
		
	}
	
	public static UsuarioLogado getInstancia() {
		if(instancia == null) {
			instancia = new UsuarioLogado();
		}
		return instancia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}