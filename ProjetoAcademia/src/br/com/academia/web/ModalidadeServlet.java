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

import br.com.academia.controller.ModalidadeDao;
import br.com.academia.model.Modalidade;

@WebServlet("/modalidades/*")
public class ModalidadeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ModalidadeDao modalidadeDao;

	public ModalidadeServlet() {
		super();
	}

	@Override
	public void init() {
		modalidadeDao = new ModalidadeDao();
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
			listaModalidades(request, response);
		} else {
			try {
				switch (rota) {
				case "/new":
					novoForm(request, response);
					break;
				case "/insert":
					insertModalidade(request, response);
					break;
				case "/delete":
					deleteModalidade(request, response);
					break;
				case "/update":
					updateModalidade(request, response);
					break;
				case "/edit":
					editForm(request, response);
					break;
				default:
					listaModalidades(request, response);
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private void listaModalidades(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Modalidade> listaModalidades = modalidadeDao.selectAllModalidades();
		request.setAttribute("listaModalidades", listaModalidades);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modalidades/modalidades-list.jsp");
		dispatcher.forward(request, response);
	}

	private void novoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modalidades/modalidade-form.jsp");
		dispatcher.forward(request, response);
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Modalidade achado = modalidadeDao.selectById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modalidades/modalidade-edit.jsp");
		request.setAttribute("modalidade", achado);
		dispatcher.forward(request, response);
	}

	private void insertModalidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String descricao = request.getParameter("descricao");
		Modalidade modalidade = new Modalidade(null, descricao);
		modalidadeDao.insertModalidade(modalidade);
		response.sendRedirect("");
	}

	private void updateModalidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Long codigoModalidade = Long.parseLong(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		Modalidade modalidade = new Modalidade(codigoModalidade, descricao);
		modalidadeDao.updateModalidade(modalidade);
		response.sendRedirect("");
	}

	private void deleteModalidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Long codigoModalidade = Long.parseLong(request.getParameter("id"));
		modalidadeDao.deleteModalidade(codigoModalidade);
		response.sendRedirect("");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
