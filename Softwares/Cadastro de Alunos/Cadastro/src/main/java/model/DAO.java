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

	// teste de conexão com o banco
	public void testeConexao() {
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			if (con == null) {
				System.out.println("Erro de conexão");
			} else {
				System.out.println("Banco conectado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método teste de conexão

	// a linha abaixo cria um objeto para acessar o JavaBeans
	JavaBeans javabeans = new JavaBeans();

	// método responsável pela inserção de contatos no banco

	// (JavaBeans javabeans) - referência ao JavaBeans
	public void adicionarNome(JavaBeans javabeans) {
		String create = "insert into contatos(nome,fone,email) values(?,?,?)";
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// preparar a query(sql) para substituir os parâmetros(?,?,?) pelos valores
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
			// fechar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método adicionarNome();
		// método responsável pela lista de nomes
		// a linha abaixo cria um método referenciando o JavaBeans
		// este método tem retorno
		// ArrayList (vetor dinâmico)

	public ArrayList<JavaBeans> listarNomes() {
		// a linha abaixo criar um objeto que irá receber a listagem de produtos do
		// banco de dados
		ArrayList<JavaBeans> nomes = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// preparar a instrução sql
			PreparedStatement pst = con.prepareStatement(read);
			// a linha abaixo executa a query(instrução sql) e usa o objeto rs para trazer o
			// resultado do banco
			ResultSet rs = pst.executeQuery(); // Passo 3 slide 22
			// enquanto existir no banco nomes
			while (rs.next()) {
				// as linhas abaixo trazem as informações do banco e armazena estas informações
				// nas variáveis (id,produto,quantidade,valor) - passo 4 do slide 22
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// a linha abaixo adiciona os produtos no JavaBeans
				// ATENÇÃO ao uso do vetor dinâmico através do objeto produtos (passo 5 do slide
				// 22)
				nomes.add(new JavaBeans(id, nome, fone, email));
			}
			con.close();
			return nomes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}// fim do método listar nomes

	// método responsável pela exclusão de um produto
	public void excluirNome(JavaBeans javabeans) {
		String delete = "delete from contatos where id=?";
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// preparar a conexão substituindo o parâmetro ? pelo id (JavaBeans)
			PreparedStatement pst = con.prepareStatement(delete);
			// javabeans.getId() passo 5 - slide 24
			// pst.setString passo 6 - slide 24
			pst.setString(1, javabeans.getId());
			// passo 7 - slide 24
			pst.executeUpdate();
			// fechar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//fim do método excluirNome

}
