package movimentacoes;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import beans.Dizimista;
import comum.EjetaException;
import comum.PropriedadesShell;
import telas.Pesquiar.WindowDizimistaRecuperarAssociarAction;
import telas.Pesquiar.WindowRecuperarDizimista;

public class TelaAuxiliarAction extends TelaAuxiliar {

	private Dizimista dizimista;
	private BigDecimal valor;

	public TelaAuxiliarAction() {
		super.createContents();
		tratareventos();
		super.open();
	}

	public void tratareventos() {

		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					WindowDizimistaRecuperarAssociarAction rsc = new WindowDizimistaRecuperarAssociarAction();
					dizimista = rsc.getDizimista();
					text.setText(rsc.getDizimista().getNomeDizimista());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("Dizimista não Carregado");
					new EjetaException(e1);
				}

			}
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!text.getText().isEmpty() && !text_1.getText().isEmpty()) {
					shlAuxiliar.dispose();
				} else {
					PropriedadesShell.mensagemDeRetorno("Verifique os campos obrigatorios.");
				}
			}
		});

		text_1.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					Text t = (Text) e.getSource();
					t.selectAll();
					if (!t.getText().isEmpty()) {
						
						String aux = t.getText().replaceAll(",", ".");
						text_1.setText(NumberFormat.getCurrencyInstance().format(valor = new BigDecimal(aux)));
					}
				} catch (Exception e1) {
					PropriedadesShell
							.mensagemDeRetorno("Não pode conter letras ou caracteres especiais no valor digitado");
					text_1.setText("");
					e1.printStackTrace();
				}
			}
		});

	}

	public Dizimista getDizimista() {
		return dizimista;
	}

	public void setDizimista(Dizimista dizimista) {
		this.dizimista = dizimista;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
