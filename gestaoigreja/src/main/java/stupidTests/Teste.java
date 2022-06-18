package stupidTests;

import java.awt.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Teste {

	public static void main(String[] args) {
		
		StringBuilder st = new StringBuilder();
		st.append("update cidade set ");
		st.append("paroNomefantasia=?,");
		st.append("paroRazaoSocial=?," );
		st.append("paroCnpj=?,");
		st.append("paroTelef=?,");
		st.append("paroCel=?,");
		st.append("paroContatos=?,");
		st.append("paroEndereco=?,");
		st.append("paroNumEndereco=?,");
		st.append("paroCep=?,");
		st.append("paroBairro=?,");
		st.append("where paroId=?");
		
		System.out.println(st.toString());
		
		//Como fazer uma caixa de dialogo 
//		MessageBox dialog =
//			    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK |  SWT.CANCEL);
//			dialog.setText("My info");
//			dialog.setMessage("teste");
//			dialog.open();
			
		
		
		
			
			
	}

}
