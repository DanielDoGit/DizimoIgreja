package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InserirPagamento extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirPagamento frame = new InserirPagamento();
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
	public InserirPagamento() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(33, 12, 111, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(134, 10, 288, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(429, 7, 117, 25);
		contentPane.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 39, 288, 92);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(33, 74, 70, 15);
		contentPane.add(lblDescrio);
		
		JDateChooser seletorDeDatas = new JDateChooser();
		seletorDeDatas.setBounds(134, 143, 90, 20);
		contentPane.add(seletorDeDatas);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(33, 143, 70, 15);
		contentPane.add(lblData);
		
		String texto = "<html> Forma de <br> pagamento </html>";
		JLabel lblFormaDePagamento = new JLabel();
		lblFormaDePagamento.setText(texto);
		lblFormaDePagamento.setBounds(33, 175, 102, 41);
		contentPane.add(lblFormaDePagamento);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 175, 288, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	}
}
