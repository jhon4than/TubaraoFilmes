package br.com.tubaraoof.service;

import java.io.IOException;
import java.util.ArrayList;

import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.model.FiltroEmprestimo;
import br.com.tubaraoof.model.FiltroFilme;
import br.com.tubaraoof.model.dao.FilmeDao;
import br.com.tubaraoof.view.FilmeView;

public class FilmeService {
	
	FilmeDao filmeDao = new FilmeDao();
	FilmeView filmeview = new FilmeView();

	public FilmeVO retornarFilme(Integer codigo) throws IOException {
		return filmeDao.retornarFilmeUnico(codigo);
	}

	public ArrayList<FilmeVO> retornarListaFilme(FiltroFilme filtro) throws IOException {
		return filmeDao.retornarFilmes(filtro);
	}

	public void detalharFilme(FilmeVO filme) {
		filmeview.detalharFilme(filme);
	}

	public void cabecalhoUsuario() {
		filmeview.listarCabecalho();
	}

	public ArrayList<EmprestimoVO> verificarFilmeEmprestimo(Integer codigo) throws IOException {
		EmprestimoService empresimoService = new EmprestimoService();
		FiltroEmprestimo filtroEmprestimo = new FiltroEmprestimo();
		filtroEmprestimo.setCodigoFilme(codigo);
		return empresimoService.validarExclusao(filtroEmprestimo);
	}
}