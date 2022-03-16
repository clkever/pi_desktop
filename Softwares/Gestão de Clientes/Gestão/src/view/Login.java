package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Construtor
	 */
	public Login() {
		setBackground(UIManager.getColor("Button.shadow"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/CSuper.png")));
		setResizable(false);
		setTitle("Gest\u00E3o de Clientes - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 307);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(54, 55, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(101, 52, 229, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(54, 108, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(101, 105, 229, 20);
		contentPane.add(txtSenha);

		JButton btnEntrar = new JButton("Logar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(54, 180, 89, 23);
		contentPane.add(btnEntrar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dberror.png")));
		lblStatus.setBounds(367, 11, 32, 32);
		contentPane.add(lblStatus);
	} // fim do construtor

	DAO dao = new DAO();
	private JLabel lblStatus;

	private void status() {
		try {

			Connection con = dao.conectar();

			if (con == null) {

				System.out.println("Erro de conexão");
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dberror.png")));
			} else {

				System.out.println("Conexão estabelecida com sucesso");
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dbok.png")));
			}
			// encerrar a conexao
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// login no sistema
	private void logar() {
		// validacao de campos obrigatorios
		if (txtLogin.getText().isEmpty()) {
			// caixa de mensagem
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			// posicionar o cursor na caixa de texto do usuario
			txtLogin.requestFocus();
		} else {
			// logica principal de login
			try {
				String read = "select * from usuarios where login=? and senha=md5(?)";
				// abrir a conexao
				Connection con = dao.conectar();
				// uso do PrepareStatement (JDBC) para substituir as ? pelo conteudo das caixas
				// de texto
				PreparedStatement pst = con.prepareStatement(read);
				// substituir as ? pelo conteudo das caixas de texto
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				// uso do ResutSet para obter os dados do banco
				ResultSet rs = pst.executeQuery();
				// se existir usuario cadastrado com login e senha
				if (rs.next()) {
					// ativar a tela principal
					Principal principal = new Principal();
					principal.setVisible(true);
					this.dispose(); // fechar a tela de login
				} else {
					JOptionPane.showMessageDialog(null, "login e/ou senha inválido(s)");
				}
				con.close(); // encerrar a conexao
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}