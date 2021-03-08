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

import br.com.tubaraoof.model.FilmeVO;
import br.com.tubaraoof.model.FiltroFilme;
import br.com.tubaraoof.util.Util;

public class FilmeDao {

	private static final String PATH = "arquivos\\Filmes.txt";
        
        private FileWriter buscarArquivoGravar(boolean novoArquivo) throws IOException{
		FileWriter arq;
		if(novoArquivo) {
			arq = new FileWriter(PATH);
		}else {
			arq = new FileWriter(PATH,true);
		}
		
		return arq;
	}
	
	public FilmeVO retornarFilmeUnico(Integer codigo) throws IOException {
		Path path = Paths.get(PATH);
		
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		
		for (String linha : linhasArquivo) {
			boolean controle = true;
			String[] aux = linha.split(";");
			
			if(Integer.parseInt(aux[0].trim()) != codigo) {
				controle = false;
			}
			
			if(!aux[5].equalsIgnoreCase("A")) {
				controle = false;
			}
			
			if(controle) {
				FilmeVO filme = new FilmeVO();
				filme.setCodigo(Integer.parseInt(aux[0].trim()));
            	filme.setNome(aux[1].trim());
            	filme.setGenero(aux[2].trim());
            	filme.setValor(Double.parseDouble(aux[3].trim()));
            	filme.setQuantidade(Integer.parseInt(aux[4].trim()));
            	filme.setAtivo(aux[5].trim());
				return filme;
			}
		}	
		return null;
	}
	
	public ArrayList<FilmeVO> retornarFilmes(FiltroFilme filtro) throws IOException {
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<FilmeVO> listaFilmes = new ArrayList<FilmeVO>();
		
        for (String linha : linhasArquivo) {
        	boolean controle = true;
            String[] aux = linha.split(";");
            
            
            if(filtro.getCodigo() != null && Integer.parseInt(aux[0].trim()) != filtro.getCodigo()) {
            	controle = false;
            }
            
            if(filtro.getNome() != null &&  !aux[1].trim().equalsIgnoreCase(filtro.getNome())) {
            	controle = false;
            }
            
            if(filtro.getGenero() != null &&  !aux[2].trim().equalsIgnoreCase(filtro.getGenero())) {
            	controle = false;
            }
            
            if(filtro.getAtivo() != null &&  !aux[5].equalsIgnoreCase(filtro.getAtivo())) {
            	controle = false;
            }
            
            if(controle) {
            	FilmeVO filme = new FilmeVO();
            	filme.setCodigo(Integer.parseInt(aux[0].trim()));
            	filme.setNome(aux[1].trim());
            	filme.setGenero(aux[2].trim());
            	filme.setValor(Double.parseDouble(aux[3].trim()));
            	filme.setQuantidade(Integer.parseInt(aux[4].trim()));
            	filme.setAtivo(aux[5].trim());
            	
            	listaFilmes.add(filme);
            }
        }	
        return listaFilmes;
	}
        public void gravarFilme(FilmeVO filme) throws IOException {
		FileWriter arq = buscarArquivoGravar(false);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		Path path = Paths.get(PATH);
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
        
        filme.setCodigo((linhasArquivo.size()+1));
		
        String linha = "";
        linha = linha.concat(Util.getParcialStr( 4,filme.getCodigo().toString())).concat(";")
             .concat(Util.getParcialStr( 15,filme.getNome())).concat(";")
             .concat(Util.getParcialStr( 10,filme.getGenero())).concat(";")
             .concat(Util.getParcialStr( 6,filme.getValor().toString())).concat(";")
             .concat(Util.getParcialStr( 3,filme.getQuantidade().toString())).concat(";")
             .concat(Util.getParcialStr( 1,filme.getAtivo())).concat(";");
        gravarArq.append(linha + "\n");
        gravarArq.close();
	}
        
        public void excluirFilme(Integer codigo) throws IOException {
		
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<FilmeVO> listaFilmes = new ArrayList<FilmeVO>();
		
        for (String linha : linhasArquivo) {
            String[] aux = linha.split(";");
            FilmeVO filme = new FilmeVO();
            
            if(Integer.parseInt(aux[0].trim()) != codigo) {
            	filme.setCodigo(Integer.parseInt(aux[0].trim()));
            	filme.setNome((aux[1].trim()));
                filme.setGenero((aux[2].trim()));
            	filme.setValor(Double.parseDouble(aux[3].trim()));
            	filme.setQuantidade(Integer.parseInt(aux[4].trim()));
            	filme.setAtivo(aux[5].trim());
            }else {
            	filme.setCodigo(Integer.parseInt(aux[0].trim()));
            	filme.setNome((aux[1].trim()));
                filme.setGenero((aux[2].trim()));
            	filme.setValor(Double.parseDouble(aux[3].trim()));
            	filme.setQuantidade(Integer.parseInt(aux[4].trim()));
            	filme.setAtivo("I");
            }
            listaFilmes.add(filme);
        }
		
        //Criar novo arquivo
        buscarArquivoGravar(true);
        
        listaFilmes.forEach(filme ->  {
			try {
				gravarFilme(filme);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

   
}
