package telas;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Coletor;
import beans.Comunidade;
import beans.PermissaoColetor;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.ColetorDao;
import telas.Pesquiar.WindowCidadeRecuperarAssociarAction;
import telas.Pesquiar.WindowComunidadeRecuperarAssociarAction;

public class WindowColetorAction extends WindowColetor {

	private String verificacaoCadastrar = "Cadastrar", verificacaoEditar = "Editar";
	private Cidade cidade;
	private Comunidade comunidade;
	private List<Permissoes> lista, listapermissao;
	private Coletor coletor;

	public void setColetor(Coletor c) {
		this.coletor = c;
	}

	public WindowColetorAction(String a) {

		if (a.equals(verificacaoCadastrar)) {
			super.createContents();
			try {
				acessarComunidade();
				acessarCidade();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				new EjetaException(e);
			}
			buscarultimoindiceColetor();
			preenchimentotabelapermissoes();
			this.tratarEventosCadastrar();
			super.open();

		} else if (a.equals(verificacaoEditar)) {
			super.createContents();
			try {
				acessarComunidade();
				acessarCidade();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				new EjetaException(e);
			}
			preenchimentotabelapermissoes();
			this.tratarEventosEditar();

		} 

	}

	public void tratarEventosCadastrar() {
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			
				textCelular.setText("");
				textCidade.setText("");
				textComunidade.setText("");
				textCpf.setText("");
				textEndereco.setText("");
				textLogin.setText("");
				textNome.setText("");
				textNumeroEndereco.setText("");
				textObservacao.setText("");
				textCodigo.setText("");
				dateChooserCombo.setValue(null);
				textRG.setText("");
				textTelefone.setText("");
				textSenha.setText("");
				
				itens = table.getItems();

				try {
					for (int i = 0; i < itens.length; i++) {
						if (itens[i].getChecked()) {
							itens[i].setChecked(false);
						} 
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
				
			}
			
		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				coletor = new Coletor();
				int aux = 0;

				try {
					ColetorDao coletorDao = new ColetorDao();
					coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
					AutenticadorUsuario.setCon(coletorDao.getConection());

					TableItem[] listaatributos = table.getItems();
					List<Permissoes> listaauxiliar = new ArrayList<Permissoes>();
					for (int a = 0; a < listaatributos.length; a++) {
						if (listaatributos[a].getChecked()) {
							aux++;
							listaauxiliar.add(lista.get(a));
						}
					}

				
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(13, "Cadastrar Coletor");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes) || autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(), permissoes)) {
						if (!textNome.getText().isEmpty() && !textCpf.getText().isEmpty() && !textRG.getText().isEmpty()
								&& !textComunidade.getText().isEmpty() && !textCidade.getText().isEmpty()
								&& !textLogin.getText().isEmpty() && !textSenha.getText().isEmpty()) {

							Date data = dateChooserCombo.getValue();

							if (data != null) {
								LocalDate hoje = LocalDate.now();
								LocalDate nascimento = Instant.ofEpochMilli(data.getTime())
										.atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toLocalDate();
								int i = Period.between(nascimento, hoje).getYears();
								if (i >= 18 && i < 100) {
									coletor.setIdUsuario(Integer.valueOf(textCodigo.getText()));
									coletor.setNomeUsuario(textNome.getText());
									coletor.setCidadeUsuario(cidade);
									coletor.setComunidade(comunidade);
									coletor.setCpfUsuario(textCpf.getText());
									coletor.setRgUsuario(textRG.getText());
									coletor.setCelularUsuario(textCelular.getText());
									coletor.setTelefoneUsuario(textTelefone.getText());
									coletor.setDatanascimentoUsuario(nascimento);
									coletor.setEnderecoUsuario(textEndereco.getText());
									coletor.setNumeroenderecoUsuario(textNumeroEndereco.getText());
									coletor.setObservacoesUsuario(textObservacao.getText());
									coletor.setDataCadastro(hoje);
									coletor.setLoginUsuario(textLogin.getText());
									coletor.setSenhaUsuario(textSenha.getText());
									if (btnAtivo.getSelection()) {
										coletor.setIsativo("S");
									} else {
										coletor.setIsativo("N");
									}

									if (aux > 0) {
										coletorDao.inserirColetor(coletor);
										new AutenticadorUsuario()
												.inputpermissaoColetor(new PermissaoColetor(coletor, listaauxiliar));
										limparAtela();
									} else {
										PropriedadesShell.mensagemDeRetorno(
												"Você precisa conceder ao menos 1 permissão para este coletor");
									}

								} else {
									PropriedadesShell.mensagemDeRetorno(
											"Não é possível cadastrar esse coletor. Sua idade é de: " + i + " ano(s)");
								}
							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Não é possível prosseguir, pois o campo data não foi preenchido");
							}

						} else {
							PropriedadesShell
									.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
						} 
					}
					Inicial.fechaconexao();

				} catch (Exception e1) {
					PropriedadesShell
							.mensagemDeErro("Não foi possível inserir o coletor, verifique o log e tente novamente.");
					new EjetaException(e1);
				}
			}

		});
	}

	public void tratarEventosEditar() {

		btnNewButton.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			
				textCelular.setText("");
				textCidade.setText("");
				textComunidade.setText("");
				textCpf.setText("");
				textEndereco.setText("");
				textLogin.setText("");
				textNome.setText("");
				textNumeroEndereco.setText("");
				textObservacao.setText("");
				textCodigo.setText("");
				dateChooserCombo.setValue(null);
				textRG.setText("");
				textTelefone.setText("");
				textSenha.setText("");
				
				itens = table.getItems();

				try {
					for (int i = 0; i < itens.length; i++) {
						if (itens[i].getChecked()) {
							itens[i].setChecked(false);
						} 
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
				
			}
			
		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					Connection cone = Inicial.startaPropertiesConnection();
					ColetorDao coletorDao = new ColetorDao();
					coletorDao.setConnectionOnComunidadeDao(cone);
					AutenticadorUsuario.setCon(cone);
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					TableItem[] listaatributos = table.getItems();
					List<Permissoes> listaauxiliar = new ArrayList<Permissoes>();
					for (int a = 0; a < listaatributos.length; a++) {
						if (listaatributos[a].getChecked()) {
							listaauxiliar.add(lista.get(a));
						}
					}

					if (!textNome.getText().isEmpty() && !textCpf.getText().isEmpty() && !textRG.getText().isEmpty()
							&& !textComunidade.getText().isEmpty() && !textCidade.getText().isEmpty()
							&& !textLogin.getText().isEmpty() && !textSenha.getText().isEmpty()) {

						Date data = dateChooserCombo.getValue();

						if (data != null) {
							LocalDate hoje = LocalDate.now();
							LocalDate nascimento = Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault())
									.truncatedTo(ChronoUnit.DAYS).toLocalDate();
							int i = Period.between(nascimento, hoje).getYears();
							if (i >= 18 && i < 100) {
								coletor.setIdUsuario(Integer.valueOf(textCodigo.getText()));
								coletor.setNomeUsuario(textNome.getText());
								coletor.setCidadeUsuario(cidade);
								coletor.setComunidade(comunidade);
								coletor.setCpfUsuario(textCpf.getText());
								coletor.setRgUsuario(textRG.getText());
								coletor.setCelularUsuario(textCelular.getText());
								coletor.setTelefoneUsuario(textTelefone.getText());
								coletor.setDatanascimentoUsuario(nascimento);
								coletor.setEnderecoUsuario(textEndereco.getText());
								coletor.setNumeroenderecoUsuario(textNumeroEndereco.getText());
								coletor.setObservacoesUsuario(textObservacao.getText());
								coletor.setDataCadastro(hoje);
								coletor.setLoginUsuario(textLogin.getText());
								coletor.setSenhaUsuario(textSenha.getText());
								if (btnAtivo.getSelection()) {
									coletor.setIsativo("S");
								} else {
									coletor.setIsativo("N");
								}

								if (listaauxiliar.size() > 0) {
									autenticadorUsuario.deletePermissoesColetor(coletor);
									autenticadorUsuario
											.inputpermissaoColetor(new PermissaoColetor(coletor, listaauxiliar));
									coletorDao.editarColetor(coletor);
									shlColetor.dispose();
								} else {
									PropriedadesShell.mensagemDeRetorno(
											"Você precisa conceder ao menos 1 permissão para este coletor");
								}

							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Não é possível cadastrar esse coletor. Sua idade é de: " + i + " ano(s)");
							}
						} else {
							PropriedadesShell.mensagemDeRetorno(
									"Não é possível prosseguir, pois o campo data não foi preenchido");
						}

					} else {
						PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
					}

					Inicial.fechaconexao();
				} catch (Exception e1) {
					PropriedadesShell
							.mensagemDeErro("Não foi possível editar o coletor, verifique o log e tente novamente.");
					new EjetaException(e1);

				}
			}

		});

	}

	public void preenchimentotabelapermissoes() {
		AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			lista = autenticadorUsuario.popularPermissoes();

			for (int i = 0; i < lista.size(); i++) {
				tableItem = new TableItem(table, SWT.NONE | SWT.CENTER);
				tableItem.setText(1, lista.get(i).getNomepermissao());

			}
			AutenticadorUsuario.getCon().close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			new EjetaException(e2);
		}

		tblclmnNewColumn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				itens = table.getItems();

				try {
					for (int i = 0; i < itens.length; i++) {
						if (!itens[i].getChecked()) {
							itens[i].setChecked(true);
						} else {
							itens[i].setChecked(false);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

	}

	public void buscarultimoindiceColetor() {
		ColetorDao coletorDao = new ColetorDao();
		try {
			coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
			textCodigo = new Text(composite, SWT.BORDER | SWT.CENTER);
			textCodigo.setEnabled(false);
			textCodigo.setBounds(259, 10, 47, 21);
			textCodigo.setText(String.valueOf(coletorDao.consultarIndiceMaximo().getIdUsuario() + 1));
			Inicial.fechaconexao();
		} catch (SQLException e) {
			new EjetaException(e);
		}

	}

	public void acessarComunidade() throws Exception {

		tltmPesquisarComunidade.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					WindowComunidadeRecuperarAssociarAction wcra = new WindowComunidadeRecuperarAssociarAction();
					comunidade = wcra.getC();
					if (comunidade != null) {
						textComunidade.setText(comunidade.getNomefantaziaComunidade());

					} else {
						PropriedadesShell.mensagemDeErro("Não foi possível carregar a comunidade");
						throw new NullPointerException("Não foi possível carregar a comunidade");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
			}

		});
	}

	public void limparAtela() {
		textCelular.setText("");
		textCidade.setText("");
		textComunidade.setText("");
		textCpf.setText("");
		textEndereco.setText("");
		textLogin.setText("");
		textNome.setText("");
		textNumeroEndereco.setText("");
		textObservacao.setText("");
		textCodigo.setText("");
		dateChooserCombo.setValue(null);
		textRG.setText("");
		textTelefone.setText("");
		textSenha.setText("");
		ColetorDao coletorDao = new ColetorDao();
		textCodigo.setText("");
		try {
			coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
			textCodigo.setText(String.valueOf(coletorDao.consultarIndiceMaximo().getIdUsuario() + 1));
			Inicial.fechaconexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

		itens = table.getItems();

		try {
			for (int i = 0; i < itens.length; i++) {

				itens[i].setChecked(false);

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			new EjetaException(e1);
		}
	}

	public void acessarCidade() {

		tltmPesquisar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					WindowCidadeRecuperarAssociarAction wc = new WindowCidadeRecuperarAssociarAction();
					cidade = wc.getC();

					if (cidade != null) {
						textCidade.setText(cidade.getNomeCidade());
					} else {
						PropriedadesShell.mensagemDeErro("Não foi possível carregar a Cidade");
						throw new NullPointerException("Não foi possível carregar a Cidade");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

	}

	public void puxartodaspermissoesColetor() {

		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			PermissaoColetor permissaoColetor = new PermissaoColetor();
			permissaoColetor.setColetor(coletor);
			AutenticadorUsuario at = new AutenticadorUsuario();
			PermissaoColetor ps = at.recuperarPermissoesColetor(permissaoColetor);
			listapermissao = ps.getListaPermissoes();

			TableItem tableItem[] = table.getItems();
			for (int i = 0; i < listapermissao.size(); i++) {
				tableItem[listapermissao.get(i).getId() - 1].setChecked(true);
			}

			Inicial.fechaconexao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

	}

	public void popularTelaColetor() {

		try {
			ColetorDao coletordao = new ColetorDao();
			coletordao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
			coletor = coletordao.consultatotal(coletor);
			textCodigo = new Text(composite, SWT.BORDER | SWT.CENTER);
			textCodigo.setEnabled(false);
			textCodigo.setBounds(259, 10, 47, 21);
			textCodigo.setText(String.valueOf(coletor.getIdUsuario()));
			textNome.setText(coletor.getNomeUsuario());
			if (coletor.getCelularUsuario() != null && coletor.getTelefoneUsuario() != null
					&& coletor.getEnderecoUsuario() != null && coletor.getNumeroenderecoUsuario() != null
					&& coletor.getObservacoesUsuario() != null) {
				textCelular.setText(coletor.getCelularUsuario());
				textTelefone.setText(coletor.getTelefoneUsuario());
				textEndereco.setText(coletor.getEnderecoUsuario());
				textNumeroEndereco.setText(coletor.getNumeroenderecoUsuario());
				textObservacao.setText(coletor.getObservacoesUsuario());
			}
			textCpf.setText(coletor.getCpfUsuario());
			textRG.setText(coletor.getRgUsuario());
			dateChooserCombo.setValue(java.sql.Date.valueOf(coletor.getDatanascimentoUsuario()));
			textCidade.setText(coletor.getCidadeUsuario().getNomeCidade());
			cidade = coletor.getCidadeUsuario();
			textComunidade.setText(coletor.getComunidade().getNomefantaziaComunidade());
			comunidade = coletor.getComunidade();
			textSenha.setText(coletor.getSenhaUsuario());
			textLogin.setText(coletor.getLoginUsuario());
			if (coletor.getIsativo().equals("S")) {
				btnAtivo.setSelection(true);
			} else {
				btnAtivo.setSelection(false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}

}
