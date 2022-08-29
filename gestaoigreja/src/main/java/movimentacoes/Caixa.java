package movimentacoes;

import java.util.Date;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

public abstract class Caixa {

	protected Shell shell;
	protected Table table;
	protected ToolItem tltmNewItem;
	protected DateChooserCombo dateChooserAte, dateChooserDe;
	protected Label lbReaisCredito, lblReaisDebitos, lblReaisTotal;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Caixa window = new Caixa() {
			};
			window.createContents();
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
	protected void createContents() {
		shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(758, 443);
		shell.setText("Caixa");
		
		Group grpPerodo = new Group(shell, SWT.NONE);
		grpPerodo.setText("Período");
		grpPerodo.setBounds(21, 10, 698, 111);
		
		dateChooserDe = new DateChooserCombo(grpPerodo, SWT.BORDER);
		dateChooserDe.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		dateChooserDe.setValue(new Date());
		dateChooserDe.setBounds(152, 29, 99, 21);
		
		dateChooserAte = new DateChooserCombo(grpPerodo, SWT.BORDER);
		dateChooserAte.setValue(new Date());
		dateChooserAte.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		dateChooserAte.setBounds(152, 80, 99, 21);
		
		Label lblDe = new Label(grpPerodo, SWT.NONE);
		lblDe.setBounds(121, 29, 25, 15);
		lblDe.setText("De");
		
		Label lblAt = new Label(grpPerodo, SWT.NONE);
		lblAt.setBounds(115, 80, 31, 15);
		lblAt.setText("Até");
		
		ToolBar toolBar = new ToolBar(grpPerodo, SWT.ARROW_DOWN);
		toolBar.setBounds(25, 49, 72, 46);
		
		tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("Aceitar");
		tltmNewItem.setImage(SWTResourceManager.getImage("./icones/aceitar.png"));
		
		Label label = new Label(grpPerodo, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(315, 18, 2, 83);
		
		Label lblCrditos = new Label(grpPerodo, SWT.NONE);
		lblCrditos.setBounds(348, 29, 55, 15);
		lblCrditos.setText("Créditos:");
		
		Label lblDbitos = new Label(grpPerodo, SWT.NONE);
		lblDbitos.setBounds(493, 29, 55, 15);
		lblDbitos.setText("Débitos:");
		
		Label lblTotal = new Label(grpPerodo, SWT.NONE);
		lblTotal.setBounds(426, 69, 43, 15);
		lblTotal.setText("Total:");
		
		lbReaisCredito = new Label(grpPerodo, SWT.NONE);
		lbReaisCredito.setBounds(409, 29, 55, 15);
		lbReaisCredito.setText("R$ 0,00");
		
		lblReaisDebitos = new Label(grpPerodo, SWT.NONE);
		lblReaisDebitos.setBounds(554, 29, 55, 15);
		lblReaisDebitos.setText("R$ 0,00");
		
		lblReaisTotal = new Label(grpPerodo, SWT.NONE);
		lblReaisTotal.setBounds(475, 69, 55, 15);
		lblReaisTotal.setText("R$ 0,00");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(21, 146, 698, 233);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnCdigo = new TableColumn(table, SWT.NONE);
		tblclmnCdigo.setWidth(89);
		tblclmnCdigo.setText("Código");
		
		TableColumn tblclmnHistorico = new TableColumn(table, SWT.NONE);
		tblclmnHistorico.setWidth(153);
		tblclmnHistorico.setText("Historico");
		
		TableColumn tblclmnValor = new TableColumn(table, SWT.NONE);
		tblclmnValor.setWidth(100);
		tblclmnValor.setText("Valor");
		
		TableColumn tblclmnFormaPagmento = new TableColumn(table, SWT.NONE);
		tblclmnFormaPagmento.setWidth(136);
		tblclmnFormaPagmento.setText("Forma Pagamento");
		
		TableColumn tblclmnTipo = new TableColumn(table, SWT.NONE);
		tblclmnTipo.setWidth(100);
		tblclmnTipo.setText("Tipo");
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(100);
		tblclmnData.setText("Data");
		

	}
}
