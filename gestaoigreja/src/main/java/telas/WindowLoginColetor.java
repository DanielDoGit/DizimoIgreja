package telas;

import java.awt.Rectangle;
import java.sql.SQLException;

import javax.management.monitor.Monitor;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import comum.ColetaPropriedades;
import comum.EjetaException;
import dao.AutenticadorUsuario;
import dao.FabricaConexoes;

import org.eclipse.swt.widgets.Button;

public class WindowLoginColetor {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			LoginColetorActionSWT window = new LoginColetorActionSWT();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		
		createContents(display);

		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents(Display display) {
		shell = new Shell(display,  SWT.CLOSE | SWT.MIN | SWT.TITLE );
		shell.setSize(450, 300);
		shell.setText("Tela de Login Coletor");
		shell.setLayout(null);
		
		
			org.eclipse.swt.widgets.Monitor primary = display.getPrimaryMonitor();
			org.eclipse.swt.graphics.Rectangle bounds = primary.getBounds();
		    org.eclipse.swt.graphics.Rectangle rect = shell.getBounds();
		   
		    
		    int x = bounds.x + (bounds.width - rect.width) / 2;
		    int y = bounds.y + (bounds.height - rect.height) / 2;
		    
		    
		    shell.setLocation(x, y);
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBounds(35, 51, 55, 15);
		lblLogin.setText("Login");
		
		Label lblSenha = new Label(shell, SWT.NONE);
		lblSenha.setBounds(35, 109, 55, 15);
		lblSenha.setText("Senha");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(96, 51, 268, 21);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(96, 109, 268, 21);
		
		Button btnAcessar = new Button(shell, SWT.NONE);
		btnAcessar.setBounds(301, 197, 75, 25);
		btnAcessar.setText("Acessar");
		btnAcessar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!text.getText().isEmpty() && !text_1.getText().isEmpty() ) {
					
					try {
						AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
						if(AutenticadorUsuario.isAuthentiquedUserColetor(text.getText(), text_1.getText())) {
							
							shell.dispose();
							new WindowPrincipal().open();
							
						}else {
							MessageBox dialog =
								    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK );
								dialog.setText("Mensagem");
								dialog.setMessage("Este usuário não está cadastrado ou está incorreto.");
								dialog.open();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}
					
				
					
				}else {
					MessageBox dialog =
						    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK );
						dialog.setText("Mensagem");
						dialog.setMessage("Verifique se os campos foram preenchidos corretamente !");
						dialog.open();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(198, 197, 75, 25);
		btnLimpar.setText("Limpar");
		btnLimpar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text.setText("");
				text_1.setText("");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
