package rf.myswing.util;

/*
 * IntegerEditor is used by TableFTFEditDemo.java.
 */

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.ControlloDati;

/**
 * Implements a cell editor that uses a formatted text field to edit Integer
 * values.
 */
public class QuantitaEditorSql extends DefaultCellEditor {
	JFormattedTextField ftf;

	DecimalFormat doubleFormat;

	private boolean DEBUG = false;

	private Object oldValue;

	private JTable tbl;

	public QuantitaEditorSql() {
		super(new JFormattedTextField());
		ftf = (JFormattedTextField) getComponent();

		// Imposta editor per i valori double
		doubleFormat = new DecimalFormat();
		doubleFormat.setMaximumFractionDigits(2);
		doubleFormat.setMinimumFractionDigits(2);
		NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
		doubleFormatter.setFormat(doubleFormat);
		ftf.setFormatterFactory(new DefaultFormatterFactory(doubleFormatter));
		ftf.setHorizontalAlignment(JTextField.CENTER);
		ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

		ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"check");
		ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
		"check");
		ftf.getActionMap().put("check", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				controllo();
			}
		});
	}

	protected void controllo() {
		if (!ftf.isEditValid()) { // il testo è valido
			if (userSaysRevert()) { // ritorna al precedente valore
				ftf.postActionEvent(); // informa l'editor
			}
		} else
			try { // Il testo è valido,
				Number tmp = (Number) ftf.getValue();
				oldValue = tmp;
				ftf.commitEdit();
				Number newDisp = (Number) ftf.getValue();
				if (!dispMagazzino(tmp.doubleValue(), newDisp
						.doubleValue())) {
					if (userSaysRevertQuantita(tmp)) {
						ftf.postActionEvent();
					}
				} else {
					ftf.commitEdit(); // così lo usiamo.
					ftf.postActionEvent(); // e fermiamo l'azione
				}
			} catch (java.text.ParseException exc) {
			}

	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.tbl = table;
		JFormattedTextField ftf = (JFormattedTextField) super
				.getTableCellEditorComponent(table, value, isSelected, row,
						column);
		NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
		doubleFormatter.setFormat(doubleFormat);
		ftf.setFormatterFactory(new DefaultFormatterFactory(doubleFormatter));
		Double d = null;
		if (value instanceof String) {
			String tmp = ControlloDati.eliminaPunti((String) value);
			tmp = tmp.replace(',', '.');
			d = new Double(tmp);
		} else if (value instanceof Double) {
			d = (Double) value;
		} else if (value instanceof Number) {
			d = new Double(((Number) value).doubleValue());
		}
		ftf.setValue(d);
		return ftf;
	}

	public Object getCellEditorValue() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		Object o = ftf.getValue();
		this.oldValue = o;
		if (o instanceof Double) {
			return o;
		} else if (o instanceof Number) {
			return new Double(((Number) o).doubleValue());
		} else {
			try {
				return doubleFormat.parseObject(o.toString());
			} catch (ParseException exc) {
				System.err.println("getCellEditorValue: can't parse o: " + o);
				return null;
			}
		}
	}

	public boolean stopCellEditing() {
		JFormattedTextField ftf = (JFormattedTextField) getComponent();
		if (ftf.isEditValid()) {
			try {
				ftf.commitEdit();
			} catch (java.text.ParseException exc) {
			}

		} else { // testo non valido
			if (!userSaysRevert()) { // editare o no il valore
				return false;
			}
		}
		return super.stopCellEditing();
	}

	protected boolean userSaysRevert() {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Modifica Valore", "Valore Precedente" };
		int answer = JOptionPane.showOptionDialog(SwingUtilities
				.getWindowAncestor(ftf),
				"Il valore deve essere in formato CCC,CC.\n"
						+ "Puoi scegliere se rimodificare il valore\n "
						+ "o ritornare al valore precedente.",
				"Inserimento Testo Invalido", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[1]);

		if (answer == 1) { // Ritorna
			ftf.setValue(oldValue);
			return true;
		}
		return false;
	}

	protected boolean dispMagazzino(double qtaOld, double qtaNew) {
		Articolo a = new Articolo();
		int row = tbl.getSelectedRow();
		String codbarre = (String) tbl.getValueAt(row, 1);
		double giacenza = 0;
		try {
			a.caricaDatiByCodBarre(codbarre);
			giacenza = a.getGiacenza();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (((giacenza + qtaOld) - qtaNew) < 0) {
			return false;
		}
		return true;
	}

	protected boolean userSaysRevertQuantita(Number oldValue) {
		Toolkit.getDefaultToolkit().beep();
		ftf.selectAll();
		Object[] options = { "Modifica", "Ripristina" };
		int answer = JOptionPane.showOptionDialog(SwingUtilities
				.getWindowAncestor(ftf), "Inserire una quantità minore",
				"Quantità selezionata non disponibile in magazzino", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[1]);

		if (answer == 1) { // ritorna
			ftf.setValue(oldValue);
			return true;
		}
		return false;
	}
}