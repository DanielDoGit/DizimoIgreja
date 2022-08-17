package telas;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.CategoriaFuncionario;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CategoriaFuncionarioDao;

public class WindowCategoriaFuncionarioAction extends WindowCategoriaFuncionario {

	private CategoriaFuncionario categoriaFuncionario;

	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}

	public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
		this.categoriaFuncionario = categoriaFuncionario;
	}

	public WindowCategoriaFuncionarioAction() {
		super.createContents();

	}

	public void populartelaeditar() {
		try {
			text.setText(Integer.toHexString(categoriaFuncionario.getIdCategoriaFuncionario()));
			text_1.setText(categoriaFuncionario.getNomeCategoraiFuncionario());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populartelaCadastro() {

		try {
			Connection con = Inicial.startaPropertiesConnection();
			CategoriaFuncionarioDao cateDao = new CategoriaFuncionarioDao();
			cateDao.setCon(con);
			categoriaFuncionario = cateDao.retornaCategoriaFuncionarioMaximo();
			if (categoriaFuncionario != null && categoriaFuncionario.getIdCategoriaFuncionario() != null) {
				text.setText(String.valueOf(categoriaFuncionario.getIdCategoriaFuncionario()));
			} else {
				text.setText("1");
			}
			Inicial.fechaconexao();
		} catch (SQLException e) {
			new EjetaException(e);
		}
	}

	public void gravarCategoriaFuncionario() {
		
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_1.setText("");
			}

		});

		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection con = Inicial.startaPropertiesConnection();
					AutenticadorUsuario.setCon(con);
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(18, "Cadastrar Categoria Funcionario");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {

						if (text_1.getText() != null && !text_1.getText().isEmpty()) {
							CategoriaFuncionarioDao cateDao = new CategoriaFuncionarioDao();
							cateDao.setCon(con);
							categoriaFuncionario.setIdCategoriaFuncionario(Integer.valueOf(text.getText()));
							categoriaFuncionario.setNomeCategoraiFuncionario(text_1.getText());
							cateDao.inserircategoria(categoriaFuncionario);
							text.setText("");
							text.setText(Integer
									.toString(cateDao.retornaCategoriaFuncionarioMaximo().getIdCategoriaFuncionario()));
							text_1.setText("");
						} else {
							PropriedadesShell
									.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
						}

					} else {
						PropriedadesShell.mensagemDeRetorno(
								"Usuário sem permissão para acessar o recurso: " + permissoes.getNomepermissao());
					}
					Inicial.fechaconexao();

				} catch (Exception e1) {
					new EjetaException(e1);
				}
			}

		});

	}

	public void editarCategoriaFuncionario() {

		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text_1.setText("");
			}

		});

		btnGravar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection con = Inicial.startaPropertiesConnection();
					if (text_1.getText() != null && !text_1.getText().isEmpty()) {

						CategoriaFuncionarioDao cateDao = new CategoriaFuncionarioDao();
						cateDao.setCon(con);
						categoriaFuncionario.setNomeCategoraiFuncionario(text_1.getText());
						categoriaFuncionario.setIdCategoriaFuncionario(Integer.valueOf(text.getText()));
						cateDao.editarcategoria(categoriaFuncionario);
						shell.dispose();

					} else {
						PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
					}

					Inicial.fechaconexao();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
					new EjetaException(e1);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					new EjetaException(e2);
					PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
				}
			}

		});

	}

}
