package br.senai.sp.leopizza.modelo;

public enum Manifestacao {
	RECLAMACAO("Reclama��o"),
	ELOGIO("Elogio"),
	SUGESTAO("Sugest�o"),
	DUVIDA("D�vida");

	Manifestacao(String string) {
		this.nome = string;
	}

	private String nome;

	@Override
	public String toString() {
		return nome;
	}
}
