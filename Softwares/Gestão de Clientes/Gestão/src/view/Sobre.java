package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	public Sobre() {
		getContentPane().setBackground(UIManager.getColor("CheckBox.highlight"));
		setModal(true);
		setResizable(false);
		setTitle("Gest\u00E3o de Clientes - Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/CSuper.png")));
		setBounds(100, 100, 418, 281);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema para gest\u00E3o de clientes.");
		lblNewLabel.setBounds(29, 43, 328, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor - Cleverson Martins.");
		lblNewLabel_1.setBounds(29, 69, 201, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Sobre.class.getResource("/img/gpl.png")));
		lblNewLabel_2.setBounds(309, 11, 64, 64);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Vers\u00E3o 1.0");
		lblNewLabel_3.setBounds(29, 145, 241, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sob. Licen\u00E7a  GPL");
		lblNewLabel_4.setBounds(29, 120, 145, 14);
		getContentPane().add(lblNewLabel_4);

	}

}
