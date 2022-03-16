package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cliente extends JDialog {
	private JTextField txtIdCli;
	private JTextField txtCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente dialog = new Cliente();
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
	public Cliente() {
		getContentPane().setBackground(SystemColor.controlShadow);
		setTitle("Gest\u00E3o de Clientes - Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/img/CSuper.png")));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 800, 582);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("* ID");
		lblNewLabel.setBounds(10, 244, 46, 14);
		getContentPane().add(lblNewLabel);

		txtIdCli = new JTextField();
		txtIdCli.setEnabled(false);
		txtIdCli.setBounds(69, 241, 55, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("* Nome");
		lblNewLabel_1.setBounds(10, 275, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtCliente = new JTextField();
		txtCliente.setBounds(69, 272, 251, 20);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		JButton btnAdicionarCliente = new JButton("");
		btnAdicionarCliente.setToolTipText("Adicionar Cliente");
		btnAdicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionarCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/add.png")));
		btnAdicionarCliente.setBounds(382, 452, 80, 80);
		getContentPane().add(btnAdicionarCliente);

		btnEditarCliente = new JButton("");
		btnEditarCliente.setEnabled(false);
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditarCliente.setToolTipText("Editar Cliente");
		btnEditarCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/edit.png")));
		btnEditarCliente.setBounds(483, 452, 80, 80);
		getContentPane().add(btnEditarCliente);

		btnExcluirCliente = new JButton("");
		btnExcluirCliente.setEnabled(false);
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluirCliente.setToolTipText("Excluir Cliente");
		btnExcluirCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/delete.png")));
		btnExcluirCliente.setBounds(586, 452, 80, 80);
		getContentPane().add(btnExcluirCliente);

		JLabel lblNewLabel_3 = new JLabel("* Campos obrigat\u00F3rios");
		lblNewLabel_3.setBounds(10, 214, 133, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("* CPF");
		lblNewLabel_2.setBounds(406, 275, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtCpfCli = new JTextField();
		txtCpfCli.setBounds(501, 272, 112, 20);
		getContentPane().add(txtCpfCli);
		txtCpfCli.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("* Fone");
		lblNewLabel_4.setBounds(10, 310, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtFoneCli = new JTextField();
		txtFoneCli.setBounds(69, 307, 112, 20);
		getContentPane().add(txtFoneCli);
		txtFoneCli.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("* Email");
		lblNewLabel_5.setBounds(406, 310, 46, 14);
		getContentPane().add(lblNewLabel_5);

		txtEmailCli = new JTextField();
		txtEmailCli.setBounds(501, 307, 217, 20);
		getContentPane().add(txtEmailCli);
		txtEmailCli.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("* CEP");
		lblNewLabel_6.setBounds(10, 348, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtCepCli = new JTextField();
		txtCepCli.setBounds(69, 345, 95, 20);
		getContentPane().add(txtCepCli);
		txtCepCli.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("* Endere\u00E7o");
		lblNewLabel_7.setBounds(406, 348, 62, 14);
		getContentPane().add(lblNewLabel_7);

		txtEndCli = new JTextField();
		txtEndCli.setBounds(501, 345, 235, 20);
		getContentPane().add(txtEndCli);
		txtEndCli.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("* N\u00FAmero");
		lblNewLabel_8.setBounds(10, 385, 62, 14);
		getContentPane().add(lblNewLabel_8);

		txtNumeroCli = new JTextField();
		txtNumeroCli.setBounds(69, 382, 62, 20);
		getContentPane().add(txtNumeroCli);
		txtNumeroCli.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Complemento");
		lblNewLabel_9.setBounds(411, 385, 80, 14);
		getContentPane().add(lblNewLabel_9);

		txtCompCli = new JTextField();
		txtCompCli.setBounds(501, 379, 187, 20);
		getContentPane().add(txtCompCli);
		txtCompCli.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("* Cidade");
		lblNewLabel_10.setBounds(10, 420, 62, 14);
		getContentPane().add(lblNewLabel_10);

		txtCidCli = new JTextField();
		txtCidCli.setBounds(69, 417, 147, 20);
		getContentPane().add(txtCidCli);
		txtCidCli.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("* Bairro");
		lblNewLabel_11.setBounds(406, 418, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtBairroCli = new JTextField();
		txtBairroCli.setBounds(501, 412, 165, 20);
		getContentPane().add(txtBairroCli);
		txtBairroCli.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("* Estado");
		lblNewLabel_12.setBounds(10, 452, 62, 14);
		getContentPane().add(lblNewLabel_12);

		txtEstCli = new JTextField();
		txtEstCli.setBounds(69, 449, 165, 20);
		getContentPane().add(txtEstCli);
		txtEstCli.setColumns(10);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento digitar na caixa de texto
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(69, 23, 224, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(Cliente.class.getResource("/img/lupa color.png")));
		lblNewLabel_13.setBounds(10, 23, 32, 32);
		getContentPane().add(lblNewLabel_13);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 66, 677, 131);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 677, 131);
		desktopPane.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tableCliente);

	}// fim do construtor

	// criar objeto para reutilizar a classe DAO(Conexao com o banco)
	DAO dao = new DAO();
	private JTextField txtCpfCli;
	private JTextField txtFoneCli;
	private JTextField txtEmailCli;
	private JTextField txtCepCli;
	private JTextField txtEndCli;
	private JTextField txtNumeroCli;
	private JTextField txtCompCli;
	private JTextField txtCidCli;
	private JTextField txtBairroCli;
	private JTextField txtEstCli;
	private JTextField txtPesquisar;
	private JTable tableCliente;
	private JButton btnEditarCliente;
	private JButton btnExcluirCliente;

	// metodo para inserir um novo cliente
	private void adicionarCliente() {
		// validacao dos campos obrigatorios
		if (txtCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente ");
			txtCliente.requestFocus();
		} else if (txtCpfCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o cpf do cliente ");
			txtCpfCli.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone do cliente ");
			txtFoneCli.requestFocus();
		} else if (txtEmailCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o email do cliente ");
			txtEmailCli.requestFocus();
		} else if (txtCepCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o cep do cliente ");
			txtCepCli.requestFocus();
		} else if (txtEndCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do cliente ");
			txtEndCli.requestFocus();
		} else if (txtNumeroCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do cliente ");
			txtNumeroCli.requestFocus();
		} else if (txtCidCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do cliente ");
			txtCidCli.requestFocus();
		} else if (txtBairroCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do cliente ");
			txtBairroCli.requestFocus();
		} else if (txtEstCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estado do cliente ");
			txtEstCli.requestFocus();
		} else {
			// logica principal
			// String (query) SQL
			String create = "insert into clientes (nome,cpf,fone,email,cep,endereco,numero,complemento,cidade,bairro,estado) values(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// abrir a conexao com o banco
				Connection con = dao.conectar();
				// preparar a conexao com a instrucao sql (query)
				PreparedStatement pst = con.prepareStatement(create);
				// substituir os parametros (?,?) pelo conteudo das caixas texto
				pst.setString(1, txtCliente.getText());
				pst.setString(2, txtCpfCli.getText());
				pst.setString(3, txtFoneCli.getText());
				pst.setString(4, txtEmailCli.getText());
				pst.setString(5, txtCepCli.getText());
				pst.setString(6, txtEndCli.getText());
				pst.setString(7, txtNumeroCli.getText());
				pst.setString(8, txtCompCli.getText());
				pst.setString(9, txtCidCli.getText());
				pst.setString(10, txtBairroCli.getText());
				pst.setString(11, txtEstCli.getText());
				// executar a query confirmando a inclusao do cliente
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.");
				}
				// encerrar a conexao
				con.close();
				// limpar os campos
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// EDITAR CLIENTE (CRUD Update) EDITAR CLIENTE CADASTRADO
	private void editarCliente() {
		// confirmar a exclusao do usuario
		int editar = JOptionPane.showConfirmDialog(null, "Deseja realmente Editar o usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (editar == JOptionPane.YES_OPTION)
			// validacao dos campos obrigatorios
			if (txtCliente.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
				txtCliente.requestFocus();
			} else if (txtCpfCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o CPF");
				txtCpfCli.requestFocus();
			} else if (txtFoneCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Fone");
				txtFoneCli.requestFocus();

			} else if (txtEmailCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Email");
				txtEmailCli.requestFocus();

			} else if (txtCepCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o CEP");
				txtCepCli.requestFocus();
			} else if (txtEndCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Endereço");
				txtEndCli.requestFocus();
			} else if (txtNumeroCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Número residencial");
				txtNumeroCli.requestFocus();
			} else if (txtCidCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha a Cidade");
				txtCidCli.requestFocus();
			} else if (txtBairroCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Bairro");
				txtBairroCli.requestFocus();
			} else if (txtEstCli.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Estado");
				txtEstCli.requestFocus();

			} else {
				// instrucao sql para Editar usuario o mesmo comando que damos no sql para
				// Editar um usuário
				String update = "update clientes set nome=?,cpf=?,fone=?,email=?,cep=?,endereco=?,numero=?,complemento=?,cidade=?,bairro=?,estado=? where idcli=?";
				try {
					// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
					// do sql abrindo a conexão
					Connection con = dao.conectar();
					// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
					// cadastrado no sql cada
					PreparedStatement pst = con.prepareStatement(update);
					// substituir parametros setamos o 2 de acordo com a posição de cadastro de
					// usuário no sql (?)

					pst.setString(1, txtCliente.getText());
					pst.setString(2, txtCpfCli.getText());
					pst.setString(3, txtFoneCli.getText());
					pst.setString(4, txtEmailCli.getText());
					pst.setString(5, txtCepCli.getText());
					pst.setString(6, txtEndCli.getText());
					pst.setString(7, txtNumeroCli.getText());
					pst.setString(8, txtCompCli.getText());
					pst.setString(9, txtCidCli.getText());
					pst.setString(10, txtBairroCli.getText());
					pst.setString(11, txtEstCli.getText());
					pst.setString(12, txtIdCli.getText());

					// exibir uma caixa de mensagem mostrando que o usuário foi editado com sucesso.
					int confirma = pst.executeUpdate();
					if (confirma == 1) {
						JOptionPane.showMessageDialog(null, "Dados de Cliente alterado com Sucesso");
					}
					// criando acao limpar caixa de texto quando os dados forem cadastrado
					limpar();

					con.close();
					// a linha abaixo trata o problema do campo unique no cadastro de cliente ,
					// devolvendo uma mensagem amigavel ao usuario se o Cliente ja existir
				} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "CPF ou Email Já Registrado\nCadastre novamente ");
					txtCpfCli.setText(null);
					txtCpfCli.requestFocus();
					txtEmailCli.setText(null);
					txtEmailCli.requestFocus();
				}

				catch (Exception e) {
					System.out.println(e);
				}
			}

	}

	// excluir cliente (CRUD Delete)
	private void excluirCliente() {
		// confirmar a exclusao do cliente
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// instrucao sql para excluir um cliente
			String delete = "delete from clientes where idCli=?";
			try {
				// estabelecer uma conexao atraves da classe DAO
				Connection con = dao.conectar();
				// preparar a intrucao sql
				PreparedStatement pst = con.prepareStatement(delete);
				// substiruir parametros (?)
				pst.setString(1, txtIdCli.getText());
				// exibir um caixa de mensagem caso o usuario seja excluido
				int verifica = pst.executeUpdate();
				if (verifica == 1) {
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				}
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// pesquisa avancada de clientes usando a biblioteca rs2xml
	private void pesquisarCliente() {
		String read = "select * from clientes where nome like ? order by nome";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar.getText() + "%");
			// obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// setar os campos do formulario com o conteudo da tabela
	private void setarCampos() {
		int setar = tableCliente.getSelectedRow();
		// (setar, 0) 0 -> 1º campo da tabela | 1 -> 2º campo da tabela ...
		txtIdCli.setText(tableCliente.getModel().getValueAt(setar, 0).toString());
		txtCliente.setText(tableCliente.getModel().getValueAt(setar, 1).toString());
		txtCpfCli.setText(tableCliente.getModel().getValueAt(setar, 2).toString());
		txtFoneCli.setText(tableCliente.getModel().getValueAt(setar, 3).toString());
		txtEmailCli.setText(tableCliente.getModel().getValueAt(setar, 4).toString());
		txtCepCli.setText(tableCliente.getModel().getValueAt(setar, 5).toString());
		txtEndCli.setText(tableCliente.getModel().getValueAt(setar, 6).toString());
		txtNumeroCli.setText(tableCliente.getModel().getValueAt(setar, 7).toString());
		txtCompCli.setText(tableCliente.getModel().getValueAt(setar, 8).toString());
		txtCidCli.setText(tableCliente.getModel().getValueAt(setar, 9).toString());
		txtBairroCli.setText(tableCliente.getModel().getValueAt(setar, 10).toString());
		txtEstCli.setText(tableCliente.getModel().getValueAt(setar, 11).toString());
		btnEditarCliente.setEnabled(true);
		btnExcluirCliente.setEnabled(true);
	}

	// limpar os campos e gerenciar os botoes
	private void limpar() {
		// posicionar o cursor na caixa ID
		txtIdCli.requestFocus();
		txtCliente.setText(null);
		txtCpfCli.setText(null);
		txtFoneCli.setText(null);
		txtEmailCli.setText(null);
		txtCepCli.setText(null);
		txtEndCli.setText(null);
		txtNumeroCli.setText(null);
		txtCompCli.setText(null);
		txtCidCli.setText(null);
		txtBairroCli.setText(null);
		txtEstCli.setText(null);

	}

}
