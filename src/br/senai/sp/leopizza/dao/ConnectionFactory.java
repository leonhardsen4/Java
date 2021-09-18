package br.senai.sp.leopizza.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection conexao;
	private static String usuario = "root";
	private static String senha = "root";
	//private static String strConexao = "jdbc:mysql://10.92.198.11/leopizza?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
	private static String strConexao = "jdbc:mysql://localhost/leopizza?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";// como � porta �dr�o

	public static Connection getConnection() {
		// verifica se a conex�o � nula
		if (conexao == null) {
			// tenta abrir a conex�o
			try {
				conexao = DriverManager.getConnection(strConexao, usuario, senha);
			} catch (SQLException e) {
				// exibe informa��es do erro
				e.printStackTrace();
				System.out.println("Erro na conex�o: " + e.getMessage());
			}
		}
		// retorna o objeto conex�o
		return conexao;
	}

	public static void CloseConnection() {
		// verifica se a conex�o existe
		if (conexao != null) {
			try {
				// tenta fechar a conex�o
				conexao.close();
			} catch (SQLException e) {
				// exibe informa��es do erro
				e.printStackTrace();
				System.out.println("Erro ao tentar fechar a conex�o: " + e.getMessage());
			}
		}
	}

}
