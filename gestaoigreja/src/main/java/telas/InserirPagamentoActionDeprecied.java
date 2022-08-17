package telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class InserirPagamentoActionDeprecied extends JFrame {

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
					InserirPagamentoActionDeprecied frame = new InserirPagamentoActionDeprecied();
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
	public InserirPagamentoActionDeprecied() {
		setTitle("Cadastro de pagamentos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(54, 41, 90, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(154, 39, 288, 19);
		contentPane.add(textField);
		textField.setEnabled(false);
		textField.setBackground(Color.cyan);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(454, 36, 117, 25);
		btnPesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				textField.setText("Rasom Eletronica");
			}
		});
		contentPane.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(155, 70, 288, 92);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(67, 102, 70, 15);
		contentPane.add(lblDescrio);
		
		JDateChooser seletorDeDatas = new JDateChooser();
		seletorDeDatas.setBounds(154, 172, 110, 20);
		seletorDeDatas.setDate(Calendar.getInstance().getTime());
		seletorDeDatas.setDateFormatString("dd-MM-yyyy");
		contentPane.add(seletorDeDatas);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(98, 177, 46, 15);
		contentPane.add(lblData);
		
		String texto = "Forma de Pgmento";
		JLabel lblFormaDePagamento = new JLabel();
		lblFormaDePagamento.setText(texto);
		lblFormaDePagamento.setBounds(12, 193, 146, 41);
		contentPane.add(lblFormaDePagamento);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 204, 288, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(454, 243, 117, 25);
		contentPane.add(btnGravar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(325, 243, 117, 25);
		contentPane.add(btnLimpar);
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(194, 10, 70, 15);
		//lblCdigo.setEnabled(false);
		contentPane.add(lblCdigo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(255, 8, 70, 19);
		contentPane.add(textField_2);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		
	}
}
