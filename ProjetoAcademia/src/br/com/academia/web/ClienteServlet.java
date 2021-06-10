package br.com.academia.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.academia.controller.ClienteDao;
import br.com.academia.controller.EnderecoDao;
import br.com.academia.model.Cliente;
import br.com.academia.model.ClienteCompleto;
import br.com.academia.model.Endereco;

@WebServlet("/clientes/*")
public class ClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClienteDao clienteDao;
	private EnderecoDao enderecoDao;

	public ClienteServlet() {
		super();
	}

	@Override
	public void init() {
		clienteDao = new ClienteDao();
		enderecoDao = new EnderecoDao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		String teste = request.getPathInfo();
		System.out.println(url);
		System.out.println(teste);

		String rota = teste;
		if (rota == null) {
			listaClientes(request, response);
		} else {
			try {
				switch (rota) {
				case "/new":
					novoForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteCliente(request, response);
					break;
				case "/update":
					updateCliente(request, response);
					break;
				case "/edit":
					editForm(request, response);
					break;
				default:
					listaClientes(request, response);
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private void listaClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ClienteCompleto> listaClientes = clienteDao.selectAllClientes();
		request.setAttribute("listaClientes", listaClientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/clientes-list.jsp");
		dispatcher.forward(request, response);
	}

	private void novoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/cliente-form.jsp");
		dispatcher.forward(request, response);
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());
		Long id = Long.parseLong(request.getParameter("id"));
		Cliente achado = clienteDao.selectById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/cliente-edit.jsp");
		request.setAttribute("cliente", achado);
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String bairro = request.getParameter("bairro");
		String logradouro = request.getParameter("logradouro");
		String numeroRec = request.getParameter("numero");
		int numero = Integer.parseInt(numeroRec);
		String CodMod = request.getParameter("modalidadeId");
		Long ModalidadeId = Long.parseLong(CodMod);
		Endereco endereco = new Endereco(null, cep, cidade, estado, bairro, logradouro, numero);

		try {
			enderecoDao.insertEndereco(endereco);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Long enderecoId = enderecoDao.selectByCep(cep, numero);
		try {
			Cliente cliente = new Cliente(null, nome, matricula, enderecoId, ModalidadeId);
			clienteDao.insertCliente(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("");

	}

	private void updateCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println(request.getPathInfo());
		Long id = Long.parseLong(request.getParameter("id"));
		System.out.println(id);
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String enderecoId = request.getParameter("enderecoId");
		Long endereco = Long.parseLong(enderecoId);
		String modalidadeId = request.getParameter("modalidadeId");
		Long modalidade = Long.parseLong(modalidadeId);
		Cliente client = new Cliente(id, nome, matricula, endereco, modalidade);
		clienteDao.updadeCliente(client);
		response.sendRedirect("");

	}

	private void deleteCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println(request.getPathInfo());
		Long id = Long.parseLong(request.getParameter("id"));
		clienteDao.deleteCliente(id);
		response.sendRedirect("");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
