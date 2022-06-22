package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;

public abstract class WindowComunidade {

	
	protected Shell shell;
	protected Label lblNome;
	protected Text textNomeFantasia;
	protected Label lblNewLabel;
	protected Text text_2;
	protected Text textCNPJ;
	protected Text text_4;
	protected FormattedText formattedText;
	protected Text textCidade;
	protected Label lblEndereo;
	protected Text textEndereco;
	protected Text textNumeroLogradouro;
	protected Text textObservacoes;
	protected Text textRazaoSocial;
	protected Label lblRazoSocial;
	protected ToolBar toolBar;
	protected ToolItem tltmPesquisar;
	protected Button btnGravar;
	protected Button btnLimpar;
	protected Text textCodigo;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			WindowComunidade window = new WindowComunidade();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 */
	

	
	
	protected void open() throws Exception{
		Display display = Display.getDefault();
		
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
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
	 */
	protected void createContents() throws Exception {
		shell = new Shell(SWT.CLOSE | SWT.MIN);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		shell.setSize(563, 426);
		
		shell.setText("Comunidade");
		
		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCdigo.setBounds(206, 23, 41, 15);
		lblCdigo.setText("Código");
		
		textCodigo = new Text(shell, SWT.BORDER | SWT.CENTER);
		textCodigo.setVisible(true);
		textCodigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textCodigo.setEnabled(false);
		textCodigo.setBounds(253, 20, 76, 21);
		
		lblNome = new Label(shell, SWT.NONE);
		lblNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNome.setBounds(21, 64, 86, 15);
		lblNome.setText("Nome Fantasia");
		
		textNomeFantasia = new Text(shell, SWT.BORDER);
		textNomeFantasia.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textNomeFantasia.setBounds(113, 61, 418, 21);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(52, 149, 41, 15);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel.setText("CNPJ");
		
		formattedText = new FormattedText(shell, SWT.BORDER);
		formattedText.setFormatter(new MaskFormatter("##.###.###/####-##"));
		formattedText.getControl().setBounds(113, 146, 120, 21);
		formattedText.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toolBar.setBounds(408, 217, 53, 30);
		
		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);		
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));
		
		Label lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCidade.setBounds(52, 217, 65, 30);
		lblCidade.setText("Cidade");
		
		textCidade = new Text(shell, SWT.BORDER);
		textCidade.setEnabled(false);
		textCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textCidade.setBounds(113, 214, 289, 21);
		
		lblEndereo = new Label(shell, SWT.NONE);
		lblEndereo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblEndereo.setBounds(52, 259, 55, 15);
		lblEndereo.setText("Endereço");
		
		textEndereco = new Text(shell, SWT.BORDER);
		textEndereco.setBounds(113, 256, 289, 21);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 190, 548, 2);
		
		Label lblN = new Label(shell, SWT.NONE);
		lblN.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblN.setBounds(415, 259, 22, 15);
		lblN.setText("Nº");
		
		textNumeroLogradouro = new Text(shell, SWT.BORDER);
		textNumeroLogradouro.setTextLimit(6);
		textNumeroLogradouro.setBounds(442, 256, 76, 21);
		
		Label lblObservaes = new Label(shell, SWT.NONE);
		lblObservaes.setText("Observações");
		lblObservaes.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblObservaes.setBounds(31, 331, 76, 15);
		
		textObservacoes = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textObservacoes.setBounds(113, 306, 289, 61);
		
		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(442, 304, 75, 25);
		btnGravar.setText("Gravar");
		
		btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(443, 342, 75, 25);
		btnLimpar.setText("Limpar");
		
		textRazaoSocial = new Text(shell, SWT.BORDER);
		textRazaoSocial.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textRazaoSocial.setBounds(113, 104, 418, 21);
		
		lblRazoSocial = new Label(shell, SWT.NONE);
		lblRazoSocial.setText("Razão Social");
		lblRazoSocial.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblRazoSocial.setBounds(31, 107, 76, 15);
		
		
		
		
		
		
		
		
		
		
	
		
		
		
	

	}
}
