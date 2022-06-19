package telas.Editar;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import beans.Cidade;
import comum.EjetaException;
import dao.CidadeDao;
import telas.Inicial;
import telas.PropriedadesShell;

public class WindowCidadeEditar {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button btnNewButton;
	protected Button editButton;

	/**
	 * @wbp.parser.entryPoint
	 */
	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Text getText() {
		return text;
	}

	public void setText(String a) {
		this.text.setText(a);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Text getText_1() {
		return text_1;
	}

	public void setText_1(String a) {
		this.text_1.setText(a);
	}

	public Text getText_2() {
		return text_2;
	}

	public void setText_2(String a) {
		this.text_2.setText(a);
	}

	public Button getBtnNewButtonCadastrar() {
		return btnNewButton;
	}
	
	public Button getBtnNewButtonEditar() {
		return btnNewButton;
	}
	
	public Button getBtnNewButtonExcluir() {
		return btnNewButton;
	}

	public void setBtnNewButton(Button btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			WindowCidadeEditar window = new WindowCidadeEditar();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public WindowCidadeEditar(Text um, Text dois, Text tres) {
		text = um;
		text_1 = dois;
		text_2 = tres;
		
		open(text, text_1, text_2);
		
	}

	/**
	 * Open the window.
	 */
	public void open(Text um, Text dois, Text tres) {
		Display display = Display.getDefault();
		createContents(um, dois, tres);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents(Text um, Text dois, Text tres) {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shell.setSize(450, 202);
		shell.setText("Cidade");
		shell.setLocation(PropriedadesShell.centralizarShell(shell, Display.getCurrent()));
		
		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCdigo.setBounds(134, 13, 50, 15);
		lblCdigo.setText("Código");
		
		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		//text.setEditable(false);
		text.setText(um.getText());
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(186, 10, 76, 21);
		
		Label lblNomeCidade = new Label(shell, SWT.NONE);
		lblNomeCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNomeCidade.setBounds(35, 59, 51, 15);
		lblNomeCidade.setText("Nome");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_1.setBounds(92, 56, 319, 21);
		text_1.setText(dois.getText());
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setTextLimit(2);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_2.setBounds(92, 93, 82, 21);
		text_2.setText(tres.getText());
		
		Label lblUf = new Label(shell, SWT.NONE);
		lblUf.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblUf.setText("UF");
		lblUf.setBounds(35, 96, 26, 15);
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(336, 121, 75, 25);
		btnNewButton.setText("Gravar");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				
				Cidade c = new Cidade();
				c.setIdCidade(Integer.valueOf(text.getText()));
				c.setNomeCidade(text_1.getText());
				c.setUfCidade(text_2.getText());
				
				
				if (!text_1.getText().isEmpty() && !text_2.getText().isEmpty()) {
					
					try {
						CidadeDao cidadeDao = new CidadeDao();
						cidadeDao.setConnection(Inicial.startaPropertiesConnection());
						cidadeDao.editar(c);
						cidadeDao.getCon().close();
						shell.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}

				}else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
				}

			}

		});
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(236, 121, 75, 25);
		btnLimpar.setText("Limpar");
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_2.setText("");
				text_1.setText("");
			}
		
		});
		
	}
}

