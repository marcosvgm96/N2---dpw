package br.com.academia.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.model.Modalidade;

public class ModalidadeDao {

	private static final String insert = "INSERT INTO modalidades (descricao) VALUES (?);";
	private static final String selectId = "SELECT \"codigoModalidade\", descricao FROM modalidades WHERE \"codigoModalidade\" = ?";
	private static final String selectAll = "SELECT * FROM modalidades";
	private static final String delete = "DELETE FROM modalidades WHERE \"codigoModalidade\" = ?;";
	private static final String update = "UPDATE modalidades SET descricao = ? WHERE \"codigoModalidade\" = ?;";

	public ModalidadeDao() {

	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "admin");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public List<Modalidade> selectAllModalidades() {
		List<Modalidade> modalidades = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectAll)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long codigoModalidade = rs.getLong("codigoModalidade");
				String descricao = rs.getString("descricao");
				modalidades.add(new Modalidade(codigoModalidade, descricao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return modalidades;
	}

	public Modalidade selectById(Long codigoModalidade) {
		Modalidade modalidade = null;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectId)) {
			ps.setLong(1, codigoModalidade);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String descricao = rs.getString("descricao");
				modalidade = new Modalidade(codigoModalidade, descricao);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return modalidade;
	}

	public void insertModalidade(Modalidade modalidade) throws SQLException {
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(insert)) {
			ps.setString(1, modalidade.getDescricao());
			ps.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean updateModalidade(Modalidade modalidade) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(update)) {
			ps.setString(1, modalidade.getDescricao());
			ps.setLong(2, modalidade.getCodigoModalidade());
			rowUpdated = ps.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteModalidade(Long codigoModalidade) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(delete)) {
			ps.setLong(1, codigoModalidade);
			rowDeleted = ps.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	private void printSQLException(SQLException exception) {
		for (Throwable e : exception) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("Código do erro: " + ((SQLException) e).getErrorCode());
				System.err.println("Mensagem: " + e.getMessage());
			}
		}
	}
}
