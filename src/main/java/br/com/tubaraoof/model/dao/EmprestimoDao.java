package br.com.tubaraoof.model.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.tubaraoof.model.EmprestimoVO;
import br.com.tubaraoof.model.FiltroEmprestimo;
import br.com.tubaraoof.util.Util;

public class EmprestimoDao {
	
	private static final String PATH = "arquivos\\Emprestimo.txt";
	
	private FileWriter buscarArquivoGravar(boolean novoArquivo) throws IOException{
		FileWriter arq;
		if(novoArquivo) {
			arq = new FileWriter(PATH);
		}else {
			arq = new FileWriter(PATH,true);
		}
		
		return arq;
	}
	
	public void gravarEmprestimo(EmprestimoVO emprestimo) throws IOException {
		FileWriter arq = buscarArquivoGravar(false);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		Path path = Paths.get(PATH);
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
        
        emprestimo.setCodigo((linhasArquivo.size()+1));
		
        String linha = "";
        linha = linha.concat(Util.getParcialStr( 4,emprestimo.getCodigo().toString())).concat(";")
             .concat(Util.getParcialStr( 4,emprestimo.getCodigoUsuario().toString())).concat(";")
             .concat(Util.getParcialStr( 4,emprestimo.getCodigoFilme().toString())).concat(";")
             .concat(Util.getParcialStr( 6,emprestimo.getValorFinal().toString())).concat(";")
             .concat(Util.getParcialStr(10,emprestimo.getDataInicial().toString())).concat(";")
             .concat(Util.getParcialStr(10,emprestimo.getDataEntrega().toString())).concat(";")
             .concat(Util.getParcialStr( 6,emprestimo.getValorMulta().toString())).concat(";")
             .concat(Util.getParcialStr( 1,emprestimo.getAtivo().toString())).concat(";");
        gravarArq.append(linha + "\n");
        gravarArq.close();
	}
	
	public ArrayList<EmprestimoVO> retornarEmprestimos(FiltroEmprestimo filtro) throws IOException {
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<EmprestimoVO> listaEmprestimos = new ArrayList<EmprestimoVO>();
		
        for (String linha : linhasArquivo) {
        	boolean controle = true;
            String[] aux = linha.split(";");
            
            if(filtro.getCodigo() != null && Integer.parseInt(aux[0].trim()) != filtro.getCodigo()) {
            	controle = false;
            }
            
            if(filtro.getCodigoUsuario() != null &&  Integer.parseInt(aux[1].trim()) != filtro.getCodigoUsuario()) {
            	controle = false;
            }
            
            if(filtro.getCodigoFilme() != null &&  Integer.parseInt(aux[2].trim()) != filtro.getCodigoFilme()) {
            	controle = false;
            }
            
            if(filtro.getDataInicial() != null &&  !aux[4].trim().equalsIgnoreCase(filtro.getDataInicial())) {
            	controle = false;
            }
            
            if(filtro.getDataEntrega() != null &&  !aux[5].trim().equalsIgnoreCase(filtro.getDataEntrega())) {
            	controle = false;
            }
            
            if(filtro.getAtivo() != null &&  !aux[7].equalsIgnoreCase(filtro.getAtivo())) {
            	controle = false;
            }
            
            if(controle) {
            	EmprestimoVO emprestimo = new EmprestimoVO();
            	emprestimo.setCodigo(Integer.parseInt(aux[0].trim()));
            	emprestimo.setCodigoUsuario(Integer.parseInt(aux[1].trim()));
            	emprestimo.setCodigoFilme(Integer.parseInt(aux[2].trim()));
            	emprestimo.setValorFinal(Double.parseDouble(aux[3].trim()));
            	emprestimo.setDataInicial(aux[4].trim());
            	emprestimo.setDataEntrega(aux[5].trim());
            	emprestimo.setValorMulta(Double.parseDouble(aux[6].trim()));
            	emprestimo.setAtivo(aux[7].trim());
            	
            	listaEmprestimos.add(emprestimo);
            }
        }	
        
        return listaEmprestimos;
	}
	
	public EmprestimoVO retornarEmprestimoUnico(Integer codigo) throws IOException {
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		
		for (String linha : linhasArquivo) {
			boolean controle = true;
			String[] aux = linha.split(";");
			
			if(Integer.parseInt(aux[0].trim()) != codigo) {
				controle = false;
			}
			
			if(!aux[7].equalsIgnoreCase("A")) {
				controle = false;
			}
			
			if(controle) {
				EmprestimoVO emprestimo = new EmprestimoVO();
				emprestimo.setCodigo(Integer.parseInt(aux[0].trim()));
				emprestimo.setCodigoUsuario(Integer.parseInt(aux[1].trim()));
				emprestimo.setCodigoFilme(Integer.parseInt(aux[2].trim()));
				emprestimo.setValorFinal(Double.parseDouble(aux[3].trim()));
				emprestimo.setDataInicial(aux[4].trim());
				emprestimo.setDataEntrega(aux[5].trim());
				emprestimo.setValorMulta(Double.parseDouble(aux[6].trim()));
				emprestimo.setAtivo(aux[7].trim());
				
				return emprestimo;
			}
		}	
		
		return null;
	}
	
	public void excluirEmprestimo(Integer codigo) throws IOException {
		
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<EmprestimoVO> listaEmprestimos = new ArrayList<EmprestimoVO>();
		
        for (String linha : linhasArquivo) {
            String[] aux = linha.split(";");
            EmprestimoVO emprestimo = new EmprestimoVO();
            
            if(Integer.parseInt(aux[0].trim()) != codigo) {
            	emprestimo.setCodigo(Integer.parseInt(aux[0].trim()));
            	emprestimo.setCodigoUsuario(Integer.parseInt(aux[1].trim()));
            	emprestimo.setCodigoFilme(Integer.parseInt(aux[2].trim()));
            	emprestimo.setValorFinal(Double.parseDouble(aux[3].trim()));
            	emprestimo.setDataInicial(aux[4].trim());
            	emprestimo.setDataEntrega(aux[5].trim());
            	emprestimo.setValorMulta(Double.parseDouble(aux[6].trim()));
            	emprestimo.setAtivo(aux[7].trim());
            }else {
            	emprestimo.setCodigo(Integer.parseInt(aux[0].trim()));
            	emprestimo.setCodigoUsuario(Integer.parseInt(aux[1].trim()));
            	emprestimo.setCodigoFilme(Integer.parseInt(aux[2].trim()));
            	emprestimo.setValorFinal(Double.parseDouble(aux[3].trim()));
            	emprestimo.setDataInicial(aux[4].trim());
            	emprestimo.setDataEntrega(aux[5].trim());
            	emprestimo.setValorMulta(Double.parseDouble(aux[6].trim()));
            	emprestimo.setAtivo("I");
            }
            listaEmprestimos.add(emprestimo);
        }
		
        //Criar novo arquivo
        buscarArquivoGravar(true);
        
        listaEmprestimos.forEach(emprestimo ->  {
			try {
				gravarEmprestimo(emprestimo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}