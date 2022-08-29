package telas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.CategoriaFuncionario;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CategoriaFuncionarioDao;

public class WindowCategoriaFuncionarioAction extends WindowCategoriaFuncionario {

	private Permissoes permissoes[] = {
			new Permissoes(18, "Cadastrar Categoria Funcionario"),
			new Permissoes(20, "Editar Categoria Funcionario"), 
			new Permissoes(21, "Excluir Categoria Funcionario"),
			new Permissoes(19, "Pesquisar Categoria Funcionario") };

	private List<Boolean> listachecagem;
	private CategoriaFuncionario categoriaFuncionario;

	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}

	public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
		this.categoriaFuncionario = categoriaFuncionario;
	}

	public WindowCategoriaFuncionarioAction() {
		listachecagem = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
		super.createContents();

	}
	
	public void verificarPermissoesCadastrar() throws SQLException {
		if (!listachecagem.get(0)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[0]);
			throw new SQLException("Usuário sem permissao para acessar o recurso:" + permissoes[0]);
		}
	}
	
	public void verificarPermissoesEditar() throws SQLException {
		if (!listachecagem.get(1)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[1]);
			throw new SQLException("Usuário sem permissao para acessar o recurso:" + permissoes[1]);
		}
	}
	
	public void verificarPermissoesExcluir() throws SQLException {
		if (!listachecagem.get(2)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[2]);
			throw new SQLException("Usuário sem permissao para acessar o recurso:" + permissoes[2]);
		}
	}
	
	public void verificarPermissoesPesquisar() throws SQLException {
		if (!listachecagem.get(3)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[3]);
			throw new SQLException("Usuário sem permissao para acessar o recurso:" + permissoes[3]);
		}
	}

	public void populartelaeditar() {
		try {
			text.setText(Integer.toString(categoriaFuncionario.getIdCategoriaFuncionario()));
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
						PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
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
