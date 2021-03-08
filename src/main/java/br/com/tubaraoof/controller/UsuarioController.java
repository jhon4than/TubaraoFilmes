package br.com.tubaraoof.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.model.interfaceFactory.FabricaDeUsuario;
import br.com.tubaraoof.model.interfaceFactory.Usuario;
import br.com.tubaraoof.model.FabricaAdministrador;
import br.com.tubaraoof.model.FabricaCliente;
import br.com.tubaraoof.model.FabricaRecepcionista;
import br.com.tubaraoof.model.FiltroUsuario;
import br.com.tubaraoof.model.dao.UsuarioDao;
import br.com.tubaraoof.service.FilmeService;
import br.com.tubaraoof.service.UsuarioService;
import br.com.tubaraoof.singleton.UsuarioLogado;
import br.com.tubaraoof.view.UsuarioView;

public class UsuarioController {
	
	UsuarioDao usuarioDao = new UsuarioDao();
	UsuarioView usuarioView = new UsuarioView();
	
	public Usuario validarLogin(ArrayList<String> dados) {
		FiltroUsuario filtro = new FiltroUsuario();
		
		try {
			filtro.setLogin(dados.get(0));
			filtro.setSenha(dados.get(1));
			
			return usuarioDao.retornarUsuarios(filtro).get(0);
		} catch (IOException e) {
			System.out.println("Erro");
		}
		
		return null;
	}
	
	public void detalharUsuario(Usuario usuario) {
		usuarioView.detalharUsuario(usuario);
	}

	public void cabecalhoUsuario() {
		usuarioView.listarCabecalho();
	}
        
        
    public void incluirUsuario() {

		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			usuarioView.erro();
			return;
		}
		
		try{
			String tipo = usuarioView.pedirTipo();
			Usuario usuario = this.instanciarUsuario(tipo);
			
			
			if(usuario == null) {
				usuarioView.erro();
				return;
			}
			
			usuario.setNome(usuarioView.pedirNome());
			usuario.setLogin(usuarioView.pedirLogin());
			usuario.setSenha(usuarioView.pedirSenha());
                        FiltroUsuario usuarioFiltro = this.converterUsurioFiltro(usuario);
			usuarioDao.gravarUsuario(usuarioFiltro);
			usuarioView.sucessoIncluir();

		} catch (IOException e1) {
			usuarioView.erro();                    
		}	

	}
    
        
        
    public void excluirUsuario() {

		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			usuarioView.erro();
			return;
		}

		FiltroUsuario filtro = new FiltroUsuario();

		try {
			ArrayList<Usuario> listaUsuario = usuarioDao.retornarUsuarios(filtro);
			usuarioView.listarCabecalho();
			listaUsuario.forEach(usuario -> usuarioView.detalharUsuario(usuario));
			
			int codigoUsuario;
			codigoUsuario = usuarioView.pedirCodigo();
			
			UsuarioService usuarioService = new UsuarioService();
			if(usuarioService.verificarUsuarioEmprestimo(codigoUsuario).size() > 0) {
				usuarioView.usuarioEmprestimo();
				return;
			}

			usuarioDao.excluirUsuario(codigoUsuario);
			usuarioView.sucessoExclusao();

		} catch (IOException e) {
			usuarioView.erro();
		}
	}
    
    public void alterarUsuario() {

		FiltroUsuario filtro = new FiltroUsuario();
		FiltroUsuario usuarioAlterar = new FiltroUsuario();
		int opcao;
                
        ArrayList<Usuario> listaUsuario;
		try {
			
			if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
				usuarioAlterar = this.converterUsurioFiltro(UsuarioLogado.getInstancia().getUsuario());
			
			}else {
				
			listaUsuario = usuarioDao.retornarUsuarios(filtro);
			usuarioView.listarCabecalho();
			listaUsuario.forEach(usuario -> usuarioView.detalharUsuario(usuario));

			usuarioAlterar = (FiltroUsuario) usuarioDao.retornarUsuarioUnico(usuarioView.pedirCodigo());
			}
		} catch (IOException e1) {
			usuarioView.erro();
		}

		do {
			opcao = usuarioView.listarAlteracoesUsuario();

			switch (opcao) {
			case 1:
				try {
					usuarioDao.excluirUsuario(usuarioAlterar.getCodigo());
					usuarioDao.gravarUsuario(usuarioAlterar);
					usuarioView.sucessoAlteracao();

				} catch (IOException e) {
					usuarioView.erro();
				}
				break;

			case 2:
				usuarioAlterar.setNome(usuarioView.pedirNome());
				usuarioView.sucessoAlteracaoNome();
				break;

			case 3:
				usuarioAlterar.setLogin(usuarioView.pedirLogin());
				usuarioView.sucessoAlteracaoLogin();
				break;

			case 4:
				usuarioAlterar.setSenha(usuarioView.pedirSenha());
				usuarioView.sucessoAlteracaoSenha();
				break;

			case 5:
				usuarioAlterar.setCodigo(usuarioView.pedirCodigo());
				usuarioView.sucessoAlteracaoCodigo();
				break;

			}
		}while(opcao != 1);
	}
    
    
    public Usuario instanciarUsuario(String tipo) {
    	Usuario usuario = null;
    	if(tipo.equalsIgnoreCase("C")) {
			FabricaDeUsuario fabrica = new FabricaCliente();
			usuario = fabrica.gravarTipoUsuario(tipo);
			
		}else if(tipo.equalsIgnoreCase("R")){
			FabricaDeUsuario fabrica = new FabricaRecepcionista();
			usuario = fabrica.gravarTipoUsuario(tipo);
			
		}else if(tipo.equalsIgnoreCase("A")) {
			FabricaDeUsuario fabrica = new FabricaAdministrador();
			usuario = fabrica.gravarTipoUsuario(tipo);
			
		}
    	return usuario;
    }
    
    public FiltroUsuario converterUsurioFiltro(Usuario usuario) {
		FiltroUsuario filtro = new FiltroUsuario();
		filtro.setCodigo(usuario.getCodigo());
		filtro.setNome(usuario.getNome());
		filtro.setLogin(usuario.getLogin());
		filtro.setSenha(usuario.getSenha());
		filtro.setTipo(usuario.getTipo());
		filtro.setStatus(usuario.getStatus());
		return filtro;
	}
}