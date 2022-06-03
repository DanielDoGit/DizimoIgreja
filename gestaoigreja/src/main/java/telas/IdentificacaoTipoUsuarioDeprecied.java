package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class IdentificacaoTipoUsuarioDeprecied extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificacaoTipoUsuarioDeprecied frame = new IdentificacaoTipoUsuarioDeprecied();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IdentificacaoTipoUsuarioDeprecied() {
		setResizable(false);
		setTitle("Painel de Controle");
		setBounds(100, 100, 441, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JLabel lblBemVindoAo = new JLabel("Bem Vindo ao sistema de Gestao de controle de Dizimo !");
		lblBemVindoAo.setBounds(12, 26, 416, 15);
		contentPane.add(lblBemVindoAo);
		
		JLabel lblVoc = new JLabel("Você é ?");
		lblVoc.setBounds(198, 76, 70, 15);
		contentPane.add(lblVoc);
		
		JButton btnNewButton = new JButton("Coletor");
		btnNewButton.setBounds(76, 140, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Funcionario");
		btnNewButton_1.setBounds(250, 140, 117, 25);
		contentPane.add(btnNewButton_1);
	}
}
