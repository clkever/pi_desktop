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
 * Servlet (respons�vel por requisi��es e respostas) urlPatterns recebe
 * requisi��es da aplica��o
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
		// teste de conex�o
		dao.testeConexao();
		// a linha abaixo cria uma vari�vel que ir� receber a requisi��o
		String action = request.getServletPath();
		// teste de recebimento da requisi��o
		System.out.println(action);
		// a linha abaixo encaminha a requisi��o recebida
		if (action.equals("/main")) {
			listarNomes(request, response);
		} else if (action.equals("/insert")) {
			// encaminhar ao m�todo inserirNome()
			inserirNome(request, response);
		} else if (action.equals("/delete")) {
			removerNome(request, response);
		}
	}// fim do m�todo principal do servlet

	// m�todo respons�vel pela listagem de nomes

	protected void listarNomes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// m�todo respons�vel pela listagem de nomes
		// a liha abaixo executa o m�todo respons�vel pela listagem de nomes (passo 2
		// slide 22)
		// o objeto lista referenciado pelo ArrayList ser� usado para encaminhar os
		// nomes ao front-end(html)
		ArrayList<JavaBeans> lista = dao.listarNomes();
		// a linha abaixo recebe a lista de nomes do JavaBeans (passo 6 slide 22) e cria
		// um atributo de nome nomes para ser despachado no documento html
		request.setAttribute("nomes", lista);
		// as linhas abaixo encaminham a lista de nomes ao documento agenda.jsp (passo 7
		// do slide 22)
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}// fim do m�todo listarNomes

	// m�todo respons�vel pela inclus�o de um novo nome

	protected void inserirNome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento dos par�metros do formul�rio novo.html
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		// armazenamento dos par�metros no JavaBeans (passo 5 slide 21)
		javabeans.setNome(request.getParameter("nome"));
		javabeans.setFone(request.getParameter("fone"));
		javabeans.setEmail(request.getParameter("email"));
		// executar o m�todo DAO respons�vel pela inser��o do produto no banco de dados
		// (Passo 6 - slide 21)
		dao.adicionarNome(javabeans);
		// redirecionar para a tela principal
		response.sendRedirect("main");
	}// fim do m�todo inserirNome

	// m�todo respons�vel pela remo��o de um nome

	protected void removerNome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		// armazenar o id do cliente em JavaBeans (passo 3 slide 24)
		javabeans.setId(request.getParameter("id"));
		// executar o m�todo respons�vel pela exclus�o de um cliente (passo 4 slide 24)
		dao.excluirNome(javabeans);
		// redirecionar para a tela principal (passo 8 - slide 24)
		response.sendRedirect("main");

	}// fim do m�todo removerNome()
}
