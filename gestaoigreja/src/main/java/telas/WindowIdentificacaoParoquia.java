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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WindowIdentificacaoParoquia {

	protected Shell shell;
	protected Display display;
	private Label lblNewLabel;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Label lblCidade;
	private Label lblEndereo;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowIdentificacaoParoquia window = new WindowIdentificacaoParoquia();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws ParseException 
	 */
	public void open() throws ParseException {
		display = Display.getDefault();
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
	 * @throws ParseException 
	 */
	protected void createContents() throws ParseException {
		shell = new Shell(SWT.MIN| SWT.CLOSE);
		shell.setSize(635, 506);
		shell.setText("Identificação Paroquia");
		shell.setLocation(PropriedadesShell.centralizarShell(shell, Display.getCurrent()));
		shell.setLayout(null);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(28, 38, 86, 15);
		lblNewLabel.setText("Nome Fantasia");
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text.setBounds(120, 35, 476, 21);
		
		Label lblRazoSocial = new Label(shell, SWT.NONE);
		lblRazoSocial.setBounds(28, 80, 86, 15);
		lblRazoSocial.setText("Razão Social");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setBounds(120, 74, 476, 21);

		FormattedText textoFormattedText = new FormattedText(shell);
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
		
		FormattedText textoFormattedTextCelular = new FormattedText(shell);
		textoFormattedTextCelular.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("(##) #####-####"));
		textoFormattedTextCelular.getControl().setBounds(120, 177, 138, 21);
		textoFormattedTextCelular.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lbltelefone = new Label(shell, SWT.NONE);
		lbltelefone.setBounds(28, 221, 55, 15);
		lbltelefone.setText("Telefone");
		
		FormattedText textoFormattedTextTelefone = new FormattedText(shell);
		textoFormattedTextTelefone.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("(##) ####-####"));
		textoFormattedTextTelefone.getControl().setBounds(120, 221, 138, 21);
		textoFormattedTextTelefone.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setEnabled(false);
		text_3.setEditable(false);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_3.setBounds(120, 278, 248, 21);
		
		
		Button btnPesquisar = new Button(shell, SWT.NONE);
		btnPesquisar.setImage(SWTResourceManager.getImage("C:\\Users\\danie\\eclipse-workspace\\gestaoigreja\\icones\\lupa.png"));
		btnPesquisar.setBounds(389, 277, 92, 23);
		FormData fdButton = new FormData();
		 fdButton.left = new FormAttachment( 0, 0 );
		 fdButton.bottom = new FormAttachment( 100, 0 );
		 btnPesquisar.setLayoutData( fdButton );
		btnPesquisar.setText("Pesquisar");
		
		lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setText("Cidade");
		lblCidade.setBounds(28, 281, 55, 15);
		
		lblEndereo = new Label(shell, SWT.NONE);
		lblEndereo.setBounds(28, 327, 55, 15);
		lblEndereo.setText("Endereço");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(120, 324, 316, 21);
		
		Label lblN = new Label(shell, SWT.NONE);
		lblN.setText("Nº");
		lblN.setBounds(457, 327, 24, 15);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBounds(487, 324, 86, 21);
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setBounds(120, 367, 171, 21);
		
		Label lblBairro = new Label(shell, SWT.NONE);
		lblBairro.setText("Bairro");
		lblBairro.setBounds(28, 370, 55, 15);
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(389, 427, 75, 25);
		btnLimpar.setText("Limpar");
		
		Button btnGravar = new Button(shell, SWT.NONE);
		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (!textoFormattedText.getControl().getText().isEmpty() && !text_3.getText().isEmpty() && !text.getText().isEmpty()) {
					System.out.println("Deu certo");
				}else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos.");
				}
				
			}
		});
		btnGravar.setText("Gravar");
		btnGravar.setBounds(498, 427, 75, 25);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(28, 259, 568, 2);
		
		text_7 = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_7.setBounds(354, 130, 242, 106);
		
		Label lblContatos = new Label(shell, SWT.NONE);
		lblContatos.setBounds(293, 177, 55, 15);
		lblContatos.setText("Contatos");
		
		Label lblCep = new Label(shell, SWT.NONE);
		lblCep.setText("CEP");
		lblCep.setBounds(28, 416, 55, 15);
		
		FormattedText textoFormattedTextCep = new FormattedText(shell);
		textoFormattedTextCep.setFormatter(new org.eclipse.nebula.widgets.formattedtext.MaskFormatter("#####-###"));
		textoFormattedTextCep.getControl().setBounds(120, 410, 171, 21);
		textoFormattedTextCep.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		


	}
}
