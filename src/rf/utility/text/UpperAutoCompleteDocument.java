package rf.utility.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import org.jdesktop.swingx.autocomplete.AbstractAutoCompleteAdaptor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;

/**
 * @author hunterbit
 */
public class UpperAutoCompleteDocument extends AutoCompleteDocument {

    @Override
    public void insertString(int arg0, String arg1, AttributeSet arg2)
	    throws BadLocationException {

	super.insertString(arg0, arg1.toUpperCase(), arg2);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public UpperAutoCompleteDocument(AbstractAutoCompleteAdaptor adaptor,
	    boolean flags) {
	super(adaptor, flags);
    }

}
