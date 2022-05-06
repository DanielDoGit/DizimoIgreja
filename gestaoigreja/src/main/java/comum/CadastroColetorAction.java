package comum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;

public class CadastroColetorAction extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6, textField_7;
	private MaskFormatter formatoDataNascimento, formatoCPF;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroColetorAction frame = new CadastroColetorAction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroColetorAction() {
		
		setResizable(false);
		setTitle("Cadastrar Coletor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 573);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(168, 10, 52, 18);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(220, 8, 51, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(87, 52, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.CYAN);
		textField_1.setBounds(151, 50, 298, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(89, 78, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		try {
			formatoDataNascimento = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			formatoCPF = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_3 = new JLabel("Data Nascimento");
		lblNewLabel_3.setBounds(12, 142, 123, 14);
		contentPane.add(lblNewLabel_3);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(formatoDataNascimento);
		formattedTextField.setBounds(151, 140, 120, 20);
		formattedTextField.setHorizontalAlignment(formattedTextField.CENTER);
		contentPane.add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(formatoCPF);
		formattedTextField_1.setBounds(151, 81, 120, 20);
		formattedTextField_1.setHorizontalAlignment(formattedTextField.CENTER);
		contentPane.add(formattedTextField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 112, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("RG");
		lblNewLabel_4.setBounds(89, 109, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cidade");
		lblNewLabel_5.setBounds(75, 230, 52, 14);
		contentPane.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.CYAN);
		textField_3.setBounds(151, 228, 209, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Celular");
		lblNewLabel_6.setBounds(75, 172, 75, 14);
		contentPane.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(150, 170, 137, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6_1 = new JLabel("Telefone");
		lblNewLabel_6_1.setBounds(58, 199, 75, 14);
		contentPane.add(lblNewLabel_6_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(151, 202, 137, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_7 = new JLabel("Endere\u00E7o");
		lblNewLabel_7.setBounds(58, 261, 75, 14);
		contentPane.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(151, 259, 298, 20);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_8 = new JLabel("N\u00BA");
		lblNewLabel_8.setBounds(104, 292, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(151, 290, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Observa\u00E7\u00F5es");
		lblNewLabel_9.setBounds(35, 441, 98, 14);
		contentPane.add(lblNewLabel_9);
		
		
		JTextArea textArea = new JTextArea();
		
		//contentPane.add(textArea);
		
		JScrollPane barraDeRolagem = new JScrollPane(textArea);
		barraDeRolagem.setBounds(151, 414, 298, 70);
		contentPane.add(barraDeRolagem);
		
		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.setBounds(396, 500, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(273, 500, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pesquisar");
		btnNewButton_2.setBounds(379, 226, 105, 23);
		contentPane.add(btnNewButton_2);
		
		JCheckBox chckbxAtivo = new JCheckBox("Ativo");
		chckbxAtivo.setBounds(290, 365, 129, 23);
		contentPane.add(chckbxAtivo);
		
		JLabel lblComunidade = new JLabel("Comunidade");
		lblComunidade.setBounds(35, 322, 98, 14);
		contentPane.add(lblComunidade);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBackground(Color.CYAN);
		textField_8.setBounds(151, 320, 209, 20);
		contentPane.add(textField_8);
		
		JButton btnNewButton_2_1 = new JButton("Pesquisar");
		btnNewButton_2_1.setBounds(379, 318, 105, 23);
		contentPane.add(btnNewButton_2_1);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.CYAN);
		textField_9.setBounds(151, 352, 114, 19);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBackground(Color.CYAN);
		textField_10.setBounds(151, 383, 114, 19);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(58, 354, 70, 15);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(58, 385, 70, 15);
		contentPane.add(lblSenha);
	}
}
