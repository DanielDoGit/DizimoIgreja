package telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CadastrarRecebimentoDizimistaAction extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tabela;

	
	public DefaultTableModel modelodetabelaPesquisa() {
		this.modelo = new DefaultTableModel();
		this.modelo.addColumn("Nome Contribuinte");
		this.modelo.addColumn("Valor");
		this.modelo.addColumn("Data Contribuição");
		
		
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
					CadastrarRecebimentoDizimistaAction frame = new CadastrarRecebimentoDizimistaAction();
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
	public CadastrarRecebimentoDizimistaAction() {
		setResizable(false);
		setTitle("Cadastrar recebimento Dizimos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 534, 75);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(12, 48, 36, 15);
		panel.add(lblTotal);
		
		JLabel lblColetor = new JLabel("Coletor");
		lblColetor.setBounds(12, 12, 94, 15);
		panel.add(lblColetor);
		
		JLabel lblQuantidadeRecebidos = new JLabel("QTD Recebimentos");
		lblQuantidadeRecebidos.setBounds(305, 12, 157, 15);
		panel.add(lblQuantidadeRecebidos);
		
		JLabel lblData = new JLabel("Data de Abertura");
		lblData.setBounds(307, 48, 157, 15);
		panel.add(lblData);
		
		tabela = new JTable(this.modelodetabelaPesquisa());
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tabela.getColumnCount(); i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(cellRender);
		}
		
		JScrollPane barraderolagem = new JScrollPane(tabela);
		barraderolagem.setBounds(12, 99, 534, 151);
		getContentPane().add(barraderolagem);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(429, 262, 117, 25);
		contentPane.add(btnGravar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(288, 262, 117, 25);
		contentPane.add(btnLimpar);
	}
}
