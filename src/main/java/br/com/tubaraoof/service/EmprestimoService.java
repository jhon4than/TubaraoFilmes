package br.com.tubaraoof.service;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.model.interfaceFactory.Usuario;
import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.model.FiltroEmprestimo;
import br.com.tubaraoof.model.FiltroFilme;
import br.com.tubaraoof.model.FiltroUsuario;
import br.com.tubaraoof.model.dao.EmprestimoDao;

public class EmprestimoService {
	
	public String retornarNomeCliente(Integer codigo) throws IOException {
		UsuarioService usuarioService = new UsuarioService();
		Usuario usuario = usuarioService.retornarCliente(codigo);
		return usuario.getNome();
	}
	
	public FilmeVO retornarFilme(Integer codigo) throws IOException {
		FilmeService filmeService = new FilmeService();
		FilmeVO filme = filmeService.retornarFilme(codigo);
		return filme;
	}
	
	public ArrayList<Usuario> listaClientes(FiltroUsuario filtro) throws IOException {
		UsuarioService usuarioService = new UsuarioService();
		return usuarioService.retornarListaCliente(filtro);
	}
	
	public ArrayList<FilmeVO> listaFilmes(FiltroFilme filtro) throws IOException {
		FilmeService filmeService = new FilmeService();
		return filmeService.retornarListaFilme(filtro);
	}

	public void detalharUsuario(Usuario usuario) {
		UsuarioService usuarioService = new UsuarioService();
		usuarioService.detalharUsuario(usuario);
	}
	
	public void detalharFilme(FilmeVO filme) {
		FilmeService filmeService = new FilmeService();
		filmeService.detalharFilme(filme);
	}

	public void cabecalhoUsuario() {
		UsuarioService usuarioService = new UsuarioService();
		usuarioService.cabecalhoUsuario();
	}

	public void cabecalhoFilme() {
		FilmeService filmeService = new FilmeService();
		filmeService.cabecalhoUsuario();
	}

	public ArrayList<EmprestimoVO> validarExclusao(FiltroEmprestimo filtroEmprestimo) throws IOException {
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		return emprestimoDao.retornarEmprestimos(filtroEmprestimo);
	}
}