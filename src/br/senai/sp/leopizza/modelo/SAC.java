package br.senai.sp.leopizza.modelo;

import java.util.Calendar;
import java.util.List;

public class SAC {
	private int protocolo;
	private int numero;
	private String funcionario;
	private Cliente cliente;
	private Pedido pedido;
	private List<ItemPedido> itens;
	private Manifestacao manifestacao;
	private Assunto assunto;
	private String relato;
	private String Feedback;
	private Calendar data;
	private String status;
	private String prazo;
	
	public int getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(int protocolo) { 
		this.protocolo = protocolo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Manifestacao getManifestacao() {
		return manifestacao;
	}

	public void setManifestacao(Manifestacao manifestacao) {
		this.manifestacao = manifestacao;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public String getRelato() {
		return relato;
	}

	public void setRelato(String relato) {
		this.relato = relato;
	}

	public String getFeedback() {
		return Feedback;
	}

	public void setFeedback(String feedback) {
		Feedback = feedback;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String string) {
		this.prazo = string;
	}
	
	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String string) {
		this.funcionario = string;
	}
	

}
