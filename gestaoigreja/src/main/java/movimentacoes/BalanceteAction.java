package movimentacoes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.BalanceteDizimo;
import beans.Coletor;
import beans.DizimistavalorDia;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.BalanceteDizimoDao;
import dao.ColetorDao;
import telas.Inicial;

public class BalanceteAction extends Balancete {
	private Permissoes permissoes[] = { new Permissoes(30, "Cadastrar balancete"),
			new Permissoes(32, "Excluir balancete"), new Permissoes(33, "Editar balancete") };
	private List<Boolean> listaPermissoes;
	private BalanceteDizimo balDizimo;
	private LocalDate dataagora;

	public BalanceteAction() {
		listaPermissoes = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
	}

	public void verificarPermissaoCadastrar() {
		if (listaPermissoes.get(0)) {
			tratarEventosCadastrar();
		} else {
			PropriedadesShell.mensagemDeRetorno(
					"Usuário sem permissao para acessar o recurso: " + permissoes[0].getNomepermissao());
		}
	}

	public void verificarPermissaoEditar() {
		if (listaPermissoes.get(2)) {
			tratarEventosEditar();
		} else {
			PropriedadesShell.mensagemDeRetorno(
					"Usuário sem permissao para acessar o recurso: " + permissoes[2].getNomepermissao());
		}
	}

	public boolean verificarPermissaoExcluir() {
		if (!listaPermissoes.get(1)) {
			PropriedadesShell.mensagemDeRetorno(
					"Usuário sem permissao para acessar o recurso: " + permissoes[1].getNomepermissao());
			return false;
		}
		return true;

	}

	private void tratarEventosCadastrar() {
		super.createContents();
		dataagora = LocalDate.now();
		balDizimo = new BalanceteDizimo();
		balDizimo.setDataEntregaBalancete(dataagora);
		balDizimo.setValortotalrecebido(new BigDecimal("0"));
		textTotal.setText(NumberFormat.getCurrencyInstance().format(balDizimo.getValortotalrecebido()));
		botoes();
		try {
			Connection con = Inicial.startaPropertiesConnection();
			BalanceteDizimoDao balDao = new BalanceteDizimoDao();
			balDao.setCon(con);
			ColetorDao colDao = new ColetorDao();
			colDao.setConnectionOnComunidadeDao(con);
			Coletor coletor = new Coletor();
			coletor.setNomeUsuario(AutenticadorUsuario.getusuario().getNomeUsuario());
			List<Coletor> lista = colDao.pesquisarlistaColetorNome(coletor);
			textComunidade.setText(lista.get(0).getComunidade().getNomefantaziaComunidade());
			textNomeColetor.setText(lista.get(0).getNomeUsuario());
			balDizimo.setColetor(lista.get(0));
			textData.setText(dataagora.format(java.time.format.DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
			textCodigo.setText(Integer.toString(balDao.recuperarIndiceMaxRecebimento()));
			balDizimo.setIdbalancete(balDao.recuperarIndiceMaxRecebimento());
			Inicial.fechaconexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		} catch (Exception e) {
			new EjetaException(e);
		}
		super.open();

	}

	private void tratarEventosEditar() {
		super.createContents();
		popularTela();
		botoes();
		super.open();
	}

	public void popularTela() {
		PropriedadesShell.limapartabela(table);
		renderizarTabela(balDizimo.getListaDizimistavalorDia());
		textTotal.setText(NumberFormat.getCurrencyInstance().format(balDizimo.getValortotalrecebido()));
		textCodigo.setText(Integer.toString(balDizimo.getIdbalancete()));
		dataagora = LocalDate.now();
		textNomeColetor.setText(balDizimo.getColetor().getNomeUsuario());
		textData.setText(dataagora.format(java.time.format.DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
		textComunidade.setText(balDizimo.getColetor().getComunidade().getNomefantaziaComunidade());
	}

	private void botoes() {
		
		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!balDizimo.getListaDizimistavalorDia().isEmpty()) {
					balDizimo.setVerificador("n");
					BalanceteDizimoDao bcD = new BalanceteDizimoDao();
					try {
						bcD.setCon(Inicial.startaPropertiesConnection());
						bcD.delete(balDizimo.getIdbalancete());
						bcD.cadastrarBalancete(balDizimo);
						Inicial.fechaconexao();
						shlbalancete.dispose();
					} catch (SQLException e1) {
						PropriedadesShell.mensagemDeErro("Ocorreu um erro SQL. Verifique o log e tente novamente");
						new EjetaException(e1);
					} catch (Exception e1) {
						PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}
					shlbalancete.dispose();
				} else {
					PropriedadesShell.mensagemDeRetorno(
							"Não é possível prosseguir. Não há nenhum dizimista cadastrado neste balancete");
				}

			}
		});

		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				shlbalancete.dispose();
			}
		});

		btnExcluirTudo.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				PropriedadesShell.limapartabela(table);

				balDizimo.setValortotalrecebido(balDizimo.getValortotalrecebido().ZERO);
				textTotal.setText(NumberFormat.getCurrencyInstance().format(balDizimo.getValortotalrecebido()));
				balDizimo.getListaDizimistavalorDia().clear();
			}
		});

		btnExcluir.addSelectionListener(new SelectionAdapter() {

			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (!balDizimo.getListaDizimistavalorDia().isEmpty()) {
						int i = table.getSelectionIndex();
						System.out.println(i);
						if (i != -1) {
							balDizimo.getListaDizimistavalorDia().remove(i);
							PropriedadesShell.limapartabela(table);
							renderizarTabela(balDizimo.getListaDizimistavalorDia());
							balDizimo.setValortotalrecebido(balDizimo.getValortotalrecebido().ZERO);
							for (int x = 0; x < balDizimo.getListaDizimistavalorDia().size(); x++) {
								balDizimo.somandoValores(
										balDizimo.getListaDizimistavalorDia().get(x).getValorcontribuido());
							}
							textTotal.setText(
									NumberFormat.getCurrencyInstance().format(balDizimo.getValortotalrecebido()));

						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um dizimista para limpar a tabela");
						}

					} else {
						PropriedadesShell.mensagemDeRetorno("Não há nenhum dizimista inserido nesta tabela");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		btnIncluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TelaAuxiliarAction tela = new TelaAuxiliarAction();
					if (tela.getDizimista() != null && tela.getValor() != null) {

						DizimistavalorDia dizimistavalorDia = new DizimistavalorDia();

						dizimistavalorDia.setDizimista(tela.getDizimista());
						dizimistavalorDia.setDatacontribuida(LocalDate.now());
						dizimistavalorDia.setValorcontribuido(tela.getValor());
						balDizimo.getListaDizimistavalorDia().add(dizimistavalorDia);

						PropriedadesShell.limapartabela(table);

						renderizarTabela(balDizimo.getListaDizimistavalorDia());
						balDizimo.somandoValores(dizimistavalorDia.getValorcontribuido());
						textTotal.setText(NumberFormat.getCurrencyInstance().format(balDizimo.getValortotalrecebido()));

					} else {
						PropriedadesShell.mensagemDeErro("Erro ao carregar o dizimista");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("ocorreu um erro. Tente novamente");
					new EjetaException(e1);
				}
			}
		});

		btnFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!balDizimo.getListaDizimistavalorDia().isEmpty()) {
					ConfirmacaoAction cf = new ConfirmacaoAction();
					cf.open();
					if (cf.isIsconfirmed()) {
						balDizimo.setVerificador("s");
						BalanceteDizimoDao bcD = new BalanceteDizimoDao();
						try {
							bcD.setCon(Inicial.startaPropertiesConnection());
							bcD.delete(balDizimo.getIdbalancete());
							bcD.cadastrarBalancete(balDizimo);
							Inicial.fechaconexao();
							shlbalancete.dispose();
						} catch (SQLException e1) {
							PropriedadesShell.mensagemDeErro("Ocorreu um erro SQL. Verifique o log e tente novamente");
							new EjetaException(e1);
						} catch (Exception e1) {
							PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
							// TODO Auto-generated catch block
							new EjetaException(e1);
						}
						shlbalancete.dispose();
					}
				} else {
					PropriedadesShell.mensagemDeRetorno(
							"Não é possível prosseguir. Não há nenhum dizimista cadastrado neste balancete");
				}
			}
		});
	}

	public BalanceteDizimo getBalDizimo() {
		return balDizimo;
	}

	public void setBalDizimo(BalanceteDizimo balDizimo) {
		this.balDizimo = balDizimo;
	}

	public void renderizarTabela(List<DizimistavalorDia> lista) {

		for (int a = 0; a < lista.size(); a++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, Integer.toString(lista.get(a).getDizimista().getIdDizimista()));
			item.setText(1, lista.get(a).getDizimista().getNomeDizimista());
			item.setText(2, lista.get(a).getDatacontribuida()
					.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			item.setText(3, NumberFormat.getCurrencyInstance().format(lista.get(a).getValorcontribuido()));
		}
	}

}
