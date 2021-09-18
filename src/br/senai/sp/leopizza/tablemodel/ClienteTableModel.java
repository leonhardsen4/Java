package br.senai.sp.leopizza.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.leopizza.modelo.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	// Lista com os clientes d tabela
	private List<Cliente> clientes;

	// vetor com os nomes das colunas
	private final String[] COLUNAS = { "Nome", "Telefone"};

	// construtor que recebe uma lista de clientes
	public ClienteTableModel(List<Cliente> lista) {
		// repassa a lista para a vari�vel clientes
		this.clientes = lista;
	}

	@Override
	public int getRowCount() {
		// o n�mero de linhas � o n�mero de clientes da lista
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		// O n�mero de colunas � o comprimento do vetor COLUNAS
		return COLUNAS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// retira da lista o objeto na posi��o rowIndex
		Cliente c = clientes.get(rowIndex);
		// verifica qual � a coluna
		switch (columnIndex) {
		case 0:
			// Se for coluna 0, retorna o nome
			return c.getNome();
		case 1:
			// Se for coluna 1, retorna o telefone
			return c.getTelefone();
		}

		return null;
	}

	@Override
	public String getColumnName(int column) {
		// Retorna o valor da posi��o column no vetor COLUNAS
		return COLUNAS[column];
	}

}
