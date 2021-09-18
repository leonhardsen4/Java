package br.senai.sp.leopizza.modelo;

public enum FormaPgto {
	DINHEIRO("Dinheiro"), CARTAO_DEB("Cart�o de D�bito"), CARTAO_CRED("Cart�o de Cr�dito"), VALE_REFEICAO("Vale Refei��o");
	

	FormaPgto(String string) {
		this.nome = string;
	}

	private String nome;

	@Override
	public String toString() {
		return nome;
	}
}
