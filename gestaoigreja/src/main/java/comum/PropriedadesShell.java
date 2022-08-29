package comum;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import beans.Permissoes;

public class PropriedadesShell {

	public static Point centralizarShell(Shell pai, Display display) {

		org.eclipse.swt.widgets.Monitor primary = display.getPrimaryMonitor();
		org.eclipse.swt.graphics.Rectangle bounds = primary.getBounds();
		org.eclipse.swt.graphics.Rectangle rect = pai.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		Point x1 = new Point(x, y);

		return x1;

	}

	public static void mensagemDeRetorno(String mensagem) {
		MessageBox dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK);
		dialog.setText("Mensagem");
		dialog.setMessage(mensagem);
		dialog.open();
	}

	public static void mensagemDeErro(String mensagem) {
		MessageBox dialog = new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK);
		dialog.setText("Mensagem de Erro");
		dialog.setMessage(mensagem);
		dialog.open();
	}

	public static void mensagemDeErroDetalhada(Exception e) {
		MessageBox dialog = new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK);
		dialog.setText("Ocoreu um Erro");
		dialog.setMessage(e.getMessage());
		dialog.open();

	}

	public static void limapartabela(Table table) {
		TableItem itens[] = table.getItems();
		TableColumn colunas[] = table.getColumns();
		for (int i = 0; i < colunas.length; i ++) {
			for (int x = 0; x < itens.length; x++) {
				itens[x].setText(i, "");
			}
		}
		table.setItemCount(0);
		
	}
	
	public static void mensagemRetornoUsuarioPermissao(Permissoes permissao) {
		MessageBox dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK);
		dialog.setText("Mensagem");
		dialog.setMessage("Usuário sem permissão para acessar o recurso: "+ permissao.getNomepermissao());
		dialog.open();
	}

}
