package telas;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

public abstract class WindowDizimista {

	protected Shell shell;
	protected Text textCodigo;
	protected Label lblNome;
	protected Text textNome;
	protected Label lblNewLabel;
	protected Text text_2;
	protected Text textCpf;
	protected Text text_4;
	protected FormattedText formattedText;
	protected Text textRg;
	protected Label lblRg;
	protected Label lblDataNascimento;
	protected Text textContatos;
	protected Label lblContatos;
	protected Label lblTelefone;
	protected Text textTelefone;
	protected Label lblCelular;
	protected Text textCelular;
	protected Text textCidade;
	protected Label lblEndereo;
	protected Text textEndereco;
	protected Text textNumeroEndereco;
	protected Text textObservacoes;
	protected Button btnLimpar, btnGravar, botaoIsAtivo;
	protected ToolItem tltmPesquisar;
	protected ToolBar toolBar;
	protected DateChooserCombo dateChooserCombo;

	public static void main(String[] args) {
		try {
			WindowDizimista window = new WindowDizimista() {
			};
			window.createContents();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getCurrent();
		shell.open();
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell(SWT.CLOSE | SWT.MIN);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shell.setSize(580, 542);
		shell.setText("Dizimista");

		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCdigo.setBounds(192, 20, 55, 15);
		lblCdigo.setText("Código");

		textCodigo = new Text(shell, SWT.BORDER | SWT.CENTER);
		textCodigo.setVisible(true);
		textCodigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textCodigo.setEnabled(false);
		textCodigo.setBounds(253, 20, 76, 21);

		lblNome = new Label(shell, SWT.NONE);
		lblNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNome.setBounds(52, 64, 55, 15);
		lblNome.setText("Nome");

		textNome = new Text(shell, SWT.BORDER);
		textNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textNome.setBounds(117, 61, 414, 21);

		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(52, 108, 55, 15);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel.setText("CPF");

		textCpf = new Text(shell, SWT.BORDER);
		textCpf.setBounds(117, 108, 134, 21);
		textCpf.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));

		textRg = new Text(shell, SWT.BORDER);
		textRg.setBounds(117, 151, 134, 21);

		lblRg = new Label(shell, SWT.NONE);
		lblRg.setText("RG");
		lblRg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblRg.setBounds(52, 154, 55, 15);

		lblDataNascimento = new Label(shell, SWT.NONE);
		lblDataNascimento.setBounds(10, 197, 97, 15);
		lblDataNascimento.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblDataNascimento.setText("Data Nascimento");

		textContatos = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textContatos.setBounds(350, 105, 182, 120);

		lblContatos = new Label(shell, SWT.NONE);
		lblContatos.setBounds(289, 154, 55, 15);
		lblContatos.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblContatos.setText("Contatos");

		lblTelefone = new Label(shell, SWT.NONE);
		lblTelefone.setBounds(41, 244, 55, 15);
		lblTelefone.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblTelefone.setText("Telefone");

		textTelefone = new Text(shell, SWT.BORDER);
		textTelefone.setBounds(117, 241, 134, 21);

		lblCelular = new Label(shell, SWT.NONE);
		lblCelular.setText("Celular");
		lblCelular.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCelular.setBounds(289, 244, 55, 15);

		textCelular = new Text(shell, SWT.BORDER);
		textCelular.setBounds(350, 241, 134, 21);

		toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		toolBar.setBounds(412, 293, 53, 23);

		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));

		Label lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCidade.setBounds(41, 295, 55, 15);
		lblCidade.setText("Cidade");

		textCidade = new Text(shell, SWT.BORDER);
		textCidade.setEnabled(false);
		textCidade.setEditable(true);
		textCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textCidade.setBounds(117, 295, 289, 21);

		lblEndereo = new Label(shell, SWT.NONE);
		lblEndereo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblEndereo.setBounds(41, 336, 55, 15);
		lblEndereo.setText("Endereço");

		textEndereco = new Text(shell, SWT.BORDER);
		textEndereco.setBounds(117, 333, 289, 21);

		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 278, 548, 2);

		Label lblN = new Label(shell, SWT.NONE);
		lblN.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblN.setBounds(427, 336, 22, 15);
		lblN.setText("Nº");

		textNumeroEndereco = new Text(shell, SWT.BORDER);
		textNumeroEndereco.setTextLimit(6);
		textNumeroEndereco.setBounds(455, 333, 76, 21);

		botaoIsAtivo = new Button(shell, SWT.CHECK);
		botaoIsAtivo.setSelection(true);
		botaoIsAtivo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		botaoIsAtivo.setBounds(455, 25, 93, 16);
		botaoIsAtivo.setText("Ativo");

		Label lblObservaes = new Label(shell, SWT.NONE);
		lblObservaes.setText("Observações");
		lblObservaes.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblObservaes.setBounds(31, 422, 76, 15);

		textObservacoes = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textObservacoes.setBounds(117, 375, 289, 104);

		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(456, 396, 75, 25);
		btnGravar.setText("Gravar");

		btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(456, 438, 75, 25);
		btnLimpar.setText("Limpar");
		
		dateChooserCombo = new DateChooserCombo(shell, SWT.BORDER | SWT.FLAT);
		dateChooserCombo.setShowButtonOnFocus(true);
		dateChooserCombo.setBounds(117, 197, 134, 21);
		
		
	}
}
