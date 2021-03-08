package br.com.tubaraoof.view;

import java.util.Scanner;

import br.com.tubaraoof.controller.EmprestimoController;
import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.util.Util;

public class EmprestimoView {
	
	public int listarFiltrosCliente() {
		Scanner input = new Scanner(System.in);
		System.out.println("------ Menu de filtros ------");
		System.out.println("1- Pesquisar");
		System.out.println("2- Data inicial do emprestimo");
		System.out.println("3- Data final   do emprestimo");
		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
	
	public int listarFiltrosRecepcionista() {
		Scanner input = new Scanner(System.in);
		System.out.println("------- Menu de filtros ------");
		System.out.println("1- Pesquisar");
		System.out.println("2- Data inicial do  emprestimo");
		System.out.println("3- Data entrega do  emprestimo");
		System.out.println("4- Cliente      com emprestimo");
		System.out.println("5- Filme        em  emprestimo");
		System.out.println("6- Emprestimos  inativos");
		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
	
	public int listarAlteracoes() {
		Scanner input = new Scanner(System.in);
		System.out.println("------ Menu de alteracao -----");
		System.out.println("1- Alterar");
		System.out.println("2- Data inicial do  emprestimo");
		System.out.println("3- Data entrega do  emprestimo");
		System.out.println("4- Filme        em  emprestimo");
		System.out.println("5- Multa        do  emprestimo");
		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
	
	public void listarCabecalho() {
		System.out.print(Util.getParcialStr( 8,"Código"));
		System.out.print(Util.getParcialStr(15,"Nome Cliente"));
		System.out.print(Util.getParcialStr(15,"Nome Filme"));
		System.out.print(Util.getParcialStr( 8,"Valor"));
		System.out.print(Util.getParcialStr(15,"Data Inicial"));
		System.out.print(Util.getParcialStr(15,"Data Entrega"));
		System.out.print(Util.getParcialStr(12,"Valor Multa"));
		System.out.print(Util.getParcialStr( 6,"Status"));
		System.out.println("");
	}
	
	public void listarEmprestimo(EmprestimoVO emprestimo) {
		EmprestimoController emprestimoController = new EmprestimoController();
		System.out.print(Util.getParcialStr( 8,emprestimo.getCodigo().toString()));
		System.out.print(Util.getParcialStr(15,emprestimoController.retornarNomeCliente(emprestimo.getCodigoUsuario())));
		System.out.print(Util.getParcialStr(15,emprestimoController.retornarFilme(emprestimo.getCodigoFilme()).getNome()));
		System.out.print(Util.getParcialStr( 8,emprestimo.getValorFinal().toString()));
		System.out.print(Util.getParcialStr(15,emprestimo.getDataInicial().toString()));
		System.out.print(Util.getParcialStr(15,emprestimo.getDataEntrega().toString()));
		System.out.print(Util.getParcialStr(12,emprestimo.getValorMulta().toString()));
		System.out.print(Util.getParcialStr( 1,emprestimo.getAtivo().toString()));
		System.out.println("");
	}
	
	
	public int pedirCliente() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o código do cliente:  ");
		return input.nextInt();
	}
	
	public int pedirEmprestimo() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o código do emprestimo:  ");
		return input.nextInt();
	}
	
	public int pedirFilme() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o código do Filme:  ");
		return input.nextInt();
	}
	
	public Double pedirMulta() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o valor da Multa:  ");
		return input.nextDouble();
	}
	
	public String pedirDataInicial() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe a data inicial:  ");
		String retorno = input.nextLine();
		return retorno;
	}
	
	public String pedirDataEntrega() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe a data de entrega:  ");
		String retorno = input.nextLine();
		return retorno;
	}

	public void sucessoFiltro() {
		System.out.println("Filtro adicionado com sucesso!");
	}
	
	public void erro() {
		System.out.println("Erro encontrado!");
	}

	public void sucessoExclusao() {
		System.out.println("Registro excluído com sucesso!");
	}
	
	public void sucessoAlteracao() {
		System.out.println("Registro alterado com sucesso!");
	}
}