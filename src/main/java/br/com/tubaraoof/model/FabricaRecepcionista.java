package br.com.tubaraoof.model;

import br.com.tubaraoof.model.interfaceFactory.FabricaDeUsuario;
import br.com.tubaraoof.model.interfaceFactory.Usuario;

public class FabricaRecepcionista implements FabricaDeUsuario{
	@Override
	public Usuario gravarTipoUsuario(String tipo) {
		return new RecepcionistaVO(tipo);
	}
}
