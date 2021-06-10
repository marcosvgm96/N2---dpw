package br.com.academia.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.model.Endereco;

public class EnderecoDao {

	private static final String insert = "INSERT INTO enderecos (cep, cidade, estado, bairro, logradouro, numero) VALUES (?,?,?,?,?,?);";
	private static final String selectId = "SELECT cep, cidade, estado, bairro, logradouro, numero FROM enderecos WHERE id = ?";
	private static final String selectCep = "SELECT id FROM enderecos WHERE cep = ? AND numero = ?;";
	private static final String selectAll = "SELECT * FROM enderecos";
	private static final String delete = "DELETE FROM enderecos WHERE id = ?;";
	private static final String update = "UPDATE enderecos SET cep = ?, cidade = ?, estado = ?, bairro = ?, logradouro = ?, numero = ? WHERE id = ?;";

	public EnderecoDao() {

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

	public List<Endereco> selectAllEnderecos() {

		List<Endereco> enderecos = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectAll)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String bairro = rs.getString("bairro");
				String logradouro = rs.getString("logradouro");
				Integer numero = rs.getInt("numero");
				enderecos.add(new Endereco(id, cep, cidade, estado, bairro, logradouro, numero));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return enderecos;
	}

	public Endereco selectById(Long id) {
		Endereco endereco = null;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectId)) {
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String bairro = rs.getString("bairro");
				String logradouro = rs.getString("logradouro");
				int numero = rs.getInt("numero");
				endereco = new Endereco(id, cep, cidade, estado, bairro, logradouro, numero);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return endereco;
	}

	public Long selectByCep(String cep, int numero) {
		Long id = null;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectCep)) {
			ps.setString(1, cep);
			ps.setInt(2, numero);

			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Long FoundId = rs.getLong("id");
				id = FoundId;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return id;

	}

	public void insertEndereco(Endereco endereco) throws SQLException {
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(insert)) {
			ps.setString(1, endereco.getCep());
			ps.setString(2, endereco.getCidade());
			ps.setString(3, endereco.getEstado());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getLogradouro());
			ps.setInt(6, endereco.getNumero());
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean updateEndereco(Endereco endereco) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(update)) {
			ps.setString(1, endereco.getCep());
			ps.setString(2, endereco.getCidade());
			ps.setString(3, endereco.getEstado());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getLogradouro());
			ps.setInt(6, endereco.getNumero());
			ps.setLong(7, endereco.getId());
			rowUpdated = ps.executeUpdate() > 0;

		}
		return rowUpdated;
	}

	public boolean deleteEndereco(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(delete)) {
			ps.setLong(1, id);
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
