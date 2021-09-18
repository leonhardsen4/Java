package br.senai.sp.leopizza.modelo;

public enum TipoProduto {
	BEBIDAS("Bebida"), PIZZA_DOCE("Pizza doce"), PIZZA_SALGADA("Pizza salgada"), BROTO_DOCE("Broto doce"),
	BROTO_SALGADA("Broto salgada"), BORDA_RECHEADA("Borda recheada");

	TipoProduto(String string) {
		this.nome = string;
	}

	private String nome;

	@Override
	public String toString() {
		return nome;
	}
}
