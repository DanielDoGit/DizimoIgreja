package comum;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class CidadeAction extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CidadeAction frame = new CidadeAction();
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
	public CidadeAction() {
		setTitle("Cadastrar Cidade");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(116, 12, 70, 15);
		contentPane.add(lblCdigo);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(188, 10, 78, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.CYAN);
		textField_1.setBounds(92, 58, 302, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(26, 60, 70, 15);
		contentPane.add(lblCidade);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(49, 86, 70, 15);
		contentPane.add(lblUf);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.CYAN);
		textField_2.setBounds(92, 89, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(300, 120, 117, 25);
		contentPane.add(btnGravar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(171, 120, 117, 25);
		contentPane.add(btnLimpar);
	}

}
