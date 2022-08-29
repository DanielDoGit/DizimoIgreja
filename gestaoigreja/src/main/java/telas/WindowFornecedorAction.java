package telas;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Fornecedor;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.FornecedorDao;
import telas.Pesquiar.WindowCidadeRecuperarAssociarAction;

public class WindowFornecedorAction extends WindowFornecedor {

	private FornecedorDao fornecedorDao;
	private Fornecedor fornecedor;
	private Cidade cidade;
	private String textoChecagem = "Cadastrar", textochecagemEditar = "Editar";
	private Button botaopropriedades;
	private org.eclipse.swt.widgets.MenuItem mntmCadastrar;
	private List<Boolean> listachecagem;
	private Permissoes permissoes[] = { new Permissoes(9, "Cadastrar Fornecedor"),
			new Permissoes(10, "Editar Fornecedor"), new Permissoes(11, "Pesquisar Fornecedor"),
			new Permissoes(12, "Excluir Fornecedor") };

	public void verificarPermissaoCadastrar() throws Exception {
		if (listachecagem.get(0)) {
			super.createContents();
			puxarIndiceMaximo();
			tratarEventosCadastrar();
			super.open();
		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[0]);
			throw new Exception("usuário sem permissao para acessar o recurso: " + permissoes[0]);
		}
	}

	public void verificarPermissaoEditar(String x) throws Exception {
		if (listachecagem.get(1)) {
			super.createContents();
			fornecedor = new Fornecedor();
			fornecedor.setIndice(Integer.valueOf(x));
			fornecedorDao = new FornecedorDao();
			fornecedorDao.setCon(Inicial.startaPropertiesConnection());
			fornecedor = fornecedorDao.populartelaComunidade(fornecedor);
			cidade = fornecedor.getCidade();
			popularATela();
			tratarEventoEditar();
			Inicial.fechaconexao();
			super.open();

		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[1]);
			throw new Exception("usuário sem permissao para acessar o recurso: " + permissoes[1]);
		}
	}

	public void verificarPermissaoExcluir(String t1) throws Exception {
		if (listachecagem.get(3)) {
			fornecedorDao = new FornecedorDao();
			fornecedorDao.setCon(Inicial.startaPropertiesConnection());

			fornecedor = new Fornecedor();
			fornecedor.setIndice(Integer.valueOf(t1));
			fornecedorDao.excluir(fornecedor);
			Inicial.fechaconexao();

		} else {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[3]);
			throw new Exception("usuário sem permissao para acessar o recurso: " + permissoes[3]);
		}
	}

	public void verificarPermissaoPesquisar() throws Exception {
		if (!listachecagem.get(2)) {
			PropriedadesShell.mensagemRetornoUsuarioPermissao(permissoes[2]);
			throw new Exception("usuário sem permissao para acessar o recurso: " + permissoes[2]);
		}
	}

	private void puxarIndiceMaximo() {
		try {
			fornecedorDao = new FornecedorDao();
			fornecedorDao.setCon(Inicial.startaPropertiesConnection());
			if (fornecedorDao.pesquisarFornecedorIndiceMaximo() != null) {
				fornecedor = fornecedorDao.pesquisarFornecedorIndiceMaximo();
				textCodigo.setText(Integer.toString(fornecedor.getIndice()));
			}
			Inicial.fechaconexao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}

	private void tratarEventosCadastrar() {
		pesquisarCidade();

		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				limpartela();
			}
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
						&& !textNomeFantasia.getText().isEmpty()) {

					try {
						fornecedorDao = new FornecedorDao();
						fornecedorDao.setCon(Inicial.startaPropertiesConnection());
						fornecedor.setNomeFantasia(textNomeFantasia.getText());
						fornecedor.setRazaoSocial(textRazaoSocial.getText());
						fornecedor.setCnpj(formattedText.getControl().getText());
						fornecedor.setCidade(cidade);
						fornecedor.setEndereco(textEndereco.getText());
						fornecedor.setNumeroendereco(textNumeroLogradouro.getText());
						fornecedor.setObservacoes(textObservacoes.getText());
						fornecedorDao.cadastrar(fornecedor);
						fornecedor = fornecedorDao.pesquisarFornecedorIndiceMaximo();
						textCodigo.setText("");
						textCodigo.setText(Integer.toString(fornecedor.getIndice()));
						Inicial.fechaconexao();
						limpartela();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}

				}

			}
		});
	}

	public WindowFornecedorAction() {
		listachecagem = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
	}

	private void popularATela() {
		textCodigo.setText(Integer.toString(fornecedor.getIndice()));
		textNomeFantasia.setText(fornecedor.getNomeFantasia());
		textRazaoSocial.setText(fornecedor.getRazaoSocial());
		formattedText.getControl().setText(fornecedor.getCnpj());
		textCidade.setText(fornecedor.getCidade().getNomeCidade());
		textEndereco.setText(fornecedor.getEndereco());
		textNumeroLogradouro.setText(fornecedor.getNumeroendereco());
		if (fornecedor.getObservacoes() == null) {
			textObservacoes.setText("");
		} else {
			textObservacoes.setText(fornecedor.getObservacoes());
		}

	}

	private void limpartela() {
		textObservacoes.setText("");
		textNumeroLogradouro.setText("");
		textEndereco.setText("");
		textCidade.setText("");
		textRazaoSocial.setText("");
		formattedText.getControl().setText("");
		textNomeFantasia.setText("");
	}

	private void pesquisarCidade() {
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				WindowCidadeRecuperarAssociarAction windowCidadeRecuperarParoquiaAction = new WindowCidadeRecuperarAssociarAction();
				if (windowCidadeRecuperarParoquiaAction.getC() != null) {
					textCidade.setText(windowCidadeRecuperarParoquiaAction.getC().getNomeCidade());
					cidade = windowCidadeRecuperarParoquiaAction.getC();
				} else {
					PropriedadesShell.mensagemDeErro("Não foi possivel realizar a busca de cidade. Tente Novamente");
					new EjetaException(new Exception());
				}

			};
		});
	}

	private void tratarEventoEditar() {

		pesquisarCidade();

		btnLimpar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				limpartela();
			}
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			
					if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
							&& !textNomeFantasia.getText().isEmpty()) {

						try {
							fornecedorDao = new FornecedorDao();
							fornecedorDao.setCon(Inicial.startaPropertiesConnection());
							fornecedor.setNomeFantasia(textNomeFantasia.getText());
							fornecedor.setRazaoSocial(textRazaoSocial.getText());
							fornecedor.setCnpj(formattedText.getControl().getText());
							fornecedor.setCidade(cidade);
							fornecedor.setEndereco(textEndereco.getText());
							fornecedor.setNumeroendereco(textNumeroLogradouro.getText());
							fornecedor.setObservacoes(textObservacoes.getText());
							fornecedorDao.editar(fornecedor);
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

	}

}
