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
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import sun.swing.SwingUtilities2;

/**
 * Implements a cell editor that uses a formatted text field to edit Integer
 * values.
 */
public class QuantitaDisponibileEditorSQL extends QuantitaDisponibileEditor {
	private JFormattedTextField ftf;
	private NumberFormat integerFormat;
	private JTable tbl;
	private Object oldValue;


	@Override
	protected boolean dispMagazzino(int qtaOld, int qtaNew) {

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
			if (((giacenza-qtaOld) - qtaNew) < 0) {
				return false;
			}
			return true;

	}




}