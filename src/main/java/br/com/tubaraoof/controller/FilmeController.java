/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tubaraoof.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.model.FiltroFilme;
import br.com.tubaraoof.model.dao.FilmeDao;
import br.com.tubaraoof.service.FilmeService;
import br.com.tubaraoof.singleton.UsuarioLogado;
import br.com.tubaraoof.view.FilmeView;

public class FilmeController {
	private FilmeView filmeView = new FilmeView();
	private FilmeDao filmeDao = new FilmeDao();


	public void mostrarFilme() {
		FiltroFilme filtro = new FiltroFilme();
		int opcao;

		do {
			if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
				opcao = filmeView.listarFiltrosCliente();
			}else {
				opcao = filmeView.listarFiltrosRecepcionista();
			}

			switch (opcao) {
			case 1:
				try {

					ArrayList<FilmeVO> listaFilme = filmeDao.retornarFilmes(filtro);
					filmeView.listarCabecalho();
					listaFilme.forEach(filme -> filmeView.detalharFilme(filme));
				} catch (IOException e) {
					e.printStackTrace();
					filmeView.erro();
				}
				break;

			case 2:
				filtro.setNome(filmeView.pedirNome());
				filmeView.sucessoFiltro();
				break;

			case 3:
				filtro.setGenero(filmeView.pedirGenero());
				filmeView.sucessoFiltro();
				break;

			case 4:
				if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
					filmeView.erro();
					break;
				}
				filtro.setCodigo(filmeView.pedirCodigo());
				filmeView.sucessoFiltro();
				break;

			case 5:
				if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
					filmeView.erro();
					break;
				}
				filtro.setValor(filmeView.pedirValor());
				filmeView.sucessoFiltro();
				break;

			case 6:
				if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
					filmeView.erro();
					break;
				}
				filtro.setQuantidade(filmeView.pedirQuantidade());
				filmeView.sucessoFiltro();
				break;

			case 7:
				if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
					filmeView.erro();
					break;
				}
				filtro.setAtivo("I");
				filmeView.sucessoFiltro();
				break;
			}
		}while(opcao != 1);
	}

	public void incluirFilmes() {

		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			filmeView.erro();
			return;
		}
		FilmeVO filme = new FilmeVO();

		try{
			filme.setNome(filmeView.pedirNome());
			filme.setGenero(filmeView.pedirGenero());
			filme.setValor(filmeView.pedirValor());
			filme.setQuantidade(filmeView.pedirQuantidade());
			filme.setAtivo("A");
			filmeDao.gravarFilme(filme);

			filmeView.sucessoIncluir();

		} catch (IOException e1) {
			filmeView.erro();                    
		}	

	}

	public void alterarFilmes() {

		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			filmeView.erro();
			return;
		}

		FiltroFilme filtro = new FiltroFilme();
		FilmeVO filmeAlterar = new FilmeVO();
		int opcao;

		ArrayList<FilmeVO> listaFilme;
		try {
			listaFilme = filmeDao.retornarFilmes(filtro);
			filmeView.listarCabecalho();
			listaFilme.forEach(filme -> filmeView.detalharFilme(filme));

			filmeAlterar = filmeDao.retornarFilmeUnico(filmeView.pedirCodigo());
		} catch (IOException e1) {
			filmeView.erro();
		}

		do {
			opcao = filmeView.listarAlteracoes();

			switch (opcao) {
			case 1:
				try {
					filmeDao.excluirFilme(filmeAlterar.getCodigo());
					filmeDao.gravarFilme(filmeAlterar);
					filmeView.sucessoAlteracao();

				} catch (IOException e) {
					filmeView.erro();
				}
				break;

			case 2:
				filmeAlterar.setNome(filmeView.pedirNome());
				filmeView.sucessoFiltro();
				break;

			case 3:
				filmeAlterar.setGenero(filmeView.pedirGenero());
				filmeView.sucessoFiltro();
				break;

			case 4:
				filmeAlterar.setValor(filmeView.pedirValor());
				filmeView.sucessoFiltro();
				break;

			case 5:
				filmeAlterar.setQuantidade(filmeView.pedirQuantidade());
				filmeView.sucessoFiltro();
				break;

			}
		}while(opcao != 1);
	}

	public void excluirFilmes() {

		if(UsuarioLogado.getInstancia().getUsuario().getTipo().equalsIgnoreCase("C")) {
			filmeView.erro();
			return;
		}
		
		FiltroFilme filtro = new FiltroFilme();

		try {
			ArrayList<FilmeVO> listaFilme = filmeDao.retornarFilmes(filtro);
			filmeView.listarCabecalho();
			listaFilme.forEach(filme -> filmeView.detalharFilme(filme));
			
			int codigoFilme;
			codigoFilme = filmeView.pedirCodigo();
			
			FilmeService filmeService = new FilmeService();
			if(filmeService.verificarFilmeEmprestimo(codigoFilme).size() > 0) {
				filmeView.filmeEmprestimo();
				return;
			}
			
			filmeDao.excluirFilme(codigoFilme);
			filmeView.sucessoExclusao();

		} catch (IOException e) {
			filmeView.erro();
		}
	}

}