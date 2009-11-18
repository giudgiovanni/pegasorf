package rf.pegaso.gui.gestione;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.toedter.calendar.JTextFieldDateEditor;

public class MyJTextFiledDateEditor extends JTextFieldDateEditor implements FocusListener{

	public MyJTextFiledDateEditor(){
		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
		setCaretPosition(0);
	}
	
	
	
	
}
