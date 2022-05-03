package comum;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginAction extends JFrame{

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
					LoginAction frame = new LoginAction();
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
	public LoginAction() {
		setTitle("Tela de Login");
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
		btnNewButton.setBounds(313, 195, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Login();
				//setVisible(false);
			//	setDefaultCloseOperation(EXIT_ON_CLOSE);
				//setEnabled(false);
				dispose();
			}
		});
		
		
		btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(200, 195, 89, 23);
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
