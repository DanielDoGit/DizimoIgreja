package telas;

import java.sql.SQLException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import telas.Cadastrar.WindowCidadeCadastrar;
import telas.Pesquiar.WindowCategoriaFuncionarioRecuperarAction;
import telas.Pesquiar.WindowCidadeRecuperar;
import telas.Pesquiar.WindowComunidadeRecuperarAction;
import telas.Pesquiar.WindowFornecedorRecuperarAction;
import telas.Pesquiar.WindowFuncionarioRecuperarAction;
import telas.Pesquiar.WindowRecuperarColetorAction;
import telas.Pesquiar.WindowRecuperarDizimistaAction;

public class WindowPrincipalAction extends WindowPrincipal {

	public WindowPrincipalAction() {

		super.createContents();
		this.trataEventos();
		super.open();

	}

	public void trataEventos() {

		lblTeste.setText("Usuário: " + AutenticadorUsuario.getusuario().getNomeUsuario());

		mntmCadastrar_2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new WindowColetorAction(mntmCadastrar_2.getText());
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
					AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(1, "Acessar paroquia");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {

						new WindowIdentificacaoParoquiaAction();

					} else {
						PropriedadesShell.mensagemDeRetorno("Usuário não possui permissao para acessar esse recurso: "
								+ permissoes.getNomepermissao());
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					PropriedadesShell
							.mensagemDeErro("Não foi possivel realizar a consulta. Verfique o log e tente novamente");
					new EjetaException(e1);
				}

			}
		});

		mntmCadastrar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowComunidadeAction(mntmCadastrar_1);
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
				new WindowCidadeCadastrar().open();
			}
		});

		mntmPesquisar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowCidadeRecuperar().open();
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
				new WindowFornecedorAction(mntmCadastrar_7);

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
				new WindowFuncionarioAction("Cadastrar");
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
				WindowCategoriaFuncionarioAction wcfa = new WindowCategoriaFuncionarioAction();
				wcfa.gravarCategoriaFuncionario();
				wcfa.populartelaCadastro();
				wcfa.open();
				

			}
		});
		
		mntmPesquisar_3.addSelectionListener(new SelectionAdapter() {
		
			public void widgetSelected(SelectionEvent e) {
				
				new WindowFuncionarioRecuperarAction();
				
			};
		} );

	}
}
