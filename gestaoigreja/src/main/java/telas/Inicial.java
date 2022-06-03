package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import stupidTests.Teste;

public class Inicial{


	public static void main(String[] args) {
		
		
		IdentificacaoUsuarioSWT identifica =  new IdentificacaoUsuarioSWT(new Shell(),SWT.PRIMARY_MODAL);
		identifica.open();
	//	new Inicial(new Shell(),SWT.PRIMARY_MODAL);
		
		 
		
	
	
	}
}
