package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	// parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cadastro";
	private String user = "root";
	private String password = "X@*#1928";

	// metodo de conexao (retorna a conexao)
	private Connection conectar() {
		// criar um objeto para estabelecer a conexao
		Connection con = null;
		// tratamento de excecoes
		try {
			// uso do driver para conectar com o banco(estabeler a conexao)
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// exibir a excecao
			System.out.println(e);
			return null;
		}
	}

	// teste de conex�o com o banco
	public void testeConexao() {
		try {
			// abrir a conex�o com o banco
			Connection con = conectar();
			if (con == null) {
				System.out.println("Erro de conex�o");
			} else {
				System.out.println("Banco conectado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do m�todo teste de conex�o

	// a linha abaixo cria um objeto para acessar o JavaBeans
	JavaBeans javabeans = new JavaBeans();

	// m�todo respons�vel pela inser��o de contatos no banco

	// (JavaBeans javabeans) - refer�ncia ao JavaBeans
	public void adicionarNome(JavaBeans javabeans) {
		String create = "insert into contatos(nome,fone,email) values(?,?,?)";
		try {
			// abrir a conex�o com o banco
			Connection con = conectar();
			// preparar a query(sql) para substituir os par�metros(?,?,?) pelos valores
			// armazenados temporariamente em JavaBeans
			PreparedStatement pst = con.prepareStatement(create);
			// pst.setString (grava no banco)
			// javabeans.get... (recupera o valor do JavaBeans)
			// passos 7 e 8 do slide 21
			pst.setString(1, javabeans.getNome());
			pst.setString(2, javabeans.getFone());
			pst.setString(3, javabeans.getEmail());
			// a linha abaixo efetua o insert no banco (passo 9 - slide 21)
			pst.executeUpdate();
			// fechar a conex�o com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do m�todo adicionarNome();
		// m�todo respons�vel pela lista de nomes
		// a linha abaixo cria um m�todo referenciando o JavaBeans
		// este m�todo tem retorno
		// ArrayList (vetor din�mico)

	public ArrayList<JavaBeans> listarNomes() {
		// a linha abaixo criar um objeto que ir� receber a listagem de produtos do
		// banco de dados
		ArrayList<JavaBeans> nomes = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// abrir a conex�o com o banco
			Connection con = conectar();
			// preparar a instru��o sql
			PreparedStatement pst = con.prepareStatement(read);
			// a linha abaixo executa a query(instru��o sql) e usa o objeto rs para trazer o
			// resultado do banco
			ResultSet rs = pst.executeQuery(); // Passo 3 slide 22
			// enquanto existir no banco nomes
			while (rs.next()) {
				// as linhas abaixo trazem as informa��es do banco e armazena estas informa��es
				// nas vari�veis (id,produto,quantidade,valor) - passo 4 do slide 22
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// a linha abaixo adiciona os produtos no JavaBeans
				// ATEN��O ao uso do vetor din�mico atrav�s do objeto produtos (passo 5 do slide
				// 22)
				nomes.add(new JavaBeans(id, nome, fone, email));
			}
			con.close();
			return nomes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}// fim do m�todo listar nomes

	// m�todo respons�vel pela exclus�o de um produto
	public void excluirNome(JavaBeans javabeans) {
		String delete = "delete from contatos where id=?";
		try {
			// abrir a conex�o com o banco
			Connection con = conectar();
			// preparar a conex�o substituindo o par�metro ? pelo id (JavaBeans)
			PreparedStatement pst = con.prepareStatement(delete);
			// javabeans.getId() passo 5 - slide 24
			// pst.setString passo 6 - slide 24
			pst.setString(1, javabeans.getId());
			// passo 7 - slide 24
			pst.executeUpdate();
			// fechar a conex�o com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//fim do m�todo excluirNome

}
