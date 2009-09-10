package rf.pegaso.gui.configurazioni;

import org.apache.log4j.Logger;

import it.infolabs.hibernate.ArticoliHome;
import it.infolabs.hibernate.BusinessObjectHome;

import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;

import rf.utility.db.DBManager;
import rf.utility.db.UtilityDBManager;

public class JPanelRestoreDB extends JPanel {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(JPanelRestoreDB.class);  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JButton btnBackup = null;
	private JButton btnRestore = null;

	/**
	 * This is the default constructor
	 */
	public JPanelRestoreDB() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		this.setLayout(flowLayout);
		this.setSize(210, 48);
		this.add(getBtnBackup(), null);
		this.add(getBtnRestore(), null);
	}

	/**
	 * This method initializes btnBackup	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBackup() {
		if (btnBackup == null) {
			btnBackup = new JButton();
			btnBackup.setText("Backup");
			btnBackup.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					backup(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnBackup;
	}

	protected void backup() {
		if (logger.isDebugEnabled()) {
			logger.debug("backup() - start");
		}

		try {
			UtilityDBManager.getSingleInstance().backupDataBase();
		} catch (FileNotFoundException e) {
			logger.error("backup()", e);
		} catch (IOException e) {
			logger.error("backup()", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("backup() - end");
		}
	}

	/**
	 * This method initializes btnRestore	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRestore() {
		if (logger.isDebugEnabled()) {
			logger.debug("getBtnRestore() - start");
		}

		if (btnRestore == null) {
			btnRestore = new JButton();
			btnRestore.setText("Restore");
			btnRestore.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (logger.isDebugEnabled()) {
						logger.debug("actionPerformed(java.awt.event.ActionEvent) - start");
					}

					try {
						restore();
					} catch (FileNotFoundException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);
					} catch (IOException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);

					} 

					if (logger.isDebugEnabled()) {
						logger
								.debug("actionPerformed(java.awt.event.ActionEvent) - end");
					}
				}
			});
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getBtnRestore() - end");
		}
		return btnRestore;
	}

	protected void restore() throws FileNotFoundException, IOException {
		
		JFileChooser fileChooser=new JFileChooser(new File(UtilityDBManager.getSingleInstance().getUserDir()));
		int i=fileChooser.showOpenDialog(this);
		if(i==JFileChooser.APPROVE_OPTION){
			UtilityDBManager.getSingleInstance().removeDataBase();
			
			//chiudiamo tutte le connessioni
			try {
				DBManager.getIstanceSingleton().getConnessione().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArticoliHome.getInstance().getSessionFactory().close();
			UtilityDBManager.getSingleInstance().restoreDataBase(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
	}

}  
