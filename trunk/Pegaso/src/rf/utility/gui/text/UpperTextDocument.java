/**
 * 
 */

package rf.utility.gui.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author hunterbit
 */
public class UpperTextDocument extends PlainDocument {

	/**
	 * Questo metodo serve per inserire sempre il testo maiscolo nei JTextFiled
	 * e altri campi di testo.
	 */
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		str = str.toUpperCase();
		super.insertString(offs, str, a);
	}

}
