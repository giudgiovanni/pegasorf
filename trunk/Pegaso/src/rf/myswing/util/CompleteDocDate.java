package rf.myswing.util;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CompleteDocDate extends PlainDocument {

	public CompleteDocDate() {
		
	}

	private JTextField tf;

	public CompleteDocDate(JTextField tf) {
		this.tf = tf;
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if(str.charAt(0)<'0' || str.charAt(0)>'9'){
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		String newText = new String(getText(0, getLength()));
		if (newText.length() > 0) {
			newText+=str;
			remove(0, getLength());
			if(newText.length()==2 || newText.length()==5)
			{
				newText+="/";
			}
			super.insertString(0, newText.toString(), a);
			
		} else
			super.insertString(offs, str, a);
	}

}
