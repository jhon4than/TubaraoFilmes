package br.com.tubaraoof.model.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.tubaraoof.controller.UsuarioController;
import br.com.tubaraoof.model.FiltroUsuario;
import br.com.tubaraoof.model.interfaceFactory.Usuario;
import br.com.tubaraoof.util.Util;
import java.io.FileWriter;
import java.io.PrintWriter;

public class UsuarioDao {
	
	private static final String PATH = "arquivos\\Usuarios.txt";
	
	public Usuario retornarUsuarioUnico(Integer codigo) throws IOException {
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
            	UsuarioController usuarioController = new UsuarioController();
            	Usuario usuario = usuarioController.instanciarUsuario(aux[4].trim());
            	usuario.setCodigo(Integer.parseInt(aux[0].trim()));
            	usuario.setNome(aux[1].trim());
            	usuario.setLogin(aux[2].trim());
            	usuario.setSenha(aux[3].trim());
                //usuario.setTipo(aux[4].trim());
            	usuario.setStatus(aux[5].trim());
            	return usuario;
            }
        }	
        return null;
	}
	
	public ArrayList<Usuario> retornarUsuarios(FiltroUsuario filtro) throws IOException {
		Path path = Paths.get(PATH);
		
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		for (String linha : linhasArquivo) {
			boolean controle = true;
            String[] aux = linha.split(";");
            
            if(filtro.getCodigo() != null && Integer.parseInt(aux[0].trim()) != filtro.getCodigo()) {
            	controle = false;
            }
            
            if(filtro.getNome() != null && !aux[1].trim().equalsIgnoreCase(filtro.getNome())) {
            	controle = false;
            }
            
            if(filtro.getLogin() != null && !aux[2].trim().equalsIgnoreCase(filtro.getLogin())) {
            	controle = false;
            }
            
            if(filtro.getSenha() != null && !aux[3].trim().equalsIgnoreCase(filtro.getSenha())) {
            	controle = false;
            }
            
            if(filtro.getTipo() != null && !aux[4].trim().equalsIgnoreCase(filtro.getTipo())) {
            	controle = false;
            }
            
            if(filtro.getStatus() != null && !aux[5].equalsIgnoreCase(filtro.getStatus())) {
            	controle = false;
            }
			
			if(controle) {
				UsuarioController usuarioController = new UsuarioController();
				Usuario usuario = usuarioController.instanciarUsuario(aux[4].trim());
				usuario.setCodigo(Integer.parseInt(aux[0].trim()));
            	usuario.setNome(aux[1].trim());
            	usuario.setLogin(aux[2].trim());
            	usuario.setSenha(aux[3].trim());
                usuario.setTipo(aux[4].trim());
            	usuario.setStatus(aux[5].trim());
				
				listaUsuarios.add(usuario);
			}
		}	
		return listaUsuarios;
	}
        
        
        private FileWriter buscarArquivoGravar(boolean novoArquivo) throws IOException{
		FileWriter arq;
		if(novoArquivo) {
			arq = new FileWriter(PATH);
		}else {
			arq = new FileWriter(PATH,true);
		}
		
		return arq;
	}
        
        
    public void gravarUsuario(FiltroUsuario usuario) throws IOException {
		FileWriter arq = buscarArquivoGravar(false);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		Path path = Paths.get(PATH);
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
        
        usuario.setCodigo((linhasArquivo.size()+1));
		
        String linha = "";
        linha = linha.concat(Util.getParcialStr( 4,usuario.getCodigo().toString())).concat(";")
             .concat(Util.getParcialStr(15,usuario.getNome().toString())).concat(";")
             .concat(Util.getParcialStr(10,usuario.getLogin().toString())).concat(";")
             .concat(Util.getParcialStr(10,usuario.getSenha().toString())).concat(";")
             .concat(Util.getParcialStr( 1,usuario.getTipo().toString())).concat(";")
             .concat(Util.getParcialStr( 1,usuario.getStatus().toString())).concat(";");
        gravarArq.append(linha + "\n");
        gravarArq.close();
	}
        
        public void excluirUsuario(Integer codigo) throws IOException {
		
		Path path = Paths.get(PATH);
		UsuarioController usuarioController = new UsuarioController();
		List<String> linhasArquivo = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
        for (String linha : linhasArquivo) {
            String[] aux = linha.split(";");
            Usuario usuario = usuarioController.instanciarUsuario(aux[4].trim());
            
            if(Integer.parseInt(aux[0].trim()) != codigo) {
            	usuario.setCodigo(Integer.parseInt(aux[0].trim()));
            	usuario.setNome(aux[1].trim());
            	usuario.setLogin(aux[2].trim());
            	usuario.setSenha(aux[3].trim());
                //usuario.setTipo(aux[4].trim());
            	usuario.setStatus(aux[5].trim());
            }else {
            	usuario.setCodigo(Integer.parseInt(aux[0].trim()));
            	usuario.setNome(aux[1].trim());
            	usuario.setLogin(aux[2].trim());
            	usuario.setSenha(aux[3].trim());
                //usuario.setTipo(aux[4].trim());
            	usuario.setStatus("I");
            	
            }
            listaUsuarios.add(usuario);
        }
		
        //Criar novo arquivo
        buscarArquivoGravar(true);
        
        listaUsuarios.forEach(usuario ->  {
			try {
				gravarUsuario(usuarioController.converterUsurioFiltro(usuario));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
        
        
}