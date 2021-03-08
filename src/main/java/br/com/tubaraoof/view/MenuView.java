package br.com.tubaraoof.view;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.tubaraoof.singleton.UsuarioLogado;

public class MenuView {
	
	Scanner input = new Scanner(System.in);
	
	public ArrayList<String> login(){
		ArrayList<String> dados = new ArrayList<String>();
		
		System.out.println("******************************************");
		System.out.print  ("*****       Informe o Login: ");
		dados.add(input.nextLine());
		System.out.println("******************************************");
		System.out.print  ("*****       Informe a Senha: ");
		dados.add(input.nextLine());
		return dados;
	}
	
	
	public int listarMenu() {
        System.out.println("******************************************");
		System.out.println("*****        Bem vindo(a)   "+UsuarioLogado.getInstancia().getUsuario().getNome()+" ! *****");
        System.out.println("******************************************");
		System.out.println("*****   1- Menu Emprestimos          *****");
        System.out.println("*****   2- Menu Filmes               *****");
        System.out.println("*****   3- Menu Usuarios             *****");
		System.out.println("*****   4- Sair                      *****");
        System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao:  ");
        int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
	
	public int listarMenuEmprestimo() {
		System.out.println("******************************************");
		System.out.println("*****    --- Menu Emprestimos ---    *****");
		System.out.println("******************************************");
		System.out.println("*****   1- Mostrar Emprestimo        *****");
		System.out.println("*****   2- Incluir Emprestimo        *****");
		System.out.println("*****   3- Alterar Emprestimo        *****");
		System.out.println("*****   4- Excluir Emprestimo        *****");
		System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao: ");
		int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
	
	public int listarMenuEmprestimoCliente() {
		System.out.println("******************************************");
		System.out.println("*****   --- Menu Emprestimos ---     *****");
		System.out.println("******************************************");
		System.out.println("*****   1- Mostrar Emprestimo        *****");
		System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao: ");
		int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
        
        public int listarMenuUsuario() {
        System.out.println("******************************************");	
		System.out.println("*****   --- Menu Usuário ---         *****");
		System.out.println("******************************************");
		System.out.println("*****   1- Alterar Usuário           *****");
		System.out.println("*****   2- Incluir Usuário           *****");
		System.out.println("*****   3- Excluir Usuário:          *****");
		System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao: ");
		int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
        
        public int listarMenuFilme() {
        System.out.println("******************************************");
		System.out.println("*****      --- Menu Filme ---        *****");
		System.out.println("******************************************");
		System.out.println("*****   1- Mostrar Filme             *****");
		System.out.println("*****   2- Incluir Filme             *****");
		System.out.println("*****   3- Alterar Filme             *****");
		System.out.println("*****   4- Excluir Filme             *****");
		System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao: ");
		int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
	
	public int listarMenuFilmeCliente() {
		System.out.println("******************************************");
		System.out.println("*****        --- Menu  ---           *****");
		System.out.println("******************************************");
		System.out.println("*****   1- Mostrar Filme             *****");
		System.out.println("******************************************");
		System.out.print  ("*****   Escolha Opcao: ");
		int opcao = input.nextInt();
		System.out.println("");
		return opcao;
	}
	
	public void espaco(int linhas) {
		for(int i = 1; i <= linhas; i++) {
			System.out.println("");
		}
	}
	
	public void falhaLogin() {
		System.out.println("******************************************");
		System.out.println("*****   Falha ao realizar o login!   *****");
		System.out.println("******************************************");
	}
	
}
