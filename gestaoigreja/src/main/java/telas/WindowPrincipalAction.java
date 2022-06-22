package telas;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import telas.Cadastrar.WindowCidadeCadastrar;
import telas.Pesquiar.WindowCidadeRecuperar;
import telas.Pesquiar.WindowComunidadeRecuperarAction;
import telas.Pesquiar.WindowFornecedorRecuperarAction;

public class WindowPrincipalAction extends WindowPrincipal{
	
	public WindowPrincipalAction() {
		
		super.createContents();
		this.trataEventos();
		super.open();
		
	}

	public void trataEventos() {
		
		mntmParoquia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new WindowIdentificacaoParoquiaAction();
			}
		});
		
		mntmCadastrar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				new WindowComunidadeAction(mntmCadastrar_1);
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
				new WindowDizimista().open();
			}
		});
		
		//CadastroFornecedor
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

	}
}
