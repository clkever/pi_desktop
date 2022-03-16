package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Usuario extends JDialog {
	private JTextField txtUsuario;
	private JTextField txtId;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario dialog = new Usuario();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuario() {
		getContentPane().setBackground(SystemColor.controlShadow);
		setResizable(false);
		setTitle("Gest\u00E3o de Clientes - Usu\u00E1rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/img/CSuper.png")));
		setModal(true);
		setBounds(100, 100, 582, 403);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("* Usu\u00E1rio");
		lblNewLabel.setBounds(88, 91, 65, 14);
		getContentPane().add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(154, 88, 321, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(88, 53, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(154, 50, 122, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("* Login");
		lblNewLabel_2.setBounds(88, 134, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(154, 131, 207, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(88, 179, 46, 14);
		getContentPane().add(lblNewLabel_3);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setEnabled(false);
		btnAdicionar.setToolTipText("Adicionar Usu\u00E1rio");
		btnAdicionar.setIcon(new ImageIcon(Usuario.class.getResource("/img/add.png")));
		btnAdicionar.setBounds(154, 253, 80, 80);
		getContentPane().add(btnAdicionar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(154, 176, 207, 20);
		getContentPane().add(txtSenha);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar usu\u00E1rio");
		btnPesquisar.setIcon(new ImageIcon(Usuario.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setBounds(244, 253, 80, 80);
		getContentPane().add(btnPesquisar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setToolTipText("Editar usu\u00E1rio");
		btnEditar.setIcon(new ImageIcon(Usuario.class.getResource("/img/edit.png")));
		btnEditar.setBounds(334, 253, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setToolTipText("Excluir Usu\u00E1rio");
		btnExcluir.setIcon(new ImageIcon(Usuario.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(424, 253, 80, 80);
		getContentPane().add(btnExcluir);

		JLabel lblNewLabel_4 = new JLabel("* Campos obrigat\u00F3rios");
		lblNewLabel_4.setBounds(350, 21, 141, 14);
		getContentPane().add(lblNewLabel_4);

	}// fim do construtor

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JButton btnExcluir;

	// pesquisar usuario
	private void pesquisarUsuario() {
		// validacao
		// se o campo txtId estiver vazio
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID");
			txtId.requestFocus();
		} else {
			// instrucao sql para pesquisar um usuario
			String read = "select * from usuarios where id=?";
			// tratamento de excecoes(ex: servidor indisponivel)
			try {
				// estabelecer uma conexao atraves da classe DAO
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(read);
				// substituir parametros (?)
				pst.setString(1, txtId.getText());
				// resultado (executar a query e obter os dados)
				ResultSet rs = pst.executeQuery();
				// se existir este usuario no banco
				if (rs.next()) {
					// setar campos
					txtUsuario.setText(rs.getString(2));
					txtLogin.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					// gerenciar botoes (UX)
					btnPesquisar.setEnabled(false);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					// desativar o txtId
					txtId.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
					// limpar o campo ID
					txtId.setText(null);
					// gerenciar os botoes
					btnAdicionar.setEnabled(true);
					btnPesquisar.setEnabled(false);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
					// desabilitar o txtId e posicionar o cursos no usuario
					txtId.setEnabled(false);
					txtUsuario.requestFocus();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// adicionar usuario (CRUD Create)
	private void adicionarUsuario() {
		// validacao dos campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else {
			// instrucao sql para inserir um usuario
			String create = "insert into usuarios(usuario,login,senha) values (?,?,md5(?))";
			try {
				// estabelecer uma conexao atraves da classe DAO
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(create);
				// substituir parametros (?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				// exibir um caixa de mensagem caso o usuario seja inserido
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
				}
				limpar();
				con.close();
				// a linha abaixo trata o problema do campo unique no login, devolvendo uma mensagem amigavel ao usuario se o login ja existir
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já existente\nCadastre outro login");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} 
			
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// editar usuario (CRUD Update)
	private void editarUsuario() {
		// validacao
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else {
			// instrucao sql para editar um usuario
			String update = "update usuarios set usuario=?,login=?,senha=md5(?) where id=?";
			try {
				// estabelecer uma conexao atraves da classe DAO
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(update);
				// substituir parametros (?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtId.getText());
				// exibir um caixa de mensagem caso o usuario seja editado
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
				}
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// excluir usuario (CRUD Delete)
	private void excluirUsuario() {
		// confirmar a exclusao do usuario
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// instrucao sql para excluir um usuario
			String delete = "delete from usuarios where id=?";
			try {
				// estabelecer uma conexao atraves da classe DAO
				Connection con = dao.conectar();
				// preparar a intrucao sql
				PreparedStatement pst = con.prepareStatement(delete);
				// substiruir parametros (?)
				pst.setString(1, txtId.getText());
				// exibir um caixa de mensagem caso o usuario seja excluido
				int verifica = pst.executeUpdate();
				if (verifica == 1) {
					JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso");
				}
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// limpar campos e gerenciar os botoes
	private void limpar() {
		// habilitar a pesquisa por ID
		txtId.setEnabled(true);
		// posicionar o cursor na caixa ID
		txtId.requestFocus();
		// limpar as caixas de texto
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		// ativar o botao de pesquisa
		btnPesquisar.setEnabled(true);
		// desativar os demais botoes
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}