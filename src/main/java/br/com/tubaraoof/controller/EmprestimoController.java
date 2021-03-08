package br.com.tubaraoof.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.model.FiltroEmprestimo;
import br.com.tubaraoof.model.FiltroFilme;
import br.com.tubaraoof.model.FiltroUsuario;
import br.com.tubaraoof.model.dao.EmprestimoDao;
import br.com.tubaraoof.service.EmprestimoService;
import br.com.tubaraoof.singleton.UsuarioLogado;
import br.com.tubaraoof.view.EmprestimoView;

public class EmprestimoController {
	
	EmprestimoView emprestimoView = new EmprestimoView();
	EmprestimoDao emprestimoDao = new EmprestimoDao();
	EmprestimoService emprestimoService = new EmprestimoService();
	
	
	public void mostrarEmprestimos() {
		FiltroEmprestimo filtro = new FiltroEmprestimo();
		int opcao;
		
		do {
			if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
				opcao = emprestimoView.listarFiltrosCliente();
			}else {
				opcao = emprestimoView.listarFiltrosRecepcionista();
			}
		
			switch (opcao) {
				case 1:
					try {
						if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
							filtro.setCodigoUsuario(UsuarioLogado.getInstancia().getUsuario().getCodigo());
						}
						ArrayList<EmprestimoVO> listaEmprestimo = emprestimoDao.retornarEmprestimos(filtro);
						emprestimoView.listarCabecalho();
						listaEmprestimo.forEach(emprestimo -> emprestimoView.listarEmprestimo(emprestimo));
					} catch (IOException e) {
						emprestimoView.erro();
					}
					break;
				
				case 2:
					filtro.setDataInicial(emprestimoView.pedirDataInicial());
					emprestimoView.sucessoFiltro();
					break;
					
				case 3:
					filtro.setDataEntrega(emprestimoView.pedirDataEntrega());
					emprestimoView.sucessoFiltro();
					break;
				
				case 4:
					if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
						emprestimoView.erro();
						break;
					}
					filtro.setCodigoUsuario(emprestimoView.pedirCliente());
					emprestimoView.sucessoFiltro();
					break;
					
				case 5:
					if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
						emprestimoView.erro();
						break;
					}
					filtro.setCodigoFilme(emprestimoView.pedirFilme());
					emprestimoView.sucessoFiltro();
					break;
					
				case 6:
					if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
						emprestimoView.erro();
						break;
					}
					filtro.setAtivo("I");
					emprestimoView.sucessoFiltro();
					break;
			}
		}while(opcao != 1);
	}
	
	public void incluirEmprestimos() {
		
		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			emprestimoView.erro();
			return;
		}
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		FiltroFilme filtroFilme = new FiltroFilme();
		EmprestimoVO emprestimo = new EmprestimoVO();
		
		try {
			filtroUsuario.setTipo("C");
			emprestimoService.cabecalhoUsuario();
			emprestimoService.listaClientes(filtroUsuario).forEach(usuario -> emprestimoService.detalharUsuario(usuario));
			
			emprestimo.setCodigoUsuario(emprestimoView.pedirCliente());
		} catch (IOException e) {
			emprestimoView.erro();
		}
		
		try {
			emprestimoService.cabecalhoFilme();
			emprestimoService.listaFilmes(filtroFilme).forEach(filme -> emprestimoService.detalharFilme(filme));
			
			emprestimo.setCodigoFilme(emprestimoView.pedirFilme());
			emprestimo.setValorFinal(this.retornarFilme(emprestimo.getCodigoFilme()).getValor());
		} catch (IOException e) {
			emprestimoView.erro();
		}
		
		emprestimo.setDataInicial(emprestimoView.pedirDataInicial());
		
		emprestimo.setDataEntrega(emprestimoView.pedirDataEntrega());
		
		emprestimo.setAtivo("A");
		emprestimo.setValorMulta(0.0);
		
		try {
			emprestimoDao.gravarEmprestimo(emprestimo);
		} catch (IOException e) {
			emprestimoView.erro();
		}
		
	}
	
	public void alterarEmprestimos() {
		
		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			emprestimoView.erro();
			return;
		}
		
		FiltroEmprestimo filtro = new FiltroEmprestimo();
		EmprestimoVO emprestimoAlterar = new EmprestimoVO();
		int opcao;
		
		ArrayList<EmprestimoVO> listaEmprestimo;
		try {
			listaEmprestimo = emprestimoDao.retornarEmprestimos(filtro);
			emprestimoView.listarCabecalho();
			listaEmprestimo.forEach(emprestimo -> emprestimoView.listarEmprestimo(emprestimo));
			
			emprestimoAlterar = emprestimoDao.retornarEmprestimoUnico(emprestimoView.pedirEmprestimo());
		} catch (IOException e1) {
			emprestimoView.erro();
		}
		
		do {
			opcao = emprestimoView.listarAlteracoes();
		
			switch (opcao) {
				case 1:
					try {
						emprestimoDao.excluirEmprestimo(emprestimoAlterar.getCodigo());
						emprestimoDao.gravarEmprestimo(emprestimoAlterar);
						emprestimoView.sucessoAlteracao();
						
					} catch (IOException e) {
						emprestimoView.erro();
					}
					break;
				
				case 2:
					emprestimoAlterar.setDataInicial(emprestimoView.pedirDataInicial());
					emprestimoView.sucessoFiltro();
					break;
					
				case 3:
					emprestimoAlterar.setDataEntrega(emprestimoView.pedirDataEntrega());
					emprestimoView.sucessoFiltro();
					break;
				
					
				case 4:
					FiltroFilme filtroFilme = new FiltroFilme();
					try {
						emprestimoService.cabecalhoFilme();
						emprestimoService.listaFilmes(filtroFilme).forEach(filme -> emprestimoService.detalharFilme(filme));
						
						int codigoFilme = emprestimoView.pedirFilme();
						emprestimoAlterar.setCodigoFilme(codigoFilme);
						emprestimoAlterar.setValorFinal(retornarFilme(codigoFilme).getValor());
					} catch (IOException e) {
						emprestimoView.erro();
					}
					emprestimoView.sucessoFiltro();
					break;
					
				case 5:
					emprestimoAlterar.setValorMulta((emprestimoView.pedirMulta()));
					emprestimoView.sucessoFiltro();
					break;
			}
		}while(opcao != 1);
	}
	
	public void excluirEmprestimos() {
		
		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			emprestimoView.erro();
			return;
		}
		
		FiltroEmprestimo filtro = new FiltroEmprestimo();
		
		try {
			ArrayList<EmprestimoVO> listaEmprestimo = emprestimoDao.retornarEmprestimos(filtro);
			emprestimoView.listarCabecalho();
			listaEmprestimo.forEach(emprestimo -> emprestimoView.listarEmprestimo(emprestimo));
			
			emprestimoDao.excluirEmprestimo(emprestimoView.pedirEmprestimo());
			emprestimoView.sucessoExclusao();
			
		} catch (IOException e) {
			emprestimoView.erro();
		}
	}
	
	public String retornarNomeCliente(Integer codigo) {
		try {
			return emprestimoService.retornarNomeCliente(codigo);
		} catch (IOException e) {
			return "Cliente não encontrado";
		}
	}
	
	public FilmeVO retornarFilme(Integer codigo) {
		try {
			return emprestimoService.retornarFilme(codigo);
		} catch (IOException e) {
			return null;
		}
	}

	
}