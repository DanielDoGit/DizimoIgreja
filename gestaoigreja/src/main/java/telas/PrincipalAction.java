package telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;

public class PrincipalAction {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PrincipalAction window = new PrincipalAction();
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
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shell.setMaximized(true);
		//shell.setModified(true);
		//sshell.setFullScreen(true);
		//shell.setSize(640, 429);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		menu.setVisible(true);
		shell.setMenuBar(menu);
		
		MenuItem mntmCadastros = new MenuItem(menu, SWT.CASCADE);
		mntmCadastros.setText("Cadastros");
		
		Menu menu_1 = new Menu(mntmCadastros);
		mntmCadastros.setMenu(menu_1);
		
		MenuItem mntmParoquia = new MenuItem(menu_1, SWT.NONE);
		mntmParoquia.setText("Paroquia");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmComunidade = new MenuItem(menu_1, SWT.CASCADE);
		mntmComunidade.setText("Comunidade");
		
		Menu menu_3 = new Menu(mntmComunidade);
		mntmComunidade.setMenu(menu_3);
		
		MenuItem mntmCadastrar_1 = new MenuItem(menu_3, SWT.NONE);
		mntmCadastrar_1.setText("Cadastrar");
		
		MenuItem mntmPesquisar_1 = new MenuItem(menu_3, SWT.NONE);
		mntmPesquisar_1.setText("Pesquisar");
		
		MenuItem mntmCidade = new MenuItem(menu_1, SWT.CASCADE);
		mntmCidade.setText("Cidade");
		
		Menu menu_2 = new Menu(mntmCidade);
		mntmCidade.setMenu(menu_2);
		
		MenuItem mntmCadastrar = new MenuItem(menu_2, SWT.NONE);
		mntmCadastrar.setText("Cadastrar");
		
		MenuItem mntmPesquisar = new MenuItem(menu_2, SWT.NONE);
		mntmPesquisar.setText("Pesquisar");
		
		MenuItem mntmFornecedor = new MenuItem(menu_1, SWT.CASCADE);
		mntmFornecedor.setText("Fornecedor");
		
		Menu menu_11 = new Menu(mntmFornecedor);
		mntmFornecedor.setMenu(menu_11);
		
		MenuItem mntmCadastrar_7 = new MenuItem(menu_11, SWT.NONE);
		mntmCadastrar_7.setText("Cadastrar");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmColetor = new MenuItem(menu_1, SWT.CASCADE);
		mntmColetor.setText("Coletor");
		
		Menu menu_4 = new Menu(mntmColetor);
		mntmColetor.setMenu(menu_4);
		
		MenuItem mntmCadastrar_2 = new MenuItem(menu_4, SWT.NONE);
		mntmCadastrar_2.setText("Cadastrar");
		
		MenuItem mntmPesquisar_2 = new MenuItem(menu_4, SWT.NONE);
		mntmPesquisar_2.setText("Pesquisar");
		
		MenuItem mntmFuncionario = new MenuItem(menu_1, SWT.CASCADE);
		mntmFuncionario.setText("Funcionario");
		
		Menu menu_5 = new Menu(mntmFuncionario);
		mntmFuncionario.setMenu(menu_5);
		
		MenuItem mntmCadastrar_3 = new MenuItem(menu_5, SWT.NONE);
		mntmCadastrar_3.setText("Cadastrar");
		
		MenuItem mntmPesquisar_3 = new MenuItem(menu_5, SWT.NONE);
		mntmPesquisar_3.setText("Pesquisar");
		
		MenuItem mntmContribuintedizimista = new MenuItem(menu_1, SWT.CASCADE);
		mntmContribuintedizimista.setText("Contribuinte/Dizimista");
		
		Menu menu_6 = new Menu(mntmContribuintedizimista);
		mntmContribuintedizimista.setMenu(menu_6);
		
		MenuItem mntmCadastrar_4 = new MenuItem(menu_6, SWT.NONE);
		mntmCadastrar_4.setText("Cadastrar");
		
		MenuItem mntmPesquisar_4 = new MenuItem(menu_6, SWT.NONE);
		mntmPesquisar_4.setText("Pesquisar");
		
		MenuItem mntmFormasDePagamento = new MenuItem(menu_1, SWT.CASCADE);
		mntmFormasDePagamento.setText("Formas de Pagamento");
		
		Menu menu_12 = new Menu(mntmFormasDePagamento);
		mntmFormasDePagamento.setMenu(menu_12);
		
		MenuItem mntmCadastrar_8 = new MenuItem(menu_12, SWT.NONE);
		mntmCadastrar_8.setText("Cadastrar");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmMissas = new MenuItem(menu_1, SWT.CASCADE);
		mntmMissas.setText("Missas");
		
		Menu menu_13 = new Menu(mntmMissas);
		mntmMissas.setMenu(menu_13);
		
		MenuItem mntmPerodos = new MenuItem(menu_13, SWT.CASCADE);
		mntmPerodos.setText("Períodos");
		
		Menu menu_14 = new Menu(mntmPerodos);
		mntmPerodos.setMenu(menu_14);
		
		MenuItem mntmCadastrar_10 = new MenuItem(menu_14, SWT.NONE);
		mntmCadastrar_10.setText("Cadastrar");
		
		MenuItem mntmPesquisar_9 = new MenuItem(menu_14, SWT.NONE);
		mntmPesquisar_9.setText("Pesquisar");
		
		MenuItem mntmDias = new MenuItem(menu_13, SWT.CASCADE);
		mntmDias.setText("Dias");
		
		Menu menu_15 = new Menu(mntmDias);
		mntmDias.setMenu(menu_15);
		
		MenuItem mntmCadastrar_11 = new MenuItem(menu_15, SWT.NONE);
		mntmCadastrar_11.setText("Cadastrar");
		
		MenuItem mntmPesquisar_8 = new MenuItem(menu_15, SWT.NONE);
		mntmPesquisar_8.setText("Pesquisar");
		
		MenuItem mntmMovimentaes = new MenuItem(menu, SWT.CASCADE);
		mntmMovimentaes.setText("Movimentações");
		
		Menu menu_7 = new Menu(mntmMovimentaes);
		mntmMovimentaes.setMenu(menu_7);
		
		MenuItem mntmSolicitaes = new MenuItem(menu_7, SWT.CASCADE);
		mntmSolicitaes.setText("Solicitações ");
		
		Menu menu_8 = new Menu(mntmSolicitaes);
		mntmSolicitaes.setMenu(menu_8);
		
		MenuItem mntmCadastrar_5 = new MenuItem(menu_8, SWT.NONE);
		mntmCadastrar_5.setText("Cadastrar");
		
		MenuItem mntmPesquisar_5 = new MenuItem(menu_8, SWT.NONE);
		mntmPesquisar_5.setText("Pesquisar");
		
		MenuItem mntmBalancete = new MenuItem(menu_7, SWT.CASCADE);
		mntmBalancete.setText("Balancete Dizimo");
		
		Menu menu_9 = new Menu(mntmBalancete);
		mntmBalancete.setMenu(menu_9);
		
		MenuItem mntmCadastrar_6 = new MenuItem(menu_9, SWT.NONE);
		mntmCadastrar_6.setText("Cadastrar");
		
		MenuItem mntmPesquisar_6 = new MenuItem(menu_9, SWT.NONE);
		mntmPesquisar_6.setText("Pesquisar");
		
		new MenuItem(menu_7, SWT.SEPARATOR);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu_7, SWT.CASCADE);
		mntmNewSubmenu.setText("Pagamentos");
		
		Menu menu_10 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_10);
		
		MenuItem mntmCadastrar_9 = new MenuItem(menu_10, SWT.NONE);
		mntmCadastrar_9.setText("Cadastrar");
		
		MenuItem mntmPesquisar_7 = new MenuItem(menu_10, SWT.NONE);
		mntmPesquisar_7.setText("Pesquisar");
		
		MenuItem mntmRecebimentoDizimo = new MenuItem(menu_10, SWT.NONE);
		mntmRecebimentoDizimo.setText("Recebimento Dizimo");
		
		MenuItem mntmCaixa = new MenuItem(menu_7, SWT.NONE);
		mntmCaixa.setText("Caixa");
		
		new MenuItem(menu_7, SWT.SEPARATOR);
		
		MenuItem mntmReceitas = new MenuItem(menu_7, SWT.NONE);
		mntmReceitas.setText("Balancete Missa");
		
		MenuItem mntmRelatrios = new MenuItem(menu, SWT.CASCADE);
		mntmRelatrios.setText("Relatórios");
		
		Menu menu_16 = new Menu(mntmRelatrios);
		mntmRelatrios.setMenu(menu_16);
		
		MenuItem mntmComunidades = new MenuItem(menu_16, SWT.NONE);
		mntmComunidades.setText("Comunidades");
		
		MenuItem mntmDizimistascontribuintes = new MenuItem(menu_16, SWT.NONE);
		mntmDizimistascontribuintes.setText("Dizimistas/Contribuintes");
		
		MenuItem mntmMissas_1 = new MenuItem(menu_16, SWT.CASCADE);
		mntmMissas_1.setText("Missas");
		
		Menu menu_17 = new Menu(mntmMissas_1);
		mntmMissas_1.setMenu(menu_17);
		
		MenuItem mntmPorPerodo = new MenuItem(menu_17, SWT.NONE);
		mntmPorPerodo.setText("Por período");
		
		MenuItem mntmPerodoEDia = new MenuItem(menu_17, SWT.NONE);
		mntmPerodoEDia.setText("Período e Dia");
		
		MenuItem mntmTodos = new MenuItem(menu_17, SWT.NONE);
		mntmTodos.setText("Todos");
		
		MenuItem mntmConfiguraes = new MenuItem(menu, SWT.CASCADE);
		mntmConfiguraes.setText("Configurações");
		
		Menu menu_18 = new Menu(mntmConfiguraes);
		mntmConfiguraes.setMenu(menu_18);
		
		MenuItem mntmConfiguraesGerais = new MenuItem(menu_18, SWT.NONE);
		mntmConfiguraesGerais.setText("Configurações gerais");
		
		MenuItem mntmEstadoDasMovimentaes = new MenuItem(menu_18, SWT.NONE);
		mntmEstadoDasMovimentaes.setText("Estado das Movimentações");
//		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_composite.heightHint = 315;
//		gd_composite.widthHint = 607;
//		composite.setLayoutData(gd_composite);
//		

	}
}
