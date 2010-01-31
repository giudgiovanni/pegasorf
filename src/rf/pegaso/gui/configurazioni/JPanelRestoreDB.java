package rf.pegaso.gui.configurazioni;

import org.apache.log4j.Logger;
 
import it.infolabs.hibernate.ArticoloHome;
import it.infolabs.hibernate.BusinessObjectHome;

import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
	private boolean restoreEnable=true;
	private boolean backupEnable=true;

	/**
	 * This is the default constructor
	 */
	public JPanelRestoreDB() {
		super();
		new JPanelRestoreDB(true,true);
		
	}
	
	public JPanelRestoreDB(boolean restoreEnable, boolean backupEnable) {
		super();
		initialize();
		setRestoreEnable(restoreEnable);
		setBackupEnable(backupEnable);
		
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
	
	private void messaggioAVideo(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
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
					if (logger.isDebugEnabled()) {
						logger
								.debug("actionPerformed(java.awt.event.ActionEvent) - start");
					}

					try {
						backup();
						messaggioAVideo("Salvataggio db effettuato con successo.", "INFO");
					} catch (FileNotFoundException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);
						messaggioAVideo("Si  verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
					} catch (IOException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);
						messaggioAVideo("Si  verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
					} // TODO Auto-generated Event stub actionPerformed()

					if (logger.isDebugEnabled()) {
						logger
								.debug("actionPerformed(java.awt.event.ActionEvent) - end");
					}
				}
			});
		}
		return btnBackup;
	}

	protected void backup() throws FileNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("backup() - start");
		}

		JFileChooser fileChooser=new JFileChooser(new File(UtilityDBManager.getSingleInstance().getUserDir()));
//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int i=fileChooser.showSaveDialog(this);
		if(i==JFileChooser.APPROVE_OPTION){
			try {
				UtilityDBManager.getSingleInstance().backupDataBase(fileChooser.getSelectedFile().getAbsolutePath());
			} catch (FileNotFoundException e) {
				logger.error("backup()", e);
				messaggioAVideo("Si verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
			} catch (IOException e) {
				logger.error("backup()", e);
				messaggioAVideo("Si  verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
			}
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

					try {
						restore();
						messaggioAVideo("Ripristino db effettuato con successo. " +
								"Riavviare il programma per utilizzare le nuove modifiche.", "INFO");
					} catch (FileNotFoundException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);
						messaggioAVideo("Si  verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
					} catch (IOException e1) {
						logger.error(
								"actionPerformed(java.awt.event.ActionEvent)",
								e1);
						messaggioAVideo("Si verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
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
				e.printStackTrace();
				messaggioAVideo("Si  verificato un errore inospettato. Chiudere e riprovare.", "ERRORE");
			}

			ArticoloHome.getInstance().getSessionFactory().close();
			UtilityDBManager.getSingleInstance().restoreDataBase(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
	}

	public boolean isRestoreEnable() {
		return restoreEnable;
	}

	public void setRestoreEnable(boolean restoreEnable) {
		this.restoreEnable = restoreEnable;
		this.btnRestore.setEnabled(restoreEnable);
	}

	public boolean isBackupEnable() {
		return backupEnable;
	}

	public void setBackupEnable(boolean backupEnable) {
		this.backupEnable = backupEnable;
		this.btnBackup.setEnabled(backupEnable);
	}

}  
