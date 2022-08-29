package telas;

import java.sql.SQLException;
import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Cidade;
import beans.Comunidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.ComunidadeDao;
import telas.Pesquiar.WindowCidadeRecuperarAssociarAction;

public class WindowComunidadeAction extends WindowComunidade {

	private Permissoes permissoes[] = { new Permissoes(6, "Cadastrar Comunidade"),
			new Permissoes(7, "Pesquisar Comunidade"), new Permissoes(8, "Excluir Comunidade"),
			new Permissoes(17, "Editar Comunidade") };
	private List<Boolean> listachecagem;
	private ComunidadeDao comunidadeDao;
	private Comunidade c;
	private Cidade cc;

	public WindowComunidadeAction() {
		listachecagem = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
	}

	public void verificarPermissaoPesquisar() throws NullPointerException {
		if (!listachecagem.get(1)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[1]);
			throw new NullPointerException(
					"Usuário sem permissao para acessar o recurso: " + permissoes[1].getNomepermissao());
		}
	}

	public void verificarPermissaoCadastrar() throws NullPointerException, Exception {
		if (listachecagem.get(0)) {

			super.createContents();
			puxarIndiceMaximo();
			this.tratarEventosCadastrar();
			super.open();
			Inicial.fechaconexao();

		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[0]);
			throw new NullPointerException("Usuário sem permissao para acessar o recurso " + permissoes[0]);
		}
	}

	public void verificarpermissaoEditar(String t) throws NullPointerException {
		if (listachecagem.get(3)) {
			comunidadeDao = new ComunidadeDao();
			try {
				comunidadeDao.setCon(Inicial.startaPropertiesConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				super.createContents();
				this.tratarEventosEditar();
				c = new Comunidade();
				c.setIdComunidade(Integer.valueOf(t));
				c = comunidadeDao.populartelaComunidade(c);
				cc = c.getCidade();
				this.popularATela();
				super.open();
				Inicial.fechaconexao();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				new EjetaException(e);
			}
		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[3]);
			throw new NullPointerException("Usuário sem permissao para acessar o recurso: " + permissoes[3]);
		}
	}

	public void verificarPermissaoExcluir(String t) {
		if (listachecagem.get(2)) {
			try {
				comunidadeDao = new ComunidadeDao();
				comunidadeDao.setCon(Inicial.startaPropertiesConnection());

				c = new Comunidade();
				c.setIdComunidade(Integer.valueOf(t));
				comunidadeDao.excluir(c);

			} catch (SQLException e) {
				PropriedadesShell
						.mensagemDeErro("Não é possível excluir esse registro pois está sendo utilizado por outros");

				new EjetaException(e);
			} catch (Exception e) {
				PropriedadesShell
						.mensagemDeErro("Não é possível excluir esse registro. Verifique o Log e tente novamente");

				new EjetaException(e);
			}
			Inicial.fechaconexao();
		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[2]);
		}
	}

	private void puxarIndiceMaximo() throws SQLException {
		comunidadeDao = new ComunidadeDao();
		comunidadeDao.setCon(Inicial.startaPropertiesConnection());
		c = comunidadeDao.pesquisarComunidadeIndiceMaximo();
		textCodigo.setText(Integer.toString(c.getIdComunidade()));
	}

	private void popularATela() {
		textCodigo.setText(Integer.toString(c.getIdComunidade()));
		textNomeFantasia.setText(c.getNomefantaziaComunidade());
		textRazaoSocial.setText(c.getNomerazaosocialComunidade());
		formattedText.getControl().setText(c.getCnpjComunidade());
		textCidade.setText(c.getCidade().getNomeCidade());
		textEndereco.setText(c.getEnderecoComunidade());
		textNumeroLogradouro.setText(c.getNumeroenderecoComunidade());
		if (c.getObservacoes() == null) {
			textObservacoes.setText("");
		} else {
			textObservacoes.setText(c.getObservacoes());
		}

	}

	private void limparTela() {
		textObservacoes.setText("");
		textNumeroLogradouro.setText("");
		textEndereco.setText("");
		textCidade.setText("");
		textRazaoSocial.setText("");
		formattedText.getControl().setText("");
		textNomeFantasia.setText("");
	}

	private void tratarEventosCadastrar() {

		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				limparTela();

			}
		});

		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					WindowCidadeRecuperarAssociarAction windowCidadeRecuperarParoquiaAction = new WindowCidadeRecuperarAssociarAction();
					if (windowCidadeRecuperarParoquiaAction.getC() != null) {
						textCidade.setText(windowCidadeRecuperarParoquiaAction.getC().getNomeCidade());
						cc = windowCidadeRecuperarParoquiaAction.getC();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("Não foi possível realizar a busca da cidade");
					new EjetaException(e1);
				}

			};
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
						&& !textNomeFantasia.getText().isEmpty()) {
					try {
						comunidadeDao.setCon(Inicial.startaPropertiesConnection());
						c.setNomefantaziaComunidade(textNomeFantasia.getText());
						c.setNomerazaosocialComunidade(textRazaoSocial.getText());
						c.setCnpjComunidade(formattedText.getControl().getText());
						c.setCidade(cc);
						c.setEnderecoComunidade(textEndereco.getText());
						c.setNumeroenderecoComunidade(textNumeroLogradouro.getText());
						c.setObservacoes(textObservacoes.getText());
						comunidadeDao.cadastrar(c);
						String numero = Integer
								.toString(comunidadeDao.pesquisarComunidadeIndiceMaximo().getIdComunidade());
						textCodigo.setText(numero);
						limparTela();
						Inicial.fechaconexao();
					} catch (Exception e1) {

						PropriedadesShell.mensagemDeErro("ocorreu um erro");
						new EjetaException(e1);
					}
				} else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos foram preenchidos corretamente");
				}
			}
		});

	}

	private void tratarEventosEditar() {

		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				limparTela();

			}
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
						&& !textNomeFantasia.getText().isEmpty()) {

					try {
						comunidadeDao.setCon(Inicial.startaPropertiesConnection());
						c.setNomefantaziaComunidade(textNomeFantasia.getText());
						c.setNomerazaosocialComunidade(textRazaoSocial.getText());
						c.setCnpjComunidade(formattedText.getControl().getText());
						c.setCidade(cc);
						c.setEnderecoComunidade(textEndereco.getText());
						c.setNumeroenderecoComunidade(textNumeroLogradouro.getText());
						c.setObservacoes(textObservacoes.getText());
						comunidadeDao.editar(c);

						Inicial.fechaconexao();

						shell.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeErro("ocorreu um erro");
						new EjetaException(e1);
					}
				} else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos foram preenchidos corretamente");
				}

			}
		});

		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					WindowCidadeRecuperarAssociarAction windowCidadeRecuperarParoquiaAction = new WindowCidadeRecuperarAssociarAction();
					if (windowCidadeRecuperarParoquiaAction.getC() != null) {
						textCidade.setText(windowCidadeRecuperarParoquiaAction.getC().getNomeCidade());
						cc = windowCidadeRecuperarParoquiaAction.getC();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("Não foi possível realizar a busca da cidade");
					new EjetaException(e1);
				}

			};
		});

	}

}
