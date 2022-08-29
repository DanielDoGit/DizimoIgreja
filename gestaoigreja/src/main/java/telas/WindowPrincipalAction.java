package telas;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import movimentacoes.BalanceteAction;
import movimentacoes.CaixaAction;
import telas.Pesquiar.WindowCategoriaFuncionarioRecuperarAction;
import telas.Pesquiar.WindowCidadeRecuperar;
import telas.Pesquiar.WindowCidadeRecuperarAction;
import telas.Pesquiar.WindowComunidadeRecuperarAction;
import telas.Pesquiar.WindowFornecedorRecuperarAction;
import telas.Pesquiar.WindowFuncionarioRecuperarAction;
import telas.Pesquiar.WindowRecuperarBalancete;
import telas.Pesquiar.WindowRecuperarBalanceteAction;
import telas.Pesquiar.WindowRecuperarColetorAction;
import telas.Pesquiar.WindowRecuperarDizimistaAction;

public class WindowPrincipalAction extends WindowPrincipal {

	private static final String VERSAOSI = "67";
	private Permissoes permissoes[] = { new Permissoes(1, "Acessar paroquia") };
	private List<Boolean> listaPermissoes;

	public WindowPrincipalAction() {
		super.createContents();
		this.trataEventos();
		super.open();

	}

	private void verificarPermissaoparoquia() throws SQLException {
		listaPermissoes = new AutenticadorUsuario().verificarPermissoesGlobal(permissoes);
		if (!listaPermissoes.get(0)) {
			PropriedadesShell.mensagemDeRetorno(
					"Usuário sem permissao para acessar o recurso: " + permissoes[0].getNomepermissao());
			throw new SQLException("Usuário sem permissão para acessar o recurso: " + permissoes[0].getNomepermissao());
		}
	}

	public void trataEventos() {

		shell.setText("Gestão Igreja - Versão: " + VERSAOSI);

		lblTeste.setText("Usuário: " + AutenticadorUsuario.getusuario().getNomeUsuario());

		mntmCadastrar_2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new WindowColetorAction().verificarPermissaoCadastrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		mntmPesquisar_2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowRecuperarColetorAction();
			}
		});

		mntmParoquia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					verificarPermissaoparoquia();
					new WindowIdentificacaoParoquiaAction();

				} catch (SQLException e1) {
					
					new EjetaException(e1);
				}

			}
		});

		mntmCadastrar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// TODO Auto-generated method stub
				try {
					new WindowComunidadeAction().verificarPermissaoCadastrar();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});

		mntmPesquisar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowComunidadeRecuperarAction();
			}
		});

		mntmCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new WindowCidadeAction().verificarPermissaoCasdastrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mntmPesquisar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowCidadeRecuperarAction();
			}
		});

		mntmCadastrar_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowDizimistaAction("Cadastrar".toLowerCase());
			}
		});

		mntmPesquisar_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new WindowRecuperarDizimistaAction();
			}
		});

		mntmCadastrar_7.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new WindowFornecedorAction().verificarPermissaoCadastrar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

		mntmPesquisar_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new WindowFornecedorRecuperarAction();

			}
		});

		mntmCadastrar_3.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowFuncionarioAction();
			}

		});

		mntmPesquisar_12.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowCategoriaFuncionarioRecuperarAction();

			}
		});

		mntmCadastrar_12.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					WindowCategoriaFuncionarioAction wcfa = new WindowCategoriaFuncionarioAction();
					wcfa.verificarPermissoesCadastrar();
					wcfa.gravarCategoriaFuncionario();
					wcfa.populartelaCadastro();
					wcfa.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

			}
		});

		mntmPesquisar_3.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				new WindowFuncionarioRecuperarAction();

			};
		});
		mntmCadastrar_6.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				BalanceteAction bc = new BalanceteAction();
				bc.verificarPermissaoCadastrar();
			}
		});

		mntmPesquisar_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowRecuperarBalanceteAction();
			}
		});

		mntmCaixa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					new CaixaAction().verificarPermissaoCaixa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
}
