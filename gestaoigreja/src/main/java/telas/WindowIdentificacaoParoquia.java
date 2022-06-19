package telas;
import java.text.ParseException;


import javax.swing.text.MaskFormatter;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.FormattingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import telas.Pesquiar.WindowCidadeRecuperarParoquiaAction;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.nebula.*;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public abstract class WindowIdentificacaoParoquia {

	protected Integer indiceShell = 1;
	protected Shell shell;
	protected Display display;
	protected Label lblNewLabel;
	protected Text textFantasia;
	protected Text textRazaoSocial;
	protected Text text_2;
	protected Text textCidade;
	private Label lblCidade;
	private Label lblEndereo;
	protected Text textEndereco;
	protected Text textNumeroEndereco;
	protected Text textBairro;
	protected Text textContatos;
	protected Text text_8;
	protected Button btnGravar, btnLimpar;
	protected ToolBar toolBar;
	protected ToolItem tltmNewItem;
	protected FormattedText textoFormattedTextCep, textoFormattedTextTelefone, textoFormattedTextCelular, textoFormattedText;
	
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			WindowIdentificacaoParoquia window = new WindowIdentificacaoParoquia();
//			
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 * @throws ParseException 
	 */
	public void open() throws ParseException {
		display = Display.getDefault();
		
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
	 * @throws ParseException 
	 */
	protected void createContents() throws ParseException {
		shell = new Shell(SWT.MIN| SWT.CLOSE);
		shell.setSize(638, 486);
		shell.setText("Identificação Paroquia");
		shell.setLocation(PropriedadesShell.centralizarShell(shell, Display.getCurrent()));
		shell.setLayout(null);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(28, 38, 86, 15);
		lblNewLabel.setText("Nome Fantasia");
		
		textFantasia = new Text(shell, SWT.BORDER);
		textFantasia.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textFantasia.setBounds(120, 35, 476, 21);
		
		Label lblRazoSocial = new Label(shell, SWT.NONE);
		lblRazoSocial.setBounds(28, 80, 86, 15);
		lblRazoSocial.setText("Razão Social");
		
		textRazaoSocial = new Text(shell, SWT.BORDER);
		textRazaoSocial.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textRazaoSocial.setBounds(120, 74, 476, 21);

		textoFormattedText = new FormattedText(shell);
		textoFormattedText.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("###.###.###/###-##"));
		textoFormattedText.getControl().setBounds(120, 130, 138, 21);
		textoFormattedText.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		
		Label lblRazoSocial_1 = new Label(shell, SWT.NONE);
		lblRazoSocial_1.setText("CNPJ");
		lblRazoSocial_1.setBounds(28, 133, 86, 15);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(28, 114, 568, 2);
		
		Label lblCelular = new Label(shell, SWT.NONE);
		lblCelular.setBounds(28, 177, 55, 15);
		lblCelular.setText("Celular");
		
		textoFormattedTextCelular = new FormattedText(shell);
		textoFormattedTextCelular.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("(##) #####-####"));
		textoFormattedTextCelular.getControl().setBounds(120, 177, 138, 21);
		textoFormattedTextCelular.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lbltelefone = new Label(shell, SWT.NONE);
		lbltelefone.setBounds(28, 221, 55, 15);
		lbltelefone.setText("Telefone");
		
		textoFormattedTextTelefone = new FormattedText(shell);
		textoFormattedTextTelefone.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("(##) ####-####"));
		textoFormattedTextTelefone.getControl().setBounds(120, 221, 138, 21);
		textoFormattedTextTelefone.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		textCidade = new Text(shell, SWT.BORDER);
		textCidade.setEnabled(false);
		textCidade.setEditable(false);
		textCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		textCidade.setBounds(120, 278, 248, 21);
		
		FormData fdButton = new FormData();
		 fdButton.left = new FormAttachment( 0, 0 );
		 fdButton.bottom = new FormAttachment( 100, 0 );
		
		lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setText("Cidade");
		lblCidade.setBounds(28, 281, 55, 15);
		
		lblEndereo = new Label(shell, SWT.NONE);
		lblEndereo.setBounds(28, 327, 55, 15);
		lblEndereo.setText("Endereço");
		
		textEndereco = new Text(shell, SWT.BORDER);
		textEndereco.setBounds(120, 324, 316, 21);
		textEndereco.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		Label lblN = new Label(shell, SWT.NONE);
		lblN.setText("Nº");
		lblN.setBounds(457, 327, 24, 15);
		
		textNumeroEndereco = new Text(shell, SWT.BORDER);
		textNumeroEndereco.setBounds(487, 324, 86, 21);
		textNumeroEndereco.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		textBairro = new Text(shell, SWT.BORDER);
		textBairro.setBounds(120, 367, 248, 21);
		
		Label lblBairro = new Label(shell, SWT.NONE);
		lblBairro.setText("Bairro");
		lblBairro.setBounds(28, 370, 55, 15);
		
		btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(406, 411, 75, 25);
		btnLimpar.setText("Limpar");
		
		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setText("Gravar");
		btnGravar.setBounds(498, 411, 75, 25);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(28, 259, 568, 2);
		
		textContatos = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		textContatos.setBounds(354, 130, 242, 106);
		
		Label lblContatos = new Label(shell, SWT.NONE);
		lblContatos.setBounds(293, 177, 55, 15);
		lblContatos.setText("Contatos");
		
		Label lblCep = new Label(shell, SWT.NONE);
		lblCep.setText("CEP");
		lblCep.setBounds(28, 416, 55, 15);
		
		textoFormattedTextCep = new FormattedText(shell);
		textoFormattedTextCep.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("#####-###"));
		textoFormattedTextCep.getControl().setBounds(120, 410, 171, 21);
		textoFormattedTextCep.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(374, 278, 29, 23);
		
		tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setImage(SWTResourceManager.getImage("./icones/search.png"));
		


	}
}
