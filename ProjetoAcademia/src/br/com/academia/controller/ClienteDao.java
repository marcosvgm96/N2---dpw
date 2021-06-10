package br.com.academia.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.model.Cliente;
import br.com.academia.model.ClienteCompleto;
import br.com.academia.model.Endereco;
import br.com.academia.model.Modalidade;

public class ClienteDao {

	private static final String insert = "INSERT INTO clientes (nome, matricula, \"enderecoId\", \"modalidadeId\") VALUES (?,?,?,?);";
	private static final String selectId = "SELECT nome, matricula, \"enderecoId\", \"modalidadeId\" FROM clientes WHERE id = ?";
	private static final String selectAll = "SELECT c.id, nome, matricula, \r\n"
			+ "	cep, cidade, estado, bairro, logradouro, numero, \r\n"
			+ "	m.\"codigoModalidade\" as \"Codigo modalidade\", m.descricao as \"Descricao modalidade\"\r\n"
			+ "	FROM public.clientes c\r\n JOIN public.enderecos e\r\n	ON  c.\"enderecoId\" = e.id\r\n"
			+ "	JOIN public.modalidades m\r\n	ON c.\"modalidadeId\" = m.\"codigoModalidade\"";
	private static final String delete = "DELETE FROM clientes WHERE id = ?;";
	private static final String update = "UPDATE clientes SET nome = ?, matricula = ?, \"enderecoId\" = ?, \"modalidadeId\" = ? WHERE id = ?;";

	public ClienteDao() {

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

	public void insertCliente(Cliente cliente) throws SQLException {
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(insert)) {
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getMatricula());
			ps.setLong(3, cliente.getEnderecoId());
			ps.setLong(4, cliente.getModalidadeId());
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Cliente selectById(Long id) {
		Cliente cliente = null;

		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectId)) {
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String matricula = rs.getString("matricula");
				Long endereco = rs.getLong("enderecoId");
				Long modalidade = rs.getLong("modalidadeId");
				cliente = new Cliente(id, nome, matricula, endereco, modalidade);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cliente;
	}

	public List<ClienteCompleto> selectAllClientes() {

		List<ClienteCompleto> clientes = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(selectAll)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String matricula = rs.getString("matricula");
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String bairro = rs.getString("bairro");
				String logradouro = rs.getString("logradouro");
				int numero = rs.getInt("numero");
				String descricao = rs.getString("Descricao modalidade");
				Long modalidadeId = rs.getLong("Codigo modalidade");
				Endereco endereco = new Endereco(null, cep, cidade, estado, bairro, logradouro, numero);
				Modalidade modalidade = new Modalidade(modalidadeId, descricao);
				clientes.add(new ClienteCompleto(id, nome, matricula, endereco, modalidade));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return clientes;

	}

	public boolean deleteCliente(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(delete)) {
			ps.setLong(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		}
		return rowDeleted;

	}

	public boolean updadeCliente(Cliente cliente) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(update)) {
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getMatricula());
			ps.setLong(3, cliente.getEnderecoId());
			ps.setLong(4, cliente.getModalidadeId());
			ps.setLong(5, cliente.getId());
			rowUpdated = ps.executeUpdate() > 0;

		}
		return rowUpdated;

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
