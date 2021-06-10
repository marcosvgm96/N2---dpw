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

import br.com.academia.controller.EnderecoDao;
import br.com.academia.model.Endereco;

@WebServlet("/enderecos/*")
public class EnderecoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EnderecoDao enderecoDao;

	public EnderecoServlet() {
		super();
	}

	@Override
	public void init() {
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
			listaEnderecos(request, response);
		} else {
			try {
				switch (rota) {
				case "/delete":
					deleteEndereco(request, response);
					break;
				case "/update":
					updateEndereco(request, response);
					break;
				case "/edit":
					editForm(request, response);
					break;
				default:
					listaEnderecos(request, response);
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private void listaEnderecos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Endereco> listaEnderecos = enderecoDao.selectAllEnderecos();
		request.setAttribute("listaEnderecos", listaEnderecos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/enderecos/enderecos-list.jsp");
		dispatcher.forward(request, response);
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Endereco achado = enderecoDao.selectById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/enderecos/endereco-edit.jsp");
		request.setAttribute("endereco", achado);
		dispatcher.forward(request, response);
	}

	private void updateEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Long id = Long.parseLong(request.getParameter("id"));
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String bairro = request.getParameter("bairro");
		String logradouro = request.getParameter("logradouro");
		int numero = Integer.parseInt(request.getParameter("numero"));
		Endereco endereco = new Endereco(id, cep, cidade, estado, bairro, logradouro, numero);
		enderecoDao.updateEndereco(endereco);
		response.sendRedirect("");

	}

	private void deleteEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Long id = Long.parseLong(request.getParameter("id"));
		enderecoDao.deleteEndereco(id);
		response.sendRedirect("");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
