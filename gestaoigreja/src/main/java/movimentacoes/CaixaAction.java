package movimentacoes;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CaixaDao;
import telas.Inicial;

public class CaixaAction extends Caixa {

	private Permissoes permissoes[] = { new Permissoes(34, "Caixa") };
	private List<Boolean> listaPermissoes;

	public CaixaAction() {
		listaPermissoes = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
	}

	public void verificarPermissaoCaixa() throws SQLException {
		if (listaPermissoes.get(0)) {
			tratarEventos();
		} else {
			PropriedadesShell.mensagemDeRetorno(
					"Usuário sem permissao para acessar o recurso: " + permissoes[0].getNomepermissao());
			throw new SQLException("Usuário sem permissão para acessar o recurso: " + permissoes[0].getNomepermissao());

		}
	}

	private void tratarEventos() {
		super.createContents();
		tltmNewItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				LocalDate localde = dateChooserDe.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate localAte = dateChooserAte.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				CaixaDao cdao = new CaixaDao();
				try {
					cdao.setConnection(Inicial.startaPropertiesConnection());
					List<beans.Caixa> listaCaixa = cdao.compilarCaixa(localde, localAte);
					lbReaisCredito.setText(NumberFormat.getCurrencyInstance().format(cdao.getCredito()));
					lblReaisDebitos.setText(NumberFormat.getCurrencyInstance().format(cdao.getDebito()));
					lblReaisTotal.setText(NumberFormat.getCurrencyInstance().format(cdao.getTotal()));

					PropriedadesShell.limapartabela(table);
					if (listaCaixa != null && !listaCaixa.isEmpty()) {
						table.setItemCount(listaCaixa.size());
						TableItem items[] = table.getItems();
						for (int i = 0; i < listaCaixa.size(); i++) {
							items[i].setText(0, listaCaixa.get(i).getCodigoReferencia());
							items[i].setText(1, listaCaixa.get(i).getHistorico());
							items[i].setText(2,
									NumberFormat.getCurrencyInstance().format(listaCaixa.get(i).getValor()));
							items[i].setText(3, listaCaixa.get(i).getFormato().name());
							items[i].setText(4, listaCaixa.get(i).getO().name());
							items[i].setText(5, listaCaixa.get(i).getDataregistro()
									.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
						}
					}

					Inicial.fechaconexao();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception ex) {
					System.out.println("Ocorreu uma exception no:" + getClass().getCanonicalName());
					ex.printStackTrace();
					new EjetaException(ex);
				}
			}
		});
		super.open();
	}
}
