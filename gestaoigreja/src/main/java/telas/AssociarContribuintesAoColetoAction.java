package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;

public class AssociarContribuintesAoColetoAction {

	private JFrame frame;
	private JButton btnNewButton;
	private JButton btnRemover;
	private JButton btnAdicionar;
	private JTable tabela;
	private DefaultTableModel modelo;

	public DefaultTableModel modelodetabelaPesquisa() {
		this.modelo = new DefaultTableModel();
		this.modelo.addColumn("Nome Contribuinte");
		this.modelo.addColumn("Data Associação");
		
		
		this.modelo.setNumRows(60);	
		
		return this.modelo;
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssociarContribuintesAoColetoAction window = new AssociarContribuintesAoColetoAction();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AssociarContribuintesAoColetoAction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 629, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 23, 601, 85);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Coletor");
		lblNewLabel.setBounds(12, 12, 70, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNomeContador = new JLabel();
		lblNomeContador.setBounds(80, 12, 225, 15);
		lblNomeContador.setText("Tarciso Almeida Ferraz Sabrino");
		panel.add(lblNomeContador);
		
		JLabel lblNewLabel_2 = new JLabel("Comunidade");
		lblNewLabel_2.setBounds(12, 58, 104, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNomeComunidade = new JLabel();
		lblNomeComunidade.setBounds(120, 58, 195, 15);
		lblNomeComunidade.setText("Santa Clara");
		panel.add(lblNomeComunidade);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(335, 12, 70, 15);
		panel.add(lblData);
		
		JLabel lblDataInformation = new JLabel();
		lblDataInformation.setBounds(385, 12, 80, 15);
		lblDataInformation.setText("04/03/2000");
		panel.add(lblDataInformation);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(85, 120, 117, 25);
		frame.getContentPane().add(btnAdicionar);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(260, 120, 117, 25);
		frame.getContentPane().add(btnRemover);
		
		btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(432, 120, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		tabela = new JTable(this.modelodetabelaPesquisa());
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tabela.getColumnCount(); i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(cellRender);
		}
		
		
		JScrollPane barraderolagem = new JScrollPane(tabela);
		barraderolagem.setBounds(12, 160, 601, 150);
		frame.getContentPane().add(barraderolagem);
		
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(480, 322, 117, 25);
		frame.getContentPane().add(btnGravar);
		
		JButton btnLimapar = new JButton("Limpar");
		btnLimapar.setBounds(341, 322, 117, 25);
		frame.getContentPane().add(btnLimapar);
		btnLimapar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}
}
