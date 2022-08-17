package telas;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.datechooser.DateChooserTheme;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

public abstract class WindowFuncionario {

	protected Display display;
	protected Shell shlColetor;
	protected Text text_2;
	protected Text text_4;
	protected FormattedText formattedText;
	protected ToolItem tltmPesquisar;
	protected ToolBar toolBar_1, toolBar;
	protected Text textCodigo;
	protected Text textNome;
	protected Text textCidade;
	protected Text textEndereco;
	protected Text textNumeroEndereco;
	protected Text textObservacao;
	protected Text textLogin;
	protected Text textSenha;
	protected Table table;
	protected Text textCategoriaFuncionario;
	protected Text textCpf;
	protected Text textRG;
	protected Text textCelular;
	protected Text textTelefone;
	protected Button btnAtivo;
	protected Button btnGravar, btnNewButton;
	protected TableColumn tblclmnNewColumn, tblclmnNewColumn_1;
	protected TableItem tableItem, itens[];
	protected ToolItem tltmPesquisarCategoriaFuncionario;
	protected Composite composite;
	protected DateChooserCombo dateChooserCombo;

	public static void main(String[] args) {
		try {
			WindowFuncionario window = new WindowFuncionario() {
			};
			window.createContents();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		display = Display.getCurrent();

		shlColetor.setLocation(PropriedadesShell.centralizarShell(shlColetor, display));
		shlColetor.open();

		while (!shlColetor.isDisposed()) {
			if (!display.readAndDispatch()) {
				Inicial.fechaconexao();
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlColetor = new Shell(SWT.CLOSE | SWT.MIN);

		shlColetor.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlColetor.setText("Funcionario");
		shlColetor.setSize(658, 623);

		shlColetor.setLayout(null);

		TabFolder tabFolder = new TabFolder(shlColetor, SWT.NONE);
		tabFolder.setBounds(10, 10, 621, 508);

		TabItem tbtmCadastros = new TabItem(tabFolder, SWT.NONE);
		tbtmCadastros.setText("Cadastros");

		composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite.setEnabled(true);
		tbtmCadastros.setControl(composite);

		Label lblCdigo = new Label(composite, SWT.NONE);
		lblCdigo.setBounds(209, 13, 40, 15);
		lblCdigo.setText("Código");

		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNome.setBounds(61, 60, 55, 15);
		lblNome.setText("Nome");

		textNome = new Text(composite, SWT.BORDER);
		textNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textNome.setBounds(135, 57, 454, 21);

		Label lblCpf = new Label(composite, SWT.NONE);
		lblCpf.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCpf.setBounds(61, 104, 40, 15);
		lblCpf.setText("CPF");

		Label lblRg = new Label(composite, SWT.NONE);
		lblRg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblRg.setBounds(336, 104, 28, 15);
		lblRg.setText("RG");

		Label lblDataNascimento = new Label(composite, SWT.NONE);
		lblDataNascimento.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblDataNascimento.setBounds(30, 143, 97, 15);
		lblDataNascimento.setText("Data Nascimento");

		Label lblCelular = new Label(composite, SWT.NONE);
		lblCelular.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCelular.setBounds(61, 188, 47, 15);
		lblCelular.setText("Celular");

		Label lblTelefone = new Label(composite, SWT.NONE);
		lblTelefone.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblTelefone.setText("Telefone");
		lblTelefone.setBounds(317, 188, 47, 15);

		Label lblCidade = new Label(composite, SWT.NONE);
		lblCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCidade.setBounds(63, 289, 47, 15);
		lblCidade.setText("Cidade");

		textCidade = new Text(composite, SWT.BORDER);
		textCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textCidade.setEnabled(false);
		textCidade.setBounds(137, 289, 257, 21);

		toolBar = new ToolBar(composite, SWT.FLAT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toolBar.setBounds(411, 279, 102, 47);

		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));
		tltmPesquisar.setText("Pesquisar");

		textEndereco = new Text(composite, SWT.BORDER);
		textEndereco.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		textEndereco.setBounds(137, 332, 257, 21);

		textNumeroEndereco = new Text(composite, SWT.BORDER);
		textNumeroEndereco.setBounds(460, 332, 76, 21);

		Label lblN = new Label(composite, SWT.NONE);
		lblN.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblN.setBounds(437, 335, 28, 15);
		lblN.setText("Nº");

		Label lblEndero = new Label(composite, SWT.NONE);
		lblEndero.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblEndero.setBounds(63, 335, 55, 15);
		lblEndero.setText("Endereço");

		textObservacao = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textObservacao.setBounds(137, 371, 257, 83);

		Label lblObservaes = new Label(composite, SWT.NONE);
		lblObservaes.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblObservaes.setBounds(42, 400, 76, 15);
		lblObservaes.setText("Observações");

		toolBar_1 = new ToolBar(composite, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toolBar_1.setBounds(409, 222, 83, 42);

		tltmPesquisarCategoriaFuncionario = new ToolItem(toolBar_1, SWT.NONE);
		tltmPesquisarCategoriaFuncionario.setImage(SWTResourceManager.getImage("./icones/search.png"));
		tltmPesquisarCategoriaFuncionario.setText("Pesquisar");

		textCategoriaFuncionario = new Text(composite, SWT.BORDER);
		textCategoriaFuncionario.setEnabled(false);
		textCategoriaFuncionario.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textCategoriaFuncionario.setBounds(135, 232, 257, 21);

		Label lblCategoria = new Label(composite, SWT.NONE);
		lblCategoria.setText("Categoria");
		lblCategoria.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCategoria.setBounds(52, 235, 64, 15);

		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(30, 265, 573, 7);

		FormattedText formattedText_1 = new FormattedText(composite, SWT.BORDER);
		formattedText_1.setFormatter(new MaskFormatter("###.###.###-###"));
		textCpf = formattedText_1.getControl();
		textCpf.setBounds(135, 104, 155, 21);

		FormattedText formattedText_1_1 = new FormattedText(composite, SWT.BORDER);
		formattedText_1_1.setFormatter(new MaskFormatter("##.###.###-##"));
		textRG = formattedText_1_1.getControl();
		textRG.setBounds(364, 104, 131, 21);

		FormattedText formattedText_1_2 = new FormattedText(composite, SWT.BORDER);
		formattedText_1_2.setFormatter(new MaskFormatter("(##) #####-####"));
		textCelular = formattedText_1_2.getControl();
		textCelular.setBounds(135, 188, 155, 21);

		FormattedText formattedText_1_3 = new FormattedText(composite, SWT.BORDER);
		formattedText_1_3.setFormatter(new MaskFormatter("(##) #####-####"));
		textTelefone = formattedText_1_3.getControl();
		textTelefone.setBounds(370, 185, 155, 21);

		dateChooserCombo = new DateChooserCombo(composite, SWT.BORDER);
		dateChooserCombo.setTheme(DateChooserTheme.BLUE);
		dateChooserCombo.setBounds(135, 143, 107, 21);

		TabItem tbtmPermisses = new TabItem(tabFolder, SWT.NONE);
		tbtmPermisses.setText("Permissões");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tbtmPermisses.setControl(composite_1);

		Label lblLogin = new Label(composite_1, SWT.NONE);
		lblLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblLogin.setBounds(28, 21, 38, 15);
		lblLogin.setText("Login");

		textLogin = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		textLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textLogin.setBounds(72, 18, 150, 21);

		Label lblSenha = new Label(composite_1, SWT.NONE);
		lblSenha.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblSenha.setBounds(262, 21, 46, 15);
		lblSenha.setText("Senha");

		textSenha = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		textSenha.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textSenha.setBounds(314, 18, 150, 21);

		btnAtivo = new Button(composite_1, SWT.CHECK);
		btnAtivo.setSelection(true);
		btnAtivo.setBounds(516, 20, 71, 16);
		btnAtivo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		btnAtivo.setText("Ativo");

		table = new Table(composite_1, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table.setSortDirection(SWT.DOWN);
		table.setBounds(28, 66, 559, 317);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnNewColumn = new TableColumn(table, SWT.BORDER);
		tblclmnNewColumn.setWidth(31);
		tblclmnNewColumn.setImage(SWTResourceManager.getImage("./icones/aceitatudo.png"));

		tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(520);
		tblclmnNewColumn_1.setText("Nome Permissão");

		btnGravar = new Button(shlColetor, SWT.NONE);

		btnGravar.setBounds(544, 541, 75, 25);
		btnGravar.setText("Gravar");

		btnNewButton = new Button(shlColetor, SWT.NONE);

		btnNewButton.setBounds(448, 541, 75, 25);
		btnNewButton.setText("Limpar");

	}
}
