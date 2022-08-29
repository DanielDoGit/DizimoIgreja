package telas;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Cidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CidadeDao;

public class WindowCidadeAction extends WindowCidade {

	private Permissoes permissoes[] = { new Permissoes(2, "Cadastrar Cidade"), new Permissoes(4, "Editar Cidade"),
			new Permissoes(5, "Excluir Cidade"), new Permissoes(3, "Pesquisar Cidade") };
	private List<Boolean> listachecagem;
	private Cidade cidade;

	public WindowCidadeAction() {
		listachecagem = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
	}

	public void verificarPermissaoEditar(Cidade cidade) throws SQLException {
		if (listachecagem.get(1)) {
			this.cidade = cidade;
			super.createContents();
			popularTela();
			tratarEventosEditar();
			super.open();
		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[1]);
			throw new SQLException("Usuário sem permissao para acessar o recurso: " + permissoes[1]);
		}
	}
	
	public void verificarPermissaoExcluir() throws SQLException {
		if (!listachecagem.get(2)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[2]);
			throw new SQLException("Usuário sem permissao para acessar o recurso: " + permissoes[2]);
		}
	}
	
	public void verificarPermissaPesquisar() throws SQLException{
		if (!listachecagem.get(3)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[3]);
			throw new SQLException("Usuário sem permissao para acessar o recurso: " + permissoes[3]);
		}
	}

	public void verificarPermissaoCasdastrar() throws SQLException {
		if (listachecagem.get(0)) {
			super.createContents();
			puxarindiceMaximo();
			tratarEventosCadastrar();
			super.open();
		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[0]);
			throw new SQLException("Ususário sem permissao para acessar o recurso: " + permissoes[0]);
		}
	}

	private void tratarEventosCadastrar() {
		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_2.setText("");
				text_1.setText("");
			}

		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Integer i;
				if (!text_1.getText().isEmpty() && !text_2.getText().isEmpty()) {
					Cidade c = new Cidade();
					c.setIdCidade(Integer.valueOf(text.getText()));
					c.setNomeCidade(text_1.getText());
					c.setUfCidade(text_2.getText());
					try {
						CidadeDao cidadeDao = new CidadeDao();
						cidadeDao.setConnection(Inicial.startaPropertiesConnection());
						cidadeDao.cadastrar(c);
						Inicial.fechaconexao();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}
					i = new Integer(Integer.valueOf(text.getText()) + 1);
					text.setText(i.toString());
					text_2.setText("");
					text_1.setText("");
				} else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
				}
			}
		});

	}

	private void puxarindiceMaximo() {
		try {

			CidadeDao cD = new CidadeDao();
			cD.setConnection(Inicial.startaPropertiesConnection());
			cidade = cD.retornaCidadeIndiceMaximo();
			if (cidade != null) {

				text.setText(Integer.toString(cidade.getIdCidade()));

			} else {
				text.setText("1");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}
	
	private void popularTela() {
		text.setText(Integer.toString(cidade.getIdCidade()));
		text_1.setText(cidade.getNomeCidade());
		text_2.setText(cidade.getUfCidade());
	}

	private void tratarEventosEditar() {
		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_2.setText("");
				text_1.setText("");
			}

		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				Cidade c = new Cidade();
				c.setIdCidade(Integer.valueOf(text.getText()));
				c.setNomeCidade(text_1.getText());
				c.setUfCidade(text_2.getText());

				if (!text_1.getText().isEmpty() && !text_2.getText().isEmpty()) {

					try {
						CidadeDao cidadeDao = new CidadeDao();
						cidadeDao.setConnection(Inicial.startaPropertiesConnection());
						cidadeDao.editar(c);
						Inicial.fechaconexao();
						shell.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}

				} else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
				}

			}

		});
	}

}
