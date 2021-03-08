package br.com.tubaraoof.model.interfaceFactory;

public interface Usuario {
	void gravarTipoUsuario(String tipo);
	
	public String getTipo();
		
	public void setTipo(String tipo);
	
	public Usuario retornarUsuario();

	void setNome(String nome);
	String getNome();

	void setLogin(String login);
	String getLogin();

	void setSenha(String senha);
	String getSenha();

	void setCodigo(Integer i);
	Integer getCodigo();

	void setStatus(String status);
	String getStatus();


}