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

public class WindowDizimista {

	protected Shell shell;
	private Text text;
	private Label lblNome;
	private Text text_1;
	private Label lblNewLabel;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private FormattedText formattedText;
	private Text text_5;
	private Label lblRg;
	private Label lblDataNascimento;
	private Text text_6;
	private Text text_7;
	private Label lblContatos;
	private Label lblTelefone;
	private Text text_8;
	private Label lblCelular;
	private Text text_9;
	private Text text_10;
	private Label lblEndereo;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowDizimista window = new WindowDizimista();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	
	
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
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
	protected void createContents(Display display) {
		shell = new Shell(display , SWT.CLOSE | SWT.MIN);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		shell.setSize(580, 542);
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		shell.setText("Cadastrar Dizimista");
		
		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCdigo.setBounds(192, 20, 55, 15);
		lblCdigo.setText("Código");
		
		text = new Text(shell, SWT.BORDER | SWT.CENTER);
		text.setVisible(true);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setEnabled(false);
		text.setBounds(253, 20, 76, 21);
		
		lblNome = new Label(shell, SWT.NONE);
		lblNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNome.setBounds(52, 64, 55, 15);
		lblNome.setText("Nome");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_1.setBounds(117, 61, 431, 21);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(52, 108, 55, 15);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel.setText("CPF");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(117, 108, 134, 21);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_3.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				Text t = (Text) e.getSource();
				t.selectAll();
				text_3 = t;
				formattedText = new FormattedText(text_3);
				formattedText.setFormatter(new MaskFormatter("###.###.###-##"));
				text_3 = formattedText.getControl();

			}
			 
			
		});
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBounds(117, 151, 134, 21);
		
		lblRg = new Label(shell, SWT.NONE);
		lblRg.setText("RG");
		lblRg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblRg.setBounds(52, 154, 55, 15);
		
		lblDataNascimento = new Label(shell, SWT.NONE);
		lblDataNascimento.setBounds(10, 197, 97, 15);
		lblDataNascimento.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblDataNascimento.setText("Data Nascimento");
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setBounds(117, 194, 134, 21);
		text_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Text t = (Text) e.getSource();
				t.selectAll();
				text_6 = t;
				formattedText = new FormattedText(text_6);
				formattedText.setFormatter(new MaskFormatter("##/##/####"));
				text_6 = formattedText.getControl();
				
			}
		});
		
		text_7 = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_7.setBounds(366, 108, 182, 104);
		
		lblContatos = new Label(shell, SWT.NONE);
		lblContatos.setBounds(305, 154, 55, 15);
		lblContatos.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblContatos.setText("Contatos");
		
		lblTelefone = new Label(shell, SWT.NONE);
		lblTelefone.setBounds(41, 244, 55, 15);
		lblTelefone.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblTelefone.setText("Telefone");
		
		text_8 = new Text(shell, SWT.BORDER);
		text_8.setBounds(117, 241, 134, 21);
		text_8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Text t = (Text) e.getSource();
				t.selectAll();
				text_8 = t;
				formattedText = new FormattedText(text_8);
				formattedText.setFormatter(new MaskFormatter("(##) ####-####"));
				text_8 = formattedText.getControl();
				
			}
		});
		
		lblCelular = new Label(shell, SWT.NONE);
		lblCelular.setText("Celular");
		lblCelular.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCelular.setBounds(305, 244, 55, 15);
		
		text_9 = new Text(shell, SWT.BORDER);
		text_9.setBounds(366, 241, 134, 21);
		text_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Text t = (Text) e.getSource();
				t.selectAll();
				text_9 = t;
				formattedText = new FormattedText(text_9);
				formattedText.setFormatter(new MaskFormatter("(##) # ####-####"));
				text_9 = formattedText.getControl();
				
			}
		});
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toolBar.setBounds(430, 295, 53, 23);
		
		ToolItem tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
			}
		});
		
		tltmPesquisar.setImage(SWTResourceManager.getImage("C:\\Users\\danie\\eclipse-workspace\\gestaoigreja\\icones\\search.png"));
		
		Label lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCidade.setBounds(41, 295, 55, 15);
		lblCidade.setText("Cidade");
		
		text_10 = new Text(shell, SWT.BORDER);
		text_10.setEnabled(false);
		text_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_10.setBounds(117, 295, 307, 21);
		
		lblEndereo = new Label(shell, SWT.NONE);
		lblEndereo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblEndereo.setBounds(41, 336, 55, 15);
		lblEndereo.setText("Endereço");
		
		text_11 = new Text(shell, SWT.BORDER);
		text_11.setBounds(117, 333, 254, 21);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 278, 548, 2);
		
		Label lblN = new Label(shell, SWT.NONE);
		lblN.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblN.setBounds(402, 336, 22, 15);
		lblN.setText("Nº");
		
		text_12 = new Text(shell, SWT.BORDER);
		text_12.setTextLimit(6);
		text_12.setBounds(424, 333, 76, 21);
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setSelection(true);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		btnCheckButton.setBounds(455, 25, 93, 16);
		btnCheckButton.setText("Ativo");
		
		Label lblObservaes = new Label(shell, SWT.NONE);
		lblObservaes.setText("Observações");
		lblObservaes.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblObservaes.setBounds(31, 422, 76, 15);
		
		text_13 = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_13.setBounds(117, 375, 254, 104);
		
		Button btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(425, 398, 75, 25);
		btnGravar.setText("Gravar");
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(424, 441, 75, 25);
		btnLimpar.setText("Limpar");
		btnLimpar.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_13.setText("");
				text_12.setText("");
				text_11.setText("");
				text_10.setText("");
				text_9.setText("");
				text_8.setText("");
				text_7.setText("");
				text_6.setText("");
				text_5.setText("");
				
				text_3.setText("");
				
				text_1.setText("");
				
			}
		});
		
		
		
		
		
		
		
		
		
	
		
		
		
	

	}
}
