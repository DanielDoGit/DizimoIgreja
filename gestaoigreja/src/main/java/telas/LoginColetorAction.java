package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginColetorAction extends JFrame{

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton,btnNewButton_1 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginColetorAction frame = new LoginColetorAction();
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
	public LoginColetorAction() {
		setTitle("Tela Login Coletor");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		lbLogin.setBounds(57, 59, 78, 31);
		contentPane.add(lbLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 65, 202, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Senha");
		lblNewLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		lblNewLabel.setBounds(57, 118, 46, 14);
		contentPane.add(lblNewLabel);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(145, 116, 202, 20);
		contentPane.add(passwordField_1);
		
		btnNewButton = new JButton("Acessar");
		btnNewButton.setBounds(249, 195, 92, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!passwordField_1.getText().isEmpty() && !passwordField.getText().isEmpty()) {
					
					dispose();
				}else {
					JOptionPane.showMessageDialog(lblNewLabel, "As credenciais digitadas são invalidas ou estao incorretas. \n Por favor tente novamente. ","Mensagem",2);
				}
				//setVisible(false);
			//	setDefaultCloseOperation(EXIT_ON_CLOSE);
				//setEnabled(false);
				
			}
		});
		
		
		btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(114, 195, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		});
	}

	
}
