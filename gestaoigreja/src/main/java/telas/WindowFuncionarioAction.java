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

import beans.CategoriaFuncionario;
import beans.Cidade;
import beans.Funcionario;
import beans.PermissaoFuncionario;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.FuncionarioDao;
import telas.Pesquiar.WindowCategoriaFuncionarioRecuperarAssociarAction;
import telas.Pesquiar.WindowCidadeRecuperarAssociarAction;

public class WindowFuncionarioAction extends WindowFuncionario {

	private String verificacaoCadastrar = "Cadastrar", verificacaoEditar = "Editar";
	private Cidade cidade;
	private List<Permissoes> lista, listapermissao;
	private Funcionario funcionario;
	private CategoriaFuncionario catFunc;

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public WindowFuncionarioAction(String a) {

		if (a.equals(verificacaoCadastrar)) {
			super.createContents();
			try {
				acessarCategoriaFuncionario();
				acessarCidade();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				new EjetaException(e);
			}
			buscarultimoindiceFuncionario();
			preenchimentotabelapermissoes();
			this.tratarEventosCadastrar();
			super.open();

		} else if (a.equals(verificacaoEditar)) {
			super.createContents();
			try {
				acessarCategoriaFuncionario();
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
		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				funcionario = new Funcionario();
				int aux = 0;

				try {
					FuncionarioDao funcionarioDao = new FuncionarioDao();
					funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
					AutenticadorUsuario.setCon(funcionarioDao.getConnection());

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
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {
						if (!textNome.getText().isEmpty() && !textCpf.getText().isEmpty() && !textRG.getText().isEmpty()
								&& !textCategoriaFuncionario.getText().isEmpty() && !textCidade.getText().isEmpty()
								&& !textLogin.getText().isEmpty() && !textSenha.getText().isEmpty()) {

							Date data = dateChooserCombo.getValue();

							if (data != null) {
								LocalDate hoje = LocalDate.now();
								LocalDate nascimento = Instant.ofEpochMilli(data.getTime())
										.atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toLocalDate();
								int i = Period.between(nascimento, hoje).getYears();
								if (i >= 18 && i < 100) {
									funcionario.setIdUsuario(Integer.valueOf(textCodigo.getText()));
									funcionario.setNomeUsuario(textNome.getText());
									funcionario.setCidadeUsuario(cidade);
									funcionario.setCpfUsuario(textCpf.getText());
									funcionario.setCategoriaFuncionario(catFunc);
									funcionario.setRgUsuario(textRG.getText());
									funcionario.setCelularUsuario(textCelular.getText());
									funcionario.setTelefoneUsuario(textTelefone.getText());
									funcionario.setDatanascimentoUsuario(nascimento);
									funcionario.setEnderecoUsuario(textEndereco.getText());
									funcionario.setNumeroenderecoUsuario(textNumeroEndereco.getText());
									funcionario.setObservacoesUsuario(textObservacao.getText());
									funcionario.setDataCadastro(hoje);
									funcionario.setLoginUsuario(textLogin.getText());
									funcionario.setSenhaUsuario(textSenha.getText());
									if (btnAtivo.getSelection()) {
										funcionario.setIsativo("S");
									} else {
										funcionario.setIsativo("N");
									}

									if (aux > 0) {
										funcionarioDao.cadastrarFuncionario(funcionario);
										new AutenticadorUsuario().inputpermissaoFuncionario(
												new PermissaoFuncionario(funcionario, listaauxiliar));
										limparAtela();
									} else {
										PropriedadesShell.mensagemDeRetorno(
												"Você precisa conceder ao menos 1 permissão para este funcionario");
									}

								} else {
									PropriedadesShell.mensagemDeRetorno(
											"Não é possível cadastrar esse funcionario. Sua idade é de: " + i
													+ " ano(s)");
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
					PropriedadesShell.mensagemDeErro(
							"Não foi possível inserir o funcionario, verifique o log e tente novamente.");
					new EjetaException(e1);
				}
			}

		});
	}

	public void tratarEventosEditar() {

		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					Connection cone = Inicial.startaPropertiesConnection();
					FuncionarioDao funcionarioDao = new FuncionarioDao();
					funcionarioDao.setConnectionFuncionarioDao(cone);
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
							&& !textCategoriaFuncionario.getText().isEmpty() && !textCidade.getText().isEmpty()
							&& !textLogin.getText().isEmpty() && !textSenha.getText().isEmpty()) {

						Date data = dateChooserCombo.getValue();

						if (data != null) {
							LocalDate hoje = LocalDate.now();
							LocalDate nascimento = Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault())
									.truncatedTo(ChronoUnit.DAYS).toLocalDate();
							int i = Period.between(nascimento, hoje).getYears();
							if (i >= 18 && i < 100) {
								funcionario.setIdUsuario(Integer.valueOf(textCodigo.getText()));
								funcionario.setNomeUsuario(textNome.getText());
								funcionario.setCidadeUsuario(cidade);
								funcionario.setCategoriaFuncionario(catFunc);
								funcionario.setCpfUsuario(textCpf.getText());
								funcionario.setRgUsuario(textRG.getText());
								funcionario.setCelularUsuario(textCelular.getText());
								funcionario.setTelefoneUsuario(textTelefone.getText());
								funcionario.setDatanascimentoUsuario(nascimento);
								funcionario.setEnderecoUsuario(textEndereco.getText());
								funcionario.setNumeroenderecoUsuario(textNumeroEndereco.getText());
								funcionario.setObservacoesUsuario(textObservacao.getText());
								funcionario.setDataCadastro(hoje);
								funcionario.setLoginUsuario(textLogin.getText());
								funcionario.setSenhaUsuario(textSenha.getText());
								if (btnAtivo.getSelection()) {
									funcionario.setIsativo("S");
								} else {
									funcionario.setIsativo("N");
								}

								if (listaauxiliar.size() > 0) {
									autenticadorUsuario.deletePermissoesFuncionario(funcionario);
									autenticadorUsuario.inputpermissaoFuncionario(
											new PermissaoFuncionario(funcionario, listaauxiliar));
									funcionarioDao.editarFuncionario(funcionario);
									shlColetor.dispose();
								} else {
									PropriedadesShell.mensagemDeRetorno(
											"Você precisa conceder ao menos 1 permissão para este funcionario");
								}

							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Não é possível cadastrar esse funcionario. Sua idade é de: " + i + " ano(s)");
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
					PropriedadesShell.mensagemDeErro(
							"Não foi possível editar o funcionario, verifique o log e tente novamente.");
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

	public void buscarultimoindiceFuncionario() {
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		try {
			funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
			textCodigo = new Text(composite, SWT.BORDER | SWT.CENTER);
			textCodigo.setEnabled(false);
			textCodigo.setBounds(259, 10, 47, 21);
			textCodigo.setText(String.valueOf(funcionarioDao.consultarIndiceMaximo().getIdUsuario() + 1));
			Inicial.fechaconexao();
		} catch (SQLException e) {
			new EjetaException(e);
		}

	}

	public void acessarCategoriaFuncionario() throws Exception {

		tltmPesquisarCategoriaFuncionario.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					WindowCategoriaFuncionarioRecuperarAssociarAction wcra = new WindowCategoriaFuncionarioRecuperarAssociarAction();
					catFunc = wcra.getCategoriaFuncionario();
					if (catFunc != null) {
						textCategoriaFuncionario.setText(catFunc.getNomeCategoraiFuncionario());

					} else {
						PropriedadesShell.mensagemDeErro("Não foi possível carregar a Categoria Funcionario");
						throw new NullPointerException("Não foi possível carregar a Categoria Funcionario");
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
		textCategoriaFuncionario.setText("");
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
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		textCodigo.setText("");
		try {
			funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
			textCodigo.setText(String.valueOf(funcionarioDao.consultarIndiceMaximo().getIdUsuario() + 1));
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

	public void puxartodaspermissoesFuncionario() {

		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			PermissaoFuncionario permissaoFuncionario = new PermissaoFuncionario();
			permissaoFuncionario.setFuncionario(funcionario);
			AutenticadorUsuario at = new AutenticadorUsuario();
			PermissaoFuncionario ps = at.recuperarPermissoesFuncionario(permissaoFuncionario);
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

	public void popularTelaFuncionario() {

		try {
			FuncionarioDao funcionariodao = new FuncionarioDao();
			funcionariodao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
			funcionario = funcionariodao.consultatotal(funcionario);
			textCodigo = new Text(composite, SWT.BORDER | SWT.CENTER);
			textCodigo.setEnabled(false);
			textCodigo.setBounds(259, 10, 47, 21);
			textCodigo.setText(String.valueOf(funcionario.getIdUsuario()));
			textNome.setText(funcionario.getNomeUsuario());
			if (funcionario.getCelularUsuario() != null && funcionario.getTelefoneUsuario() != null
					&& funcionario.getEnderecoUsuario() != null && funcionario.getNumeroenderecoUsuario() != null
					&& funcionario.getObservacoesUsuario() != null) {
				textCelular.setText(funcionario.getCelularUsuario());
				textTelefone.setText(funcionario.getTelefoneUsuario());
				textEndereco.setText(funcionario.getEnderecoUsuario());
				textNumeroEndereco.setText(funcionario.getNumeroenderecoUsuario());
				textObservacao.setText(funcionario.getObservacoesUsuario());
			}
			textCpf.setText(funcionario.getCpfUsuario());
			textRG.setText(funcionario.getRgUsuario());
			dateChooserCombo.setValue(java.sql.Date.valueOf(funcionario.getDatanascimentoUsuario()));
			textCidade.setText(funcionario.getCidadeUsuario().getNomeCidade());
			cidade = funcionario.getCidadeUsuario();
			textCategoriaFuncionario.setText(funcionario.getCategoriaFuncionario().getNomeCategoraiFuncionario());
			catFunc = funcionario.getCategoriaFuncionario();
			textSenha.setText(funcionario.getSenhaUsuario());
			textLogin.setText(funcionario.getLoginUsuario());
			if (funcionario.getIsativo().equals("S")) {
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
