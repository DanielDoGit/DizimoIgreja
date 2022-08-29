package telas.Pesquiar;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;
import telas.Inicial;

public abstract class WindowRecuperarBalancete {

	protected Shell shell;
	protected Display display;
	protected Text textColetor;
	protected Text text1;
	protected Table table;
	protected DateChooserCombo comboPeriodoColetorDe;
	protected Combo combo;
	protected ToolBar toolBar, toolBar_1, toolBar_1_1;
	protected ToolItem tltmPesquisar, tltmAceitar, tltmExcluir;
	protected TableColumn tblclmnCdigo, tblclmnNome, tblclmnAtivo, tblclmnCidadeDizimista;
	protected Group grpPerodo;
	protected Label lblDe;
	protected Label lblAt;
	protected DateChooserCombo dateChooserComboAte;
	protected DateChooserCombo dateChooserComboDe;
	protected Group grpPerodoEColetor;
	protected Label lblColetor;
	protected Text text;
	protected Text textDizimista;
	protected Label lblDe_1, lblAt_1;
	protected DateChooserCombo comboPeriodoColetorAte;
	protected Group grpDizimista;
	protected Group grpCdigo;
	protected Text textCodigo;

	public static void main(String args[]) {
		try {

			WindowRecuperarBalancete shell = new WindowRecuperarBalancete() {
			};
			shell.createContents();
			shell.openShell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openShell() {
		display = Display.getDefault();
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		shell.open();

		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				Inicial.fechaconexao();
				display.sleep();
			}
		}
	}

	public void createContents() {
		shell = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
		shell.setText("Pesquisar Balancete Dizimista");
		shell.setSize(838, 493);
		shell.setLayout(null);

		Label lblCampo = new Label(shell, SWT.NONE);
		lblCampo.setBounds(28, 21, 55, 15);
		lblCampo.setText("Filtro");

		combo = new Combo(shell, SWT.READ_ONLY);
		combo.add("Código", 0);
		combo.add("Por período", 1);
		combo.add("Nome Coletor", 2);
		combo.add("Nome Dizimista", 3);
		combo.add("Por período e coletor", 4);
		combo.select(1);
		combo.setBounds(28, 42, 136, 23);

		toolBar = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar.setBounds(579, 21, 84, 62);

		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));
		tltmPesquisar.setText("Pesquisar");

		toolBar_1 = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar_1.setBounds(674, 21, 62, 62);

		tltmAceitar = new ToolItem(toolBar_1, SWT.NONE);
		tltmAceitar.setImage(SWTResourceManager.getImage("./icones/update.png"));
		tltmAceitar.setText("Editar");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(28, 119, 776, 313);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		

		tblclmnCdigo = new TableColumn(table, SWT.NONE);
		tblclmnCdigo.setWidth(94);
		tblclmnCdigo.setText("Código");

		tblclmnNome = new TableColumn(table, SWT.NONE);
		tblclmnNome.setWidth(280);
		tblclmnNome.setText("Nome Coletor");

		tblclmnCidadeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnCidadeDizimista.setWidth(165);
		tblclmnCidadeDizimista.setText("Data de Cadastro");

		tblclmnAtivo = new TableColumn(table, SWT.NONE);
		tblclmnAtivo.setWidth(231);
		tblclmnAtivo.setText("Autorizado");

		toolBar_1_1 = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar_1_1.setBounds(742, 21, 62, 62);

		tltmExcluir = new ToolItem(toolBar_1_1, SWT.NONE);
		tltmExcluir.setImage(SWTResourceManager.getImage("./icones/delete.png"));
		tltmExcluir.setText("Excluir");
		
		grpPerodo = new Group(shell, SWT.NONE);
		grpPerodo.setText("Período");
		grpPerodo.setBounds(193, 10, 361, 62);

		lblDe = new Label(grpPerodo, SWT.SHADOW_NONE);
		lblDe.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDe.setAlignment(SWT.CENTER);
		lblDe.setBounds(20, 24, 27, 28);
		lblDe.setText("De");

		lblAt = new Label(grpPerodo, SWT.NONE);
		lblAt.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblAt.setBounds(162, 24, 27, 28);
		lblAt.setText("até");

		dateChooserComboDe = new DateChooserCombo(grpPerodo, SWT.BORDER);
		dateChooserComboDe.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		dateChooserComboDe.setBounds(55, 24, 86, 28);

		dateChooserComboAte = new DateChooserCombo(grpPerodo, SWT.BORDER);
		dateChooserComboAte.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		dateChooserComboAte.setBounds(195, 24, 95, 28);

	}
}
