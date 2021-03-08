package br.com.tubaraoof.model;

public class FiltroEmprestimo {
	
	private Integer codigo;
	private Integer codigoUsuario;
	private Integer codigoFilme;
	private Double  valor;
	private String  dataInicial;
	private String  dataEntrega;
	private Double  multa;
	private String  ativo;
	
	
	public FiltroEmprestimo() {
		ativo = "A";
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public Integer getCodigoFilme() {
		return codigoFilme;
	}
	public void setCodigoFilme(Integer codigoFilme) {
		this.codigoFilme = codigoFilme;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public Double getMulta() {
		return multa;
	}
	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}