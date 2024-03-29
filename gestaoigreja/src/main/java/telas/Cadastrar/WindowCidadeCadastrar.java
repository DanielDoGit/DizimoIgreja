package telas.Cadastrar;

import java.sql.SQLException;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import beans.Cidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CidadeDao;
import telas.Inicial;

public class WindowCidadeCadastrar {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button btnNewButton;
	protected Button editButton;


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

	public static void main(String[] args) {
		try {
			WindowCidadeCadastrar window = new WindowCidadeCadastrar();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
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
	protected void createContents() {
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
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(186, 10, 76, 21);
		try {
			
			CidadeDao cD = new CidadeDao();
			cD.setConnection(Inicial.startaPropertiesConnection());
			Integer i = cD.retornaCidadeIndiceMaximo().getIdCidade();
			if (i != null) {
			
				setText(Integer.toString((int)i));
			
			}else {
				setText("1");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
		
		
		Label lblNomeCidade = new Label(shell, SWT.NONE);
		lblNomeCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNomeCidade.setBounds(35, 59, 51, 15);
		lblNomeCidade.setText("Nome");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_1.setBounds(92, 56, 319, 21);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setTextLimit(2);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_2.setBounds(92, 93, 82, 21);
		
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
			
				try {
					AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(2, "Cadastrar Cidade");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes) || autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(), permissoes)) {
						Integer i;
						if (!text_1.getText().isEmpty() && !text_2.getText().isEmpty()) {
							Cidade c = new Cidade();
							c.setIdCidade(Integer.valueOf(text.getText()));
							c.setNomeCidade(text_1.getText());
							c.setUfCidade(text_2.getText());
							try {
								CidadeDao cidadeDao = new CidadeDao();
								cidadeDao.setConnection(Inicial.startaPropertiesConnection());
								cidadeDao.cadastrar(c);
								Inicial.fechaconexao();

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								new EjetaException(e1);
							}
							i = new Integer(Integer.valueOf(text.getText()) + 1);
							text.setText(i.toString());
							text_2.setText("");
							text_1.setText("");
						} else {
							PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
						} 
					}else {
						PropriedadesShell.mensagemDeRetorno("Usuário sem permissão para acessar o recurso: "+permissoes.getNomepermissao());
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
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

