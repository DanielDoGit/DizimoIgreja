package movimentacoes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;
import telas.Inicial;

public abstract class Balancete {

	protected Display display;
	protected Shell shlbalancete;
	protected Text textNomeColetor;
	protected Text textCodigo;
	protected Text textComunidade;
	protected Text textData;
	protected Table table;
	protected Text textTotal;
	protected TableColumn tblclmnNomeDizimista;
	protected TableColumn tblclmnData;
	protected TableColumn tblclmnCdigo;
	protected TableColumn tblclmnValorDaContribuio;
	protected Button btnIncluir;
	protected Button btnExcluir;
	protected Button btnExcluirTudo;
	protected Button btnFinalizar;
	protected Button btnCancelar;
	protected Button btnGravar;

	public static void main(String[] args) {
		try {
			Balancete balancete = new Balancete() {
			};
			balancete.createContents();
			balancete.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		display = Display.getCurrent();

		shlbalancete.setLocation(PropriedadesShell.centralizarShell(shlbalancete, display));
		shlbalancete.open();

		while (!shlbalancete.isDisposed()) {
			if (!display.readAndDispatch()) {
				Inicial.fechaconexao();
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlbalancete = new Shell(SWT.CLOSE | SWT.MIN);
		shlbalancete.setSize(789, 539);
		shlbalancete.setText("Balancete do Dizimo");

		Group Cabecalho = new Group(shlbalancete, SWT.NONE);
		Cabecalho.setText("Cabecalho");
		Cabecalho.setBounds(22, 10, 738, 117);

		Label lblCdigo = new Label(Cabecalho, SWT.NONE);
		lblCdigo.setBounds(42, 24, 45, 15);
		lblCdigo.setText("Código");

		Label lblNomeColetor = new Label(Cabecalho, SWT.NONE);
		lblNomeColetor.setBounds(21, 67, 81, 15);
		lblNomeColetor.setText("Nome Coletor");

		textNomeColetor = new Text(Cabecalho, SWT.BORDER);
		textNomeColetor.setEnabled(false);
		textNomeColetor.setBounds(108, 67, 247, 21);

		textCodigo = new Text(Cabecalho, SWT.BORDER);
		textCodigo.setEnabled(false);
		textCodigo.setBounds(108, 21, 76, 21);

		Label lblComunidade = new Label(Cabecalho, SWT.NONE);
		lblComunidade.setBounds(352, 24, 81, 15);
		lblComunidade.setText("Comunidade");

		textComunidade = new Text(Cabecalho, SWT.BORDER);
		textComunidade.setEnabled(false);
		textComunidade.setBounds(439, 21, 237, 21);

		Label lblData = new Label(Cabecalho, SWT.NONE);
		lblData.setBounds(401, 67, 32, 15);
		lblData.setText("Data");

		textData = new Text(Cabecalho, SWT.BORDER);
		textData.setEnabled(false);
		textData.setBounds(439, 64, 237, 21);

		table = new Table(shlbalancete, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(22, 146, 738, 270);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnCdigo = new TableColumn(table, SWT.NONE);
		tblclmnCdigo.setWidth(104);
		tblclmnCdigo.setText("Código ");

		tblclmnNomeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnNomeDizimista.setWidth(260);
		tblclmnNomeDizimista.setText("Nome Dizimista");

		tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(128);
		tblclmnData.setText("Data");

		tblclmnValorDaContribuio = new TableColumn(table, SWT.NONE);
		tblclmnValorDaContribuio.setWidth(158);
		tblclmnValorDaContribuio.setText("Valor da Contribuição");

		Label lblTotal = new Label(shlbalancete, SWT.NONE);
		lblTotal.setBounds(550, 425, 37, 15);
		lblTotal.setText("Total");

		textTotal = new Text(shlbalancete, SWT.BORDER);
		textTotal.setEnabled(false);
		textTotal.setEditable(false);
		textTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textTotal.setBounds(593, 422, 147, 21);

		btnIncluir = new Button(shlbalancete, SWT.NONE);
		btnIncluir.setImage(SWTResourceManager.getImage("./icones/plus.png"));
		btnIncluir.setBounds(22, 422, 75, 25);
		btnIncluir.setText("Incluir");

		btnExcluir = new Button(shlbalancete, SWT.NONE);
		btnExcluir.setImage(SWTResourceManager.getImage("./icones/subtract.png"));
		btnExcluir.setBounds(127, 422, 75, 25);
		btnExcluir.setText("Excluir");

		btnExcluirTudo = new Button(shlbalancete, SWT.NONE);
		btnExcluirTudo.setBounds(234, 422, 117, 25);
		btnExcluirTudo.setImage(SWTResourceManager.getImage("./icones/deleteall.png"));
		btnExcluirTudo.setText("Excluir Tudo");

		btnFinalizar = new Button(shlbalancete, SWT.NONE);
		btnFinalizar.setBounds(384, 422, 75, 25);
		btnFinalizar.setText("Finalizar ?");

		btnGravar = new Button(shlbalancete, SWT.NONE);
		btnGravar.setBounds(665, 460, 75, 25);
		btnGravar.setText("Gravar");

		btnCancelar = new Button(shlbalancete, SWT.NONE);
		btnCancelar.setBounds(569, 460, 75, 25);
		btnCancelar.setText("Cancelar");

	}
}
