package telas.Pesquiar;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import beans.BalanceteDizimo;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.BalanceteDizimoDao;
import movimentacoes.BalanceteAction;
import telas.Inicial;

public class WindowRecuperarBalanceteAction extends WindowRecuperarBalancete {

	private boolean permissaopesquisar = false;
	private AutenticadorUsuario at;
	private List<BalanceteDizimo> listabalBalanceteDizimo;
	private BalanceteDizimo bal;

	public void verificarpermissoes() {
		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			at = new AutenticadorUsuario();
			if (AutenticadorUsuario.isColetor()) {
				permissaopesquisar = at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(),
						new Permissoes(31, "Pesquisar balancete"));
			} else {
				permissaopesquisar = at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
						new Permissoes(31, "Pesquisar balancete"));
			}
			Inicial.fechaconexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

	}

	public void tratarmudancasJanelas() {

		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				grpPerodo.dispose();
				if (combo.getSelectionIndex() == 0) {
					grpCdigo = new Group(shell, SWT.NONE);
					grpCdigo.setText("Código");
					grpCdigo.setBounds(185, 10, 367, 82);

					textCodigo = new Text(grpCdigo, SWT.BORDER);
					textCodigo.setBounds(88, 39, 175, 21);
				} else if (grpCdigo != null) {
					grpCdigo.dispose();
				}

				if (combo.getSelectionIndex() == 1 && grpPerodo.isDisposed()) {
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
				} else if (!grpPerodo.isDisposed()) {
					grpPerodo.dispose();
				}

				if (combo.getSelectionIndex() == 2) {
					textColetor = new Text(shell, SWT.BORDER);
					textColetor.setBounds(181, 42, 371, 21);
				} else if (textColetor != null) {
					textColetor.dispose();
				}

				if (combo.getSelectionIndex() == 3) {
					grpDizimista = new Group(shell, SWT.NONE);
					grpDizimista.setText("Dizimista");
					grpDizimista.setBounds(180, 10, 382, 82);
					textDizimista = new Text(grpDizimista, SWT.BORDER);
					textDizimista.setBounds(10, 36, 362, 21);
				} else if (grpDizimista != null) {
					grpDizimista.dispose();
				}

				if (combo.getSelectionIndex() == 4) {
					grpPerodoEColetor = new Group(shell, SWT.NONE);
					grpPerodoEColetor.setText("Período e Coletor");
					grpPerodoEColetor.setBounds(179, 10, 379, 85);

					lblColetor = new Label(grpPerodoEColetor, SWT.NONE);
					lblColetor.setBounds(10, 25, 44, 15);
					lblColetor.setText("Coletor");

					text = new Text(grpPerodoEColetor, SWT.BORDER);
					text.setBounds(60, 22, 297, 21);

					lblDe_1 = new Label(grpPerodoEColetor, SWT.NONE);
					lblDe_1.setBounds(24, 60, 25, 15);
					lblDe_1.setText("De");

					comboPeriodoColetorDe = new DateChooserCombo(grpPerodoEColetor, SWT.BORDER);
					comboPeriodoColetorDe.setBounds(55, 56, 86, 21);

					lblAt_1 = new Label(grpPerodoEColetor, SWT.NONE);
					lblAt_1.setBounds(159, 60, 31, 15);
					lblAt_1.setText("Até");

					comboPeriodoColetorAte = new DateChooserCombo(grpPerodoEColetor, SWT.BORDER);
					comboPeriodoColetorAte.setBounds(199, 54, 86, 21);
				} else if (grpPerodoEColetor != null) {
					grpPerodoEColetor.dispose();
				}
			}
		});

	}

	public void tratarEventosPesquisar() {
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (permissaopesquisar) {
						PropriedadesShell.limapartabela(table);
						BalanceteDizimoDao baldiziDao = new BalanceteDizimoDao();
						baldiziDao.setCon(Inicial.startaPropertiesConnection());

						if (combo.getSelectionIndex() == 0 && !textCodigo.getText().isEmpty()) {

							BalanceteDizimo b = baldiziDao.pesquisarCodigo(Integer.valueOf(textCodigo.getText()));

							if (b != null) {
								listabalBalanceteDizimo = new ArrayList<BalanceteDizimo>();
								listabalBalanceteDizimo.add(b);
								table.setItemCount(listabalBalanceteDizimo.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0,
											Integer.toString(listabalBalanceteDizimo.get(i).getIdbalancete()));
									itens[i].setText(1, listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
									if (listabalBalanceteDizimo.get(i).getVerificador().equals("s")) {
										itens[i].setText(2, listabalBalanceteDizimo.get(i).getDataEntregaBalancete()
												.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
										itens[i].setText(3, "Sim");
									} else {
										itens[i].setText(2, "");
										itens[i].setText(3, "Não");
									}

								}

							} else {
								PropriedadesShell.mensagemDeRetorno("Não foi encontrado nenhum registro");
							}
							Inicial.fechaconexao();

						} else if (combo.getSelectionIndex() == 1) {

							if (dateChooserComboDe.getValue() != null && dateChooserComboAte.getValue() != null) {

								java.util.Date dataDe = dateChooserComboDe.getValue();
								java.util.Date dataAte = dateChooserComboAte.getValue();
								LocalDate localDe = dataDe.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								LocalDate localAte = dataAte.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

								listabalBalanceteDizimo = baldiziDao.pesquisarListaPeriodo(localDe, localAte);
								if (listabalBalanceteDizimo != null && !listabalBalanceteDizimo.isEmpty()) {

									table.setItemCount(listabalBalanceteDizimo.size());
									TableItem itens[] = table.getItems();
									for (int i = 0; i < itens.length; i++) {
										itens[i].setText(0,
												Integer.toString(listabalBalanceteDizimo.get(i).getIdbalancete()));
										itens[i].setText(1,
												listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
										if (listabalBalanceteDizimo.get(i).getVerificador().equals("s")) {
											itens[i].setText(2, listabalBalanceteDizimo.get(i).getDataEntregaBalancete()
													.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
											itens[i].setText(3, "Sim");
										} else {
											itens[i].setText(2, "");
											itens[i].setText(3, "Não");
										}
									}
								} else {
									PropriedadesShell
											.mensagemDeRetorno("Não foi encontrado nenhum registro com esse argumento");
								}

							} else {

								PropriedadesShell.mensagemDeRetorno(
										"Informe os argumentos necessarios para realizar esta comsulta");
							}

						} else if (combo.getSelectionIndex() == 2 && !textColetor.getText().isEmpty()) {

							listabalBalanceteDizimo = baldiziDao.pesquisarListaNomeColetor(textColetor.getText());
							if (!listabalBalanceteDizimo.isEmpty()) {
								table.setItemCount(listabalBalanceteDizimo.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0,
											Integer.toString(listabalBalanceteDizimo.get(i).getIdbalancete()));
									itens[i].setText(1, listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
									if (listabalBalanceteDizimo.get(i).getVerificador().equals("s")) {
										itens[i].setText(2, listabalBalanceteDizimo.get(i).getDataEntregaBalancete()
												.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
										itens[i].setText(3, "Sim");
									} else {
										itens[i].setText(2, "");
										itens[i].setText(3, "Não");
									}
								}

							} else {
								PropriedadesShell
										.mensagemDeRetorno("Não foi encontrado nenhum registro com este argumento");
							}

						} else if (combo.getSelectionIndex() == 3 && !textDizimista.getText().isEmpty()) {
							listabalBalanceteDizimo = baldiziDao.pesquisarListaNomeDizimista(textDizimista.getText());
							if (!listabalBalanceteDizimo.isEmpty()) {
								table.setItemCount(listabalBalanceteDizimo.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0,
											Integer.toString(listabalBalanceteDizimo.get(i).getIdbalancete()));
									itens[i].setText(1, listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
									if (listabalBalanceteDizimo.get(i).getVerificador().equals("s")) {
										itens[i].setText(2, listabalBalanceteDizimo.get(i).getDataEntregaBalancete()
												.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
										itens[i].setText(3, "Sim");
									} else {
										itens[i].setText(2, "");
										itens[i].setText(3, "Não");
									}
								}

							} else {
								PropriedadesShell
										.mensagemDeRetorno("Não foi encontrado nenhum registro com este argumento");
							}
						} else if (combo.getSelectionIndex() == 4) {

							java.util.Date datade = comboPeriodoColetorDe.getValue();
							java.util.Date dataAte = comboPeriodoColetorAte.getValue();

							if (datade != null && dataAte != null && !text.getText().isEmpty()) {

								LocalDate localDe = datade.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								LocalDate localAte = dataAte.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

								comboPeriodoColetorAte.getValue();
								listabalBalanceteDizimo = baldiziDao.pesquisarListaPeriodoeNome(localDe, localAte,
										text.getText());

								if (listabalBalanceteDizimo != null && !listabalBalanceteDizimo.isEmpty()) {
									table.setItemCount(listabalBalanceteDizimo.size());
									TableItem itens[] = table.getItems();
									for (int i = 0; i < itens.length; i++) {
										itens[i].setText(0,
												Integer.toString(listabalBalanceteDizimo.get(i).getIdbalancete()));
										itens[i].setText(1,
												listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
										if (listabalBalanceteDizimo.get(i).getVerificador().equals("s")) {
											itens[i].setText(2, listabalBalanceteDizimo.get(i).getDataEntregaBalancete()
													.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
											itens[i].setText(3, "Sim");
										} else {
											itens[i].setText(2, "");
											itens[i].setText(3, "Não");
										}
									}

								} else {
									PropriedadesShell
											.mensagemDeRetorno("Não foi encontrado nenhum registro com este argumento");
								}

							} else {
								PropriedadesShell
										.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
							}

						}else{
							PropriedadesShell.mensagemDeRetorno("Informe os argumentos necessários para realizar a consulta");
						} 
					} else {
						PropriedadesShell
								.mensagemDeRetorno("Usuário sem permissão para acessar o recurso: Pesquisar Balancete");
					}
				} catch (SQLException e1) {

					PropriedadesShell.mensagemDeRetorno("Ocorreu um erro SQL. Verifique o log e tente novamente");
					Inicial.fechaconexao();
					new EjetaException(e1);
				} catch (NumberFormatException e2) {
					PropriedadesShell.mensagemDeRetorno("O Argumento não é um número");
					Inicial.fechaconexao();
					new EjetaException(e2);
				} catch (Exception e2) {
					Inicial.fechaconexao();
					PropriedadesShell.mensagemDeRetorno("Ocorreu um erro. Verifique o log e tente novamente");
					new EjetaException(e2);
				}
				Inicial.fechaconexao();

			}

		});
	}

	public void tratarEventosEditar() {
		tltmAceitar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int i = table.getSelectionIndex();
				if (i != -1) {
					BalanceteDizimoDao balDao = new BalanceteDizimoDao();
					try {
						TableItem item = table.getItem(i);

						balDao.setCon(Inicial.startaPropertiesConnection());
						bal = balDao.pesquisarListaPopularTela(Integer.valueOf(item.getText()));

						if (bal != null && bal.getVerificador().equals("n")) {
							BalanceteAction bc = new BalanceteAction();
							bc.setBalDizimo(bal);
							bc.verificarPermissaoEditar();

						} else {
							PropriedadesShell
									.mensagemDeRetorno("A coleta não pode ser editada pois já foi confirmada !");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NullPointerException nullexception) {
						new EjetaException(nullexception);
						nullexception.printStackTrace();
					}

					Inicial.fechaconexao();
				} else {
					PropriedadesShell.mensagemDeRetorno("Selecione um item para ser editado");
				}

			}
		});
	}

	public void tratarEventosExcluir() {
		tltmExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				if (new BalanceteAction().verificarPermissaoExcluir()) {
					int i = table.getSelectionIndex();
					if (i != -1) {

						BalanceteDizimoDao dizidao = new BalanceteDizimoDao();
						try {
							dizidao.setCon(Inicial.startaPropertiesConnection());
							if (listabalBalanceteDizimo.get(i).getVerificador().equals("n")) {
								dizidao.delete(listabalBalanceteDizimo.get(i).getIdbalancete());
								PropriedadesShell.limapartabela(table);
							}else {
								PropriedadesShell.mensagemDeErro("Não é possível excluir. "
										+ "Esse registro foi certificado pelo coletor: "+listabalBalanceteDizimo.get(i).getColetor().getNomeUsuario());
							}

						} catch (SQLException esql) {
							// TODO Auto-generated catch block
							Inicial.fechaconexao();
							PropriedadesShell.mensagemDeErro(
									"Este registro esta participando de outros registros, por isso não pode ser excluido.");
							new EjetaException(esql);
						} catch (Exception egeneric) {
							PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
							Inicial.fechaconexao();
							new EjetaException(egeneric);
						}

					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione uma registro para excluir");
					} 
				}
				Inicial.fechaconexao();
			}
		});
	}

	public WindowRecuperarBalanceteAction() {
		verificarpermissoes();
		super.createContents();
		tratarmudancasJanelas();
		tratarEventosPesquisar();
		tratarEventosExcluir();
		tratarEventosEditar();
		super.openShell();

	}

}
