package br.com.tubaraoof.model;

public class EmprestimoVO {
	private Integer codigo;
	private Integer codigoUsuario;
	private Integer codigoFilme;
	private Double valorFinal;
	private String dataInicial;
	private String dataEntrega;
	private Double valorMulta;
	private String ativo;
	
	public EmprestimoVO() {
		
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

	public Double getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
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

	public Double getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Emprestimo [codigo=" + codigo + ", codigoUsuario=" + codigoUsuario + ", codigoFilme=" + codigoFilme
				+ ", valorFinal=" + valorFinal + ", dataInicial=" + dataInicial + ", dataEntrega=" + dataEntrega
				+ ", valorMulta=" + valorMulta + ", ativo=" + ativo + "]";
	}
}
