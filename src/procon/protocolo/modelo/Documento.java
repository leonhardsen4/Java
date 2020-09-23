package procon.protocolo.modelo;

import java.util.Calendar;

public class Documento {
	private int ID;
	private TipoDocumento tipo;
	private String referencia;
	private String descricao;
	private Calendar data;
	private Origem setor;
	private Destino destino;

	// Constructor
	public Documento() {
	}

	// Getters and Setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Origem getSetor() {
		return setor;
	}

	public void setSetor(Origem setor) {
		this.setor = setor;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

}
