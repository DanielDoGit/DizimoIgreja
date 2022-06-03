package telas;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PropriedadesShell {
	
	public static Point centralizarShell(Shell pai, Display display) {
		
		org.eclipse.swt.widgets.Monitor primary = display.getPrimaryMonitor();
		org.eclipse.swt.graphics.Rectangle bounds = primary.getBounds();
	    org.eclipse.swt.graphics.Rectangle rect = pai.getBounds();
	   
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	     
	    Point x1 = new Point(x, y);
	    
	    
	    return x1;
		
	}

}
