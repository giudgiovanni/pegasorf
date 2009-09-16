package rf.pegaso.gui.internalframe;

import org.apache.log4j.Logger;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import rf.pegaso.gui.configurazioni.JPanelRestoreDB;
import rf.utility.db.UtilityDBManager;
import rf.utility.gui.UtilGUI;
import java.awt.Dimension;

public class JDialogGestioneDatabase extends JDialog {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(JDialogGestioneDatabase.class);

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanelRestoreDB jPanelRestoreDB = null;

	/**
	 * @param owner
	 */
	public JDialogGestioneDatabase(Frame owner) {
		super(owner,true);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(361, 165);
		this.setTitle("Gestione Database");
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
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
			jContentPane.add(getJPanelRestoreDB(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelRestoreDB	
	 * 	
	 * @return rf.pegaso.gui.configurazioni.JPanelRestoreDB	
	 */
	private JPanelRestoreDB getJPanelRestoreDB() {
		if (logger.isDebugEnabled()) {
			logger.debug("getJPanelRestoreDB() - start");
		}

		if (jPanelRestoreDB == null) {
			try {
				boolean restore=UtilityDBManager.getSingleInstance().isRestoreEnable();
				boolean backup=UtilityDBManager.getSingleInstance().isBackupEnable();
				jPanelRestoreDB = new JPanelRestoreDB(restore,backup);
			} catch (FileNotFoundException e) {
				logger.error("getJPanelRestoreDB()", e);
			} catch (IOException e) {
				logger.error("getJPanelRestoreDB()", e);
			}
			
			
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getJPanelRestoreDB() - end");
		}
		return jPanelRestoreDB;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
