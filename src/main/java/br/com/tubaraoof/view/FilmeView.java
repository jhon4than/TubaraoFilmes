package br.com.tubaraoof.view;

import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.util.Util;
import java.util.Scanner;

public class FilmeView {
	
	public void listarCabecalho() {
		System.out.print(Util.getParcialStr( 8,"Código"));
		System.out.print(Util.getParcialStr(17,"Título"));
		System.out.print(Util.getParcialStr(12,"Genero"));
		System.out.print(Util.getParcialStr( 8,"Valor"));
		System.out.print(Util.getParcialStr(12,"Quantidade"));
		System.out.print(Util.getParcialStr( 7,"Ativo"));
		System.out.println("");
	}
	
	public void detalharFilme(FilmeVO filme) {
		System.out.print(Util.getParcialStr( 8,filme.getCodigo().toString()));
		System.out.print(Util.getParcialStr(17,filme.getNome()));
		System.out.print(Util.getParcialStr(12,filme.getGenero()));
		System.out.print(Util.getParcialStr( 8,filme.getValor().toString()));
		System.out.print(Util.getParcialStr(12,filme.getQuantidade().toString()));
		System.out.print(Util.getParcialStr( 6,filme.getAtivo()));
		System.out.println("");
	}

    public int listarFiltrosCliente() {
		Scanner input = new Scanner(System.in);
		System.out.println("------ Menu de filtros ------");
		System.out.println("1- Pesquisar");
		System.out.println("2- Nome   do filme");
		System.out.println("3- Genero do filme");
		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}

    public int listarFiltrosRecepcionista() {
        Scanner input = new Scanner(System.in);
		System.out.println("------- Menu de filtros ------");
		System.out.println("1- Pesquisar");
		System.out.println("2- Nome   do filme");
		System.out.println("3- Genero do filme");
		System.out.println("4- Codigo do filme");
		System.out.println("5- Valor  do Filme");
		System.out.println("6- Quantidade");
                System.out.println("7- Disponibilidade");
		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
    
    public int listarAlteracoes() {
		Scanner input = new Scanner(System.in);
		System.out.println("------ Menu de alteracao -----");
		System.out.println("1- Alterar");
		System.out.println("2- Nome   do filme");
		System.out.println("3- Genero do filme");
		System.out.println("4- Valor  do Filme");
		System.out.println("5- Quantidade");

		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
	
	public String pedirNome() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Nome do Filme:  ");
		return input.nextLine();
	}
        
        public String pedirGenero() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Genero do Filme:  ");
		return input.nextLine();
	}
      
        public Integer pedirCodigo() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Codigo do Filme:  ");
		return input.nextInt();
	}
        
        public Double pedirValor() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Valor do Filme:  ");
		return input.nextDouble();
	}
        
        public Integer pedirQuantidade() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe a quantidade disponivel de Filmes:  ");
		return input.nextInt();
	}       

	public void sucessoFiltro() {
		System.out.println("Filtro adicionado com sucesso!");
	}
        
        public void sucessoIncluir() {
		System.out.println("Filme adicionado com sucesso!");
	}
	
	public void erro() {
		System.out.println("Erro encontrado!");
	}

	public void sucessoExclusao() {
		System.out.println("Filme excluído com sucesso!");
	}
	
	public void sucessoAlteracao() {
		System.out.println("Filme alterado com sucesso!");
	}

	public void filmeEmprestimo() {
		System.out.println("Erro ao excluir filme!\nFilme em empréstimo");
	}
}
