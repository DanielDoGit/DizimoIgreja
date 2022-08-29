package telas;


import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Dizimista;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.DizimistaDao;
import telas.Pesquiar.WindowCidadeRecuperarAssociarAction;

public class WindowDizimistaAction extends WindowDizimista {

	private Dizimista dizimista;

	public WindowDizimistaAction(String a) {
		super.createContents();
		manutencoesTela();
		if ("Cadastrar".toLowerCase().equals(a)) {
			tratarPermissoesCadastrar();
		} else if ("editar".toLowerCase().equals(a)) {
			tratarPermissoesEditar();
		}

	}

	public void tratarPermissoesCadastrar() {
		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			AutenticadorUsuario at = new AutenticadorUsuario();
			Permissoes ps = new Permissoes(26, "Cadastrar Dizimista");
			if (AutenticadorUsuario.isColetor() && at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), ps)) {
				tratarEventosCadastrar();
				dizimista = new Dizimista();
				super.open();
			} else if (!AutenticadorUsuario.isColetor()
					&& at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(), ps)) {
				tratarEventosCadastrar();
				dizimista = new Dizimista();
				super.open();
			} else {
				PropriedadesShell
						.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: " + ps.getNomepermissao());
			}
			Inicial.fechaconexao();
		} catch (SQLException ex) {
			PropriedadesShell.mensagemDeErro("" + ex);
			new EjetaException(ex);
		}
	}

	public void tratarPermissoesEditar() {
		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			AutenticadorUsuario at = new AutenticadorUsuario();
			Permissoes ps = new Permissoes(27, "Editar Dizimista");
			if (AutenticadorUsuario.isColetor() && at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), ps)) {
				tratarEventosEditar();
			} else if (!AutenticadorUsuario.isColetor()
					&& at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(), ps)) {
				tratarEventosEditar();
			} else {
				PropriedadesShell
						.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: " + ps.getNomepermissao());
			}
			Inicial.fechaconexao();
		} catch (SQLException ex) {
			PropriedadesShell.mensagemDeErro("" + ex);
			new EjetaException(ex);
		}
	}

	public void puxarIndiceDizimista() {
		try {
			DizimistaDao diziDao = new DizimistaDao();
			diziDao.setConnection(Inicial.startaPropertiesConnection());
			Integer i = diziDao.puxarIndiceMaximo();
			if (i != null) {
				textCodigo.setText("");
				textCodigo.setText(Integer.toString(i + 1));
			} else {
				textCodigo.setText("1");
			}
			Inicial.fechaconexao();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void tratarEventosCadastrar() {

		puxarIndiceDizimista();

		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					WindowCidadeRecuperarAssociarAction wc = new WindowCidadeRecuperarAssociarAction();
					if (wc.getC() != null) {
						dizimista.setCidade(wc.getC());
						textCidade.setText(wc.getC().getNomeCidade());
					} else {
						PropriedadesShell.mensagemDeErro("Não foi possível carregar a cidade");
						throw new NullPointerException("Não foi possível carregar a cidade");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("" + e1.getMessage());
					new EjetaException(e1);
				}

			}

		});

		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {

					if (!textNome.getText().isEmpty() && !textCpf.getText().isEmpty() && !textRg.getText().isEmpty()) {
						Date data = dateChooserCombo.getValue();
						if (data != null && dizimista.getCidade() != null) {
							LocalDate hoje = LocalDate.now();
							LocalDate nascimento = Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault())
									.truncatedTo(ChronoUnit.DAYS).toLocalDate();
							int i = Period.between(nascimento, hoje).getYears();
							if (i >= 18 && i < 100) {
								dizimista.setIdDizimista(Integer.valueOf(textCodigo.getText()));
								dizimista.setNomeDizimista(textNome.getText());
								dizimista.setCpfDizimista(textCpf.getText());
								dizimista.setRgDizimista(textRg.getText());
								dizimista.setCelularDizimista(textCelular.getText());
								dizimista.setTelefoneDizimista(textTelefone.getText());
								dizimista.setDataNascimentoDizimista(nascimento);
								dizimista.setDatacadastroDizimista(hoje);
								dizimista.setEdereco(textEndereco.getText());
								dizimista.setNumerologradouro(textNumeroEndereco.getText());
								dizimista.setObservacoesDizimista(textObservacoes.getText());
								if (botaoIsAtivo.getSelection()) {
									dizimista.setIsativo("S");
								} else {
									dizimista.setIsativo("N");
								}
								dizimista.setContatos(textContatos.getText());

								DizimistaDao diziDao = new DizimistaDao();
								diziDao.setConnection(Inicial.startaPropertiesConnection());
								diziDao.cadastrarDizimista(dizimista);
								Inicial.fechaconexao();
								limparTela();
								puxarIndiceDizimista();

							}

						} else {
							PropriedadesShell
									.mensagemDeRetorno(" Verifique a cidade. //n Informe uma data para continuar");
						}
					} else {
						PropriedadesShell.mensagemDeRetorno(" Verifique se os campos obrigatórios foram preenchidos");
					}

				} catch (Exception ex) {
					PropriedadesShell.mensagemDeErro(
							"Não foi possível cadastrar o Dizimista. Verifique o log e tente novamente.");
					new EjetaException(ex);
				}

			}

		});

	}

	public void tratarEventosEditar() {
		
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					WindowCidadeRecuperarAssociarAction wc = new WindowCidadeRecuperarAssociarAction();
					if (wc.getC() != null) {
						dizimista.setCidade(wc.getC());
						textCidade.setText(wc.getC().getNomeCidade());
					} else {
						PropriedadesShell.mensagemDeErro("Não foi possível carregar a cidade");
						throw new NullPointerException("Não foi possível carregar a cidade");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("" + e1.getMessage());
					new EjetaException(e1);
				}

			}

		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {

					if (!textNome.getText().isEmpty() && !textCpf.getText().isEmpty() && !textRg.getText().isEmpty()) {
						Date data = dateChooserCombo.getValue();
						if (data != null && dizimista.getCidade() != null) {
							LocalDate hoje = LocalDate.now();
							LocalDate nascimento = Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault())
									.truncatedTo(ChronoUnit.DAYS).toLocalDate();
							int i = Period.between(nascimento, hoje).getYears();
							if (i >= 18 && i < 100) {
								dizimista.setIdDizimista(Integer.valueOf(textCodigo.getText()));
								dizimista.setNomeDizimista(textNome.getText());
								dizimista.setCpfDizimista(textCpf.getText());
								dizimista.setRgDizimista(textRg.getText());
								dizimista.setCelularDizimista(textCelular.getText());
								dizimista.setTelefoneDizimista(textTelefone.getText());
								dizimista.setDataNascimentoDizimista(nascimento);
								dizimista.setDatacadastroDizimista(hoje);
								dizimista.setEdereco(textEndereco.getText());
								dizimista.setNumerologradouro(textNumeroEndereco.getText());
								dizimista.setObservacoesDizimista(textObservacoes.getText());
								if (botaoIsAtivo.getSelection()) {
									dizimista.setIsativo("S");
								} else {
									dizimista.setIsativo("N");
								}
								dizimista.setContatos(textContatos.getText());

								DizimistaDao diziDao = new DizimistaDao();
								diziDao.setConnection(Inicial.startaPropertiesConnection());
								diziDao.editarDizimista(dizimista);
								Inicial.fechaconexao();
								shell.dispose();

							}

						} else {
							PropriedadesShell
									.mensagemDeRetorno(" Verifique a cidade. //n Informe uma data para continuar");
						}
					} else {
						PropriedadesShell.mensagemDeRetorno(" Verifique se os campos obrigatórios foram preenchidos");
					}

				} catch (Exception ex) {
					PropriedadesShell.mensagemDeErro(
							"Não foi possível editar o Dizimista. Verifique o log e tente novamente.");
					new EjetaException(ex);
				}

			}
		});
	}

	public void popularATela() {

		try {
			DizimistaDao disDao = new DizimistaDao();
			disDao.setConnection(Inicial.startaPropertiesConnection());
			Dizimista dizi = disDao.consultatotal(dizimista.getIdDizimista());
			textCodigo.setText(Integer.toString(dizi.getIdDizimista()));
			textNome.setText(dizi.getNomeDizimista());
			textCpf.setText(dizi.getCpfDizimista());
			textRg.setText(dizi.getRgDizimista());
			dateChooserCombo.setValue(java.sql.Date.valueOf(dizi.getDataNascimentoDizimista()));
			textCidade.setText(dizi.getCidade().getNomeCidade());
			dizimista = dizi;
			if (dizi.getCelularDizimista() != null && dizi.getTelefoneDizimista() != null && dizi.getContatos() != null
					&& dizi.getEdereco() != null && dizi.getNumerologradouro() != null
					&& dizi.getObservacoesDizimista() != null) {
				textCelular.setText(dizi.getCelularDizimista());
				textContatos.setText(dizi.getContatos());
				textTelefone.setText(dizi.getTelefoneDizimista());
				textEndereco.setText(dizi.getEdereco());
				textNumeroEndereco.setText(dizi.getNumerologradouro());
				textObservacoes.setText(dizi.getObservacoesDizimista());
			}
			if (dizi.getIsativo().equals("N")) {
				botaoIsAtivo.setSelection(false);
			}else {
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

	}

	public void manutencoesTela() {

		textCpf.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

				formattedText = new FormattedText(textCpf);
				formattedText.setFormatter(new MaskFormatter("###.###.###-##"));
				textCpf = formattedText.getControl();

			}

		});

		textCelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				formattedText = new FormattedText(textCelular);
				formattedText.setFormatter(new MaskFormatter("(##) # ####-####"));
				textCelular = formattedText.getControl();

			}
		});

		textTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				formattedText = new FormattedText(textTelefone);
				formattedText.setFormatter(new MaskFormatter("(##) ####-####"));
				textTelefone = formattedText.getControl();

			}
		});
		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				textObservacoes.setText("");
				textNome.setText("");
				textCpf.setText("");
				textRg.setText("");
				textContatos.setText("");
				textCelular.setText("");
				textTelefone.setText("");
				textCidade.setText("");
				textEndereco.setText("");
				textNumeroEndereco.setText("");

			}
		});
	}

	public void limparTela() {
		textObservacoes.setText("");
		textNome.setText("");
		textCpf.setText("");
		textRg.setText("");
		textContatos.setText("");
		textCelular.setText("");
		textTelefone.setText("");
		textCidade.setText("");
		textEndereco.setText("");
		dateChooserCombo.setValue(null);
		textNumeroEndereco.setText("");
	}

	public Dizimista getDizimista() {
		return dizimista;
	}

	public void setDizimista(Dizimista dizimista) {
		this.dizimista = dizimista;
	}

}
