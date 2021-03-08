package br.com.tubaraoof.service;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.controller.UsuarioController;
import br.com.tubaraoof.model.interfaceFactory.Usuario;
import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.model.FiltroEmprestimo;
import br.com.tubaraoof.model.FiltroUsuario;
import br.com.tubaraoof.model.dao.UsuarioDao;

public class UsuarioService {
	
	UsuarioDao usuarioDao = new UsuarioDao();

	public Usuario retornarCliente(Integer codigo) throws IOException {
		return usuarioDao.retornarUsuarioUnico(codigo);
	}
	
	public ArrayList<Usuario> retornarListaCliente(FiltroUsuario filtro) throws IOException {
		return usuarioDao.retornarUsuarios(filtro);
	}
	
	public void detalharUsuario(Usuario usuario) {
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.detalharUsuario(usuario);
	}

	public void cabecalhoUsuario() {
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.cabecalhoUsuario();
	}

	public ArrayList<EmprestimoVO> verificarUsuarioEmprestimo(int codigoUsuario) throws IOException {
		EmprestimoService empresimoService = new EmprestimoService();
		FiltroEmprestimo filtroEmprestimo = new FiltroEmprestimo();
		filtroEmprestimo.setCodigoUsuario(codigoUsuario);
		return empresimoService.validarExclusao(filtroEmprestimo);
	}
}