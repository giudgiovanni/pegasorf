/**
 * 
 */
package rf.pegaso.gui.viste;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.ViewDocCaricoModel;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 * 
 */
public class ViewDocCarico extends JDialog {

	private static final long serialVersionUID = 1L;

	private DBManager dbm;

	private int idArticolo;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblDocCarico = null;

	/**
	 * @param owner
	 */
	public ViewDocCarico(JDialog owner, DBManager dbm, int idArticolo) {
		super(owner);
		this.dbm = dbm;
		this.idArticolo = idArticolo;
		initialize();
	}

	public ViewDocCarico(JFrame owner, DBManager dbm, int idArticolo) {
		super(owner);
		this.dbm = dbm;
		this.idArticolo = idArticolo;
		initialize();
	}
	
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			try {
				jScrollPane = new JScrollPane();
				jScrollPane.setViewportView(getTblDocCarico()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblDocCarico
	 * 
	 * @return javax.swing.JTable
	 */
	private JXTable getTblDocCarico() {
		if (tblDocCarico == null) {
			try {
				tblDocCarico = new JXTable();
				ViewDocCaricoModel modello = new ViewDocCaricoModel(dbm,
						idArticolo);
				dbm.addDBStateChange(modello);
				tblDocCarico
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblDocCarico.setModel(modello);
				tblDocCarico.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblDocCarico.packAll();
				tblDocCarico.getTableHeader().setReorderingAllowed(false);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return tblDocCarico;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(591, 218);
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
