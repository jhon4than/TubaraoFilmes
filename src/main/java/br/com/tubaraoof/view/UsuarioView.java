package br.com.tubaraoof.view;

import br.com.tubaraoof.model.interfaceFactory.Usuario;
import br.com.tubaraoof.util.Util;
import java.util.Scanner;

public class UsuarioView {
	
	
	public void listarCabecalho() {
		System.out.print(Util.getParcialStr( 8,"Código"));
		System.out.print(Util.getParcialStr(17,"Nome"));
		System.out.print(Util.getParcialStr( 6,"Tipo"));
		System.out.print(Util.getParcialStr( 6,"Status"));
		System.out.println("");
	}
	
	public void detalharUsuario(Usuario usuario) {
		System.out.print(Util.getParcialStr( 8,usuario.getCodigo().toString()));
		System.out.print(Util.getParcialStr(17,usuario.getNome()));
		System.out.print(Util.getParcialStr( 6,usuario.getTipo()));
		System.out.print(Util.getParcialStr( 6,usuario.getStatus()));
		System.out.println("");
	}
        
    public String pedirNome() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Nome:  ");
		return input.nextLine();
	}
        
    public Integer pedirCodigo() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Codigo do Usuario:  ");
		return input.nextInt();
	}
    public String pedirTipo() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Tipo:  ");
		return input.nextLine();
	}
        
    public String pedirStatus() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Status:  ");
		return input.nextLine();
	}
    
     public int listarAlteracoesUsuario() {
		Scanner input = new Scanner(System.in);
		System.out.println("------ Menu de alteracao Usuario -----");
		System.out.println("1- Alterar");
		System.out.println("2- Nome");
		System.out.println("3- Login");
		System.out.println("4- Senha");
		System.out.println("5- Codigo");

		System.out.print  ("Escolha Opcao: ");
		return input.nextInt();
	}
        
        public void sucessoIncluir() {
		System.out.println("Usuário adicionado com sucesso!");
	}
	
	public void erro() {
		System.out.println("Erro encontrado!");
	}

	public void sucessoExclusao() {
		System.out.println("Usuário excluído com sucesso!");
	}
	
	public void sucessoAlteracao() {
		System.out.println("Usuário alterado com sucesso!");
	}
        public void sucessoAlteracaoNome() {
		System.out.println("Nome alterado com sucesso!");
	}
        
        public void sucessoAlteracaoLogin() {
		System.out.println("Login alterado com sucesso!");
	}
         public void sucessoAlteracaoSenha() {
		System.out.println("Senha alterado com sucesso!");
	}
        public void sucessoAlteracaoCodigo() {
		System.out.println("Codigo alterado com sucesso!");
	}

	public String pedirLogin() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe o Login:  ");
		return input.nextLine();
	}

	public String pedirSenha() {
		Scanner input = new Scanner(System.in);
		System.out.print  ("Informe a Senha:  ");
		return input.nextLine();
	}
	
	public void usuarioEmprestimo() {
		System.out.println("Erro ao excluir usuario!\nUsuario em empréstimo");
	}
	
}
