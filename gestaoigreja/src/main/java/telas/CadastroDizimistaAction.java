package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Button;
import java.awt.ScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CadastroDizimistaAction extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDizimistaAction frame = new CadastroDizimistaAction();
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
	 * @throws ParseException 
	 */
	public CadastroDizimistaAction() throws ParseException {
		setResizable(false);
		
		setTitle("Castrar Dizimista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(182, 15, 60, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(241, 12, 51, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(85, 56, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.CYAN);
		textField_1.setBounds(155, 53, 298, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(85, 87, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		MaskFormatter formatoDataNascimento = new MaskFormatter("##/##/####");
		MaskFormatter formatoCPF = new MaskFormatter("###.###.###-##");
		
		JLabel lblNewLabel_3 = new JLabel("Data Nascimento");
		lblNewLabel_3.setBounds(16, 146, 129, 14);
		contentPane.add(lblNewLabel_3);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(formatoDataNascimento);
		formattedTextField.setBounds(155, 144, 120, 20);
		formattedTextField.setHorizontalAlignment(formattedTextField.CENTER);
		contentPane.add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(formatoCPF);
		formattedTextField_1.setBounds(155, 84, 120, 20);
		formattedTextField_1.setHorizontalAlignment(formattedTextField.CENTER);
		contentPane.add(formattedTextField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(155, 115, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("RG");
		lblNewLabel_4.setBounds(85, 115, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cidade");
		lblNewLabel_5.setBounds(71, 234, 60, 14);
		contentPane.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.CYAN);
		textField_3.setBounds(155, 234, 209, 20);
		contentPane.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Celular");
		lblNewLabel_6.setBounds(71, 173, 60, 20);
		contentPane.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(155, 173, 137, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6_1 = new JLabel("Telefone");
		lblNewLabel_6_1.setBounds(71, 205, 74, 14);
		contentPane.add(lblNewLabel_6_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(155, 205, 137, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_7 = new JLabel("Endere\u00E7o");
		lblNewLabel_7.setBounds(71, 263, 74, 14);
		contentPane.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(155, 263, 298, 20);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_8 = new JLabel("N\u00BA");
		lblNewLabel_8.setBounds(85, 292, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(155, 294, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Observa\u00E7\u00F5es");
		lblNewLabel_9.setBounds(48, 359, 97, 14);
		contentPane.add(lblNewLabel_9);
		
		
		JTextArea textArea = new JTextArea();
		
		//contentPane.add(textArea);
		
		JScrollPane barraDeRolagem = new JScrollPane(textArea);
		barraDeRolagem.setBounds(155, 334, 200, 70);
		contentPane.add(barraDeRolagem);
		
		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.setBounds(379, 331, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(379, 376, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pesquisar");
		btnNewButton_2.setBounds(379, 234, 105, 23);
		contentPane.add(btnNewButton_2);
		
		JCheckBox chckbxAtivo = new JCheckBox("Ativo");
		chckbxAtivo.setBounds(263, 291, 129, 23);
		contentPane.add(chckbxAtivo);
	}
}
