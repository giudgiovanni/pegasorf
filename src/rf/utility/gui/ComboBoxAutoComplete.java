package rf.utility.gui;

/*
 Handling the initial selection

 It is a quiet annoying that the initially selected item is not shown in the combo box. This 
 can be easily changed in the constructor of the auto completing document.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class ComboBoxAutoComplete extends PlainDocument {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		// the combo box (add/modify items if you like to)
		JComboBox comboBox = new JComboBox(new Object[] { "Ester", "Jordi",
				"Jordina", "Jorge", "Sergi" });
		// has to be editable
		comboBox.setEditable(true);
		// change the editor's document
		new ComboBoxAutoComplete(comboBox);

		// create and show a window containing the combo box
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().add(comboBox);
		frame.pack();
		frame.setVisible(true);
	}

	JComboBox comboBox;
	JTextComponent editor;

	ComboBoxModel model;

	// flag to indicate if setSelectedItem has been called
	// subsequent calls to remove/insertString should be ignored
	boolean selecting = false;
	private boolean matching;

	public ComboBoxAutoComplete(final JComboBox comboBox) {
		new ComboBoxAutoComplete(comboBox,true);
	}
	
	public ComboBoxAutoComplete(final JComboBox comboBox,boolean matching) {
		this.matching=matching;
		this.comboBox = comboBox;
		model = comboBox.getModel();
		editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
		editor.setDocument(this);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!selecting)
					highlightCompletedText(0);
			}
		});
		editor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (comboBox.isDisplayable())
					comboBox.setPopupVisible(true);
			}
		});
		// Handle initially selected object
		Object selected = comboBox.getSelectedItem();
		if (selected != null)
			setText(selected.toString());
		highlightCompletedText(0);
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		// return immediately when selecting an item
		str=str.toUpperCase();
		if (selecting)
			return;
		// insert the string into the document
		super.insertString(offs, str, a);
		// lookup and select a matching item
		Object item = lookupItem(getText(0, getLength()));
		if(matching){
			if (item != null) {
				setSelectedItem(item);
			} else {
				// keep old item selected if there is no match
				item = comboBox.getSelectedItem();
				// imitate no insert (later on offs will be incremented by
				// str.length(): selection won't move forward)
				offs = offs - str.length();
				// provide feedback to the user that his input has been received but
				// can not be accepted
				comboBox.getToolkit().beep(); // when available use:
				// UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
				setText(item.toString());
				// select the completed part
				highlightCompletedText(offs + str.length());
			}
		}else{
			 boolean listContainsSelectedItem = true;
		        if (item == null) {
		            // no item matches => use the current input as selected item
		            item=getText(0, getLength());
		            listContainsSelectedItem=false;
		        }
		        setSelectedItem(item);
		        setText(item.toString());
		        // select the completed part
		        if (listContainsSelectedItem) highlightCompletedText(offs+str.length());
		}
		
		
		
		
		
        
       
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		// return immediately when selecting an item
		if (selecting)
			return;
		super.remove(offs, len);
	}

	private void highlightCompletedText(int start) {
		editor.setCaretPosition(getLength());
		editor.moveCaretPosition(start);
	}

	private Object lookupItem(String pattern) {
		Object selectedItem = model.getSelectedItem();
		// only search for a different item if the currently selected does not
		// match
		if (selectedItem != null
				&& startsWithIgnoreCase(selectedItem.toString(), pattern)) {
			return selectedItem;
		} else {
			// iterate over all items
			for (int i = 0, n = model.getSize(); i < n; i++) {
				Object currentItem = model.getElementAt(i);
				// current item starts with the pattern?
				if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
					return currentItem;
				}
			}
		}
		// no item starts with the pattern => return null
		return null;
	}

	private void setSelectedItem(Object item) {
		selecting = true;
		model.setSelectedItem(item);
		selecting = false;
	}

	private void setText(String text) {
		try {
			// remove all text and insert the completed string
			super.remove(0, getLength());
			super.insertString(0, text, null);
		} catch (BadLocationException e) {
			throw new RuntimeException(e.toString());
		}
	}

	// checks if str1 starts with str2 - ignores case
	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase().startsWith(str2.toUpperCase());
	}
}
