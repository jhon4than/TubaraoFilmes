package br.com.tubaraoof.main;

import br.com.tubaraoof.controller.EmprestimoController;
import br.com.tubaraoof.controller.FilmeController;
import br.com.tubaraoof.controller.UsuarioController;
import br.com.tubaraoof.singleton.UsuarioLogado;
import br.com.tubaraoof.view.MenuView;

public class Main {
	
	public static void main(String[] args) {
		int opcao;
		MenuView menu = new MenuView();
		UsuarioController usuarioController = new UsuarioController();
		
		try {
			UsuarioLogado.getInstancia().setUsuario(usuarioController.validarLogin(menu.login()));
		} catch (Exception e) {
			menu.falhaLogin();
		}
		
		if(UsuarioLogado.getInstancia().getUsuario() != null) {
			do {
				opcao = menu.listarMenu();
			
				switch(opcao) {
					case 1:
						EmprestimoController emprestimoController = new EmprestimoController();
						int opcaoEmprestimo;
						if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
							opcaoEmprestimo = menu.listarMenuEmprestimoCliente();
						}else {
							opcaoEmprestimo = menu.listarMenuEmprestimo();
						}
						
						if(opcaoEmprestimo == 1) {
							emprestimoController.mostrarEmprestimos();
							menu.espaco(2);
						}
						
						if(opcaoEmprestimo == 2) {
							emprestimoController.incluirEmprestimos();
							menu.espaco(2);
						}
						
						if(opcaoEmprestimo == 3) {
							emprestimoController.alterarEmprestimos();
							menu.espaco(2);
						}
						
						if(opcaoEmprestimo == 4) {
							emprestimoController.excluirEmprestimos();
							menu.espaco(2);
						}
						
						break;
						
                    case 2:
						FilmeController filmeController = new FilmeController();
						int opcaoFilme;
						if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
							opcaoFilme = menu.listarMenuFilmeCliente();
						}else {
							opcaoFilme = menu.listarMenuFilme();
						}
						
						if(opcaoFilme == 1) {
							filmeController.mostrarFilme();
							menu.espaco(2);
						}
						
						if(opcaoFilme == 2) {
							filmeController.incluirFilmes();
							menu.espaco(2);
						}
						
						if(opcaoFilme == 3) {
							filmeController.alterarFilmes();
							menu.espaco(2);
						}
						
						if(opcaoFilme == 4) {
							filmeController.excluirFilmes();
							menu.espaco(2);
						}
						
						break;
                                                
                    case 3:
                        usuarioController = new UsuarioController();
						int opcaoUsuario;
						opcaoUsuario = menu.listarMenuUsuario();
						
						if(opcaoUsuario == 1) {
							usuarioController.alterarUsuario();
							menu.espaco(2);
							if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
								opcao = 4;
							}
						}
						
						if(opcaoUsuario == 2) {
							if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
								break;
							}
							usuarioController.incluirUsuario();
							menu.espaco(2);
						}
						
						if(opcaoUsuario == 3) {
							if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
								break;
							}
							usuarioController.excluirUsuario();
							menu.espaco(2);
						}
						break;
				}
			}while(opcao != 4);
		}
	}
}