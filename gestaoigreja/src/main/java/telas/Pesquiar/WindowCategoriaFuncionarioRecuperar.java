package telas.Pesquiar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

public abstract class WindowCategoriaFuncionarioRecuperar {

	protected Shell shlPesquisar;
	protected Text text;
	protected Table table;
	protected TableItem ass[];
	protected CCombo combo;
	protected ToolBar toolBar;
	protected ToolItem tltmPesquisar;
	protected ToolBar toolBar_1;
	protected ToolItem tltmEditar;
	protected ToolBar toolBar_2;
	protected ToolItem tltmExcluir;

	public static void main(String[] args) {
		WindowCategoriaFuncionarioRecuperar ss = new WindowCategoriaFuncionarioRecuperar() {
		};
		try {
			ss.createContents();
			ss.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Shell getShlPesquisar() {
		return shlPesquisar;
	}

	public void setShlPesquisar(Shell shlPesquisar) {
		this.shlPesquisar = shlPesquisar;
	}

	public void open() throws Exception {
		Display display = Display.getDefault();

		shlPesquisar.open();
		shlPesquisar.setLocation(PropriedadesShell.centralizarShell(shlPesquisar, display));

		while (!shlPesquisar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	protected void createContents() throws Exception {
		shlPesquisar = new Shell(SWT.MIN | SWT.CLOSE);
		shlPesquisar.setModified(true);
		shlPesquisar.setSize(784, 502);
		shlPesquisar.setText("Pesquisar");
		shlPesquisar.setLayout(null);
		shlPesquisar.layout();

		Label lblFiltro = new Label(shlPesquisar, SWT.NONE);
		lblFiltro.setBounds(32, 14, 55, 15);
		lblFiltro.setText("Filtro");

		text = new Text(shlPesquisar, SWT.BORDER);
		text.setBounds(146, 31, 338, 21);

		table = new Table(shlPesquisar, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(32, 84, 703, 352);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(176);
		tblclmnNewColumn.setText("Código");

		TableColumn tblclmnNomeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnNomeDizimista.setWidth(523);
		tblclmnNomeDizimista.setText("Descrição Categoria");

		Label lblCampoDePesquisa = new Label(shlPesquisar, SWT.NONE);
		lblCampoDePesquisa.setBounds(146, 14, 62, 15);
		lblCampoDePesquisa.setText("Campo");

		combo = new CCombo(shlPesquisar, SWT.BORDER | SWT.READ_ONLY);
		combo.setListVisible(true);
		combo.setBounds(32, 31, 91, 21);
		combo.add("Código", 0);
		combo.add("Categoria", 1);
		combo.select(1);
		
		toolBar = new ToolBar(shlPesquisar, SWT.ARROW_DOWN);
		toolBar.setBounds(501, 14, 81, 58);
		
		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.setText("Pesquisar");
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));
		
		toolBar_1 = new ToolBar(shlPesquisar, SWT.ARROW_DOWN);
		toolBar_1.setBounds(594, 14, 62, 58);
		
		tltmEditar = new ToolItem(toolBar_1, SWT.NONE);
		tltmEditar.setImage(SWTResourceManager.getImage("./icones/update.png"));
		tltmEditar.setText("Editar");
		
		toolBar_2 = new ToolBar(shlPesquisar, SWT.ARROW_DOWN);
		toolBar_2.setBounds(672, 14, 73, 58);
		
		tltmExcluir = new ToolItem(toolBar_2, SWT.NONE);
		tltmExcluir.setImage(SWTResourceManager.getImage("./icones/delete.png"));
		tltmExcluir.setText("Excluir");

	}
}
