package br.senai.sp.leopizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import br.senai.sp.leopizza.modelo.SAC;

public class DAOSac implements InterfaceCRUD<SAC> {

	private Connection conexao;

	public DAOSac() {
		this.conexao = ConnectionFactory.getConnection();
	}

	@Override
	public void inserir(SAC s) throws Exception {
		conexao.setAutoCommit(false);
		String sql = "insert into sac (cliente_id, manifestacao, assunto, pedido, relato, feedback, prazo, status, funcionario_cpf)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		comando.setInt(1, s.getCliente().getId());
		comando.setInt(2, s.getManifestacao().ordinal());
		comando.setInt(3, s.getAssunto().ordinal());
		comando.setInt(4, s.getPedido().getNumero());
		//comando.setInt(, s.getItens());
		comando.setString(5, s.getRelato());
		comando.setString(6, s.getFeedback());
		comando.setString(7, s.getPrazo());
		comando.setString(8, s.getStatus());
		comando.setString(9, s.getFuncionario().toString());
		try {
			comando.execute();
			ResultSet chaves = comando.getGeneratedKeys();
			if (chaves.next()) {
				s.setProtocolo(chaves.getInt(1));
			} else {
				throw new Exception("Erro ao inserir a manifestação");
			}
			comando.close();
			conexao.commit();
		} catch (Exception e) {
			conexao.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			conexao.setAutoCommit(true);
		}
	}
	

	@Override
	public void atualizar(SAC s) throws Exception {
		String sql = "update sac (cliente_id, manifestacao, assunto, pedido, relato, feedback, prazo, status, funcionario_cpf)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, s.getCliente().getId());
		comando.setInt(2, s.getManifestacao().ordinal());
		comando.setInt(3, s.getAssunto().ordinal());
		comando.setInt(4, s.getPedido().getNumero());
		comando.setString(5, s.getRelato());
		comando.setString(6, s.getFeedback());
		comando.setString(7, s.getPrazo());
		comando.setString(8, s.getStatus());
		comando.setString(9, s.getFuncionario().toString());
		comando.execute();
		comando.close();
	}

	@Override
	public void excluir(SAC objeto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SAC> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
