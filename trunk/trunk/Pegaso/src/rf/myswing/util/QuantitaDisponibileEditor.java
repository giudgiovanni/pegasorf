package rf.myswing.util;

/*
 * IntegerEditor is used by TableFTFEditDemo.java.
 */

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.exception.IDNonValido;

/**
 * Implements a cell editor that uses a formatted text field to edit Integer
 * values.
 */
public class QuantitaDisponibileEditor extends DefaultCellEditor {
	private JFormattedTextField ftf;
	private NumberFormat integerFormat;
	private JTable tbl;
	private Object oldValue;

	public QuantitaDisponibileEditor() {
		super(new JFormattedTextField());
		ftf = (JFormattedTextField) getComponent();

		// Set up the editor for the integer cells.
		integerFormat = NumberFormat.getIntegerInstance();
		NumberFormatter intFormatter = new NumberFormatter(integerFormat);
		intFormatter.setFormat(integerFormat);

		ftf.setFormatterFactory(new DefaultFormatterFactory(intFormatter));
		ftf.setHorizontalAlignment(JTextField.TRAILING);
		ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

		// Impostiamo il comportamento quando l'oggetto ha il focus
		// e quindi allo scatenarsi dell'evento quale azione
		// effettuare
		ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"check");
		ftf.getActionMap().put("check", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!ftf.isEditValid()) { // Il testo è invalido
					if (userSaysRevert()) { // ritorna
						ftf.postActionEvent(); // informa l'editor
					}
				} else
					try { // Il testo è valido,
						//memorizziamo il vecchio valore
						Long tmp=(Long)ftf.getValue();
						oldValue=tmp;
						ftf.commitEdit();
						Long newDisp=(Long)ftf.getValue();
						if(!dispMagazzino(tmp.intValue(),newDisp.intValue())){
							if(userSaysRevertQuantita(tmp)){
								ftf.postActionEvent();
							}
						}else{
							ftf.commitEdit(); // così lo usiamo.
							ftf.postActionEvent(); // e fermiamo l'azione
							// notifichiamo i cambiamneti nella tabella
							DBManager.getIstanceSingleton().notifyDBStateChange();
						}
						
					} catch (java.text.ParseException exc) {
					}
			}
		});
	}

	protected boolean dispMagazzino(int qtaOld,int qtaNew) {
		Articolo a=new Articolo();
		int row=tbl.getSelectedRow();
		String codbarre=(String)tbl.getValueAt(row, 1);
		int giacenza=0;
		try {
			a.caricaDatiByCodBarre(codbarre);
			giacenza = a.getGiacenza();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (((giacenza+qtaOld) - qtaNew) < 0) {
			return false;
		}
		return true;
	}
 
	// Override to invoke setValue on the formatted text field.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.tbl=table;
		JFormattedTextField ftf = (JFormattedTextField) super
				.getTableCellEditorComponent(table, value, isSelected, row,
						column);
		ftf.setValue(value);
		return ftf;
	}

	// Override to ensure that the value remains an Integer.
	public Object getCellEditorValue() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		Object o = ftf.getValue();
		this.oldValue=o;
		if (o instanceof Integer) {
			return o;
		} else if (o instanceof Number) {
			return new Integer(((Number) o).intValue());
		} else {
			try {
				return integerFormat.parseObject(o.toString());
			} catch (ParseException exc) {
				System.err.println("getCellEditorValue: can't parse o: " + o);
				return null;
			}
		}
	}

	// Override to check whether the edit is valid,
	// setting the value if it is and complaining if
	// it isn't. If it's OK for the editor to go
	// away, we need to invoke the superclass's version
	// of this method so that everything gets cleaned up.
	public boolean stopCellEditing() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		if (ftf.isEditValid()) {
			try {
				ftf.commitEdit();
			} catch (java.text.ParseException exc) {
			}

		} else { // text is invalid
			if (!userSaysRevert()) { // user wants to edit
				return false; // don't let the editor go away
			}
		}
		return super.stopCellEditing();
	}

	/**
	 * Lets the user know that the text they entered is bad. Returns true if the
	 * user elects to revert to the last good value. Otherwise, returns false,
	 * indicating that the user wants to continue editing.
	 */
	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Modifica", "Ripristina" };
		int answer = JOptionPane.showOptionDialog(SwingUtilities
				.getWindowAncestor(ftf), "Bisogna inserire solo numeri interi",
				"Dati non validi", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[1]);

		if (answer == 1) { // ritorna
			ftf.setValue(oldValue);
			return true;
		}
		return false;
	}
	
	protected boolean userSaysRevertQuantita(Long oldValue) {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Modifica", "Ripristina" };
		int answer = JOptionPane.showOptionDialog(SwingUtilities
				.getWindowAncestor(ftf), "Inserire una quantità minore",
				"Quantità elevata", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[1]);

		if (answer == 1) { // ritorna
			ftf.setValue(oldValue);
			return true;
		}
		return false;
	}
}