package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet (responsável por requisições e respostas) urlPatterns recebe
 * requisições da aplicação
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// criar objetos para acessar a camada model (JavaBeans e DAO)
	JavaBeans javabeans = new JavaBeans();
	DAO dao = new DAO();

	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de conexão
		dao.testeConexao();
		// a linha abaixo cria uma variável que irá receber a requisição
		String action = request.getServletPath();
		// teste de recebimento da requisição
		System.out.println(action);
		// a linha abaixo encaminha a requisição recebida
		if (action.equals("/main")) {
			listarNomes(request, response);
		} else if (action.equals("/insert")) {
			// encaminhar ao método inserirNome()
			inserirNome(request, response);
		} else if (action.equals("/delete")) {
			removerNome(request, response);
		}
	}// fim do método principal do servlet

	// método responsável pela listagem de nomes

	protected void listarNomes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// método responsável pela listagem de nomes
		// a liha abaixo executa o método responsável pela listagem de nomes (passo 2
		// slide 22)
		// o objeto lista referenciado pelo ArrayList será usado para encaminhar os
		// nomes ao front-end(html)
		ArrayList<JavaBeans> lista = dao.listarNomes();
		// a linha abaixo recebe a lista de nomes do JavaBeans (passo 6 slide 22) e cria
		// um atributo de nome nomes para ser despachado no documento html
		request.setAttribute("nomes", lista);
		// as linhas abaixo encaminham a lista de nomes ao documento agenda.jsp (passo 7
		// do slide 22)
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}// fim do método listarNomes

	// método responsável pela inclusão de um novo nome

	protected void inserirNome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento dos parâmetros do formulário novo.html
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		// armazenamento dos parâmetros no JavaBeans (passo 5 slide 21)
		javabeans.setNome(request.getParameter("nome"));
		javabeans.setFone(request.getParameter("fone"));
		javabeans.setEmail(request.getParameter("email"));
		// executar o método DAO responsável pela inserção do produto no banco de dados
		// (Passo 6 - slide 21)
		dao.adicionarNome(javabeans);
		// redirecionar para a tela principal
		response.sendRedirect("main");
	}// fim do método inserirNome

	// método responsável pela remoção de um nome

	protected void removerNome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		// armazenar o id do cliente em JavaBeans (passo 3 slide 24)
		javabeans.setId(request.getParameter("id"));
		// executar o método responsável pela exclusão de um cliente (passo 4 slide 24)
		dao.excluirNome(javabeans);
		// redirecionar para a tela principal (passo 8 - slide 24)
		response.sendRedirect("main");

	}// fim do método removerNome()
}
