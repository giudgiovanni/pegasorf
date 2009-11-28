package rf.pegaso.gui.configurazioni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class DBJPanelConfigBackup extends JPanel {

    private static final long serialVersionUID = 1L;
    private Properties p=null;  //  @jve:decl-index=0:
	private JPanel pnlNord = null;
	private JPanel pnlCentro = null;
	private JButton btnSalva = null;
	private JLabel lblNomeDB = null;
	private JTextField txtNomeDB = null;
	private JLabel lblPathCommand = null;
	private JTextField txtPAthCommand = null;
	private JLabel lblCmdBackup = null;
	private JTextField txtCmdBackup = null;
	private JLabel lblCmdRestore = null;
	private JTextField txtCmdRestore = null;
	private JLabel lblFolderBackup = null;
	private JTextField txtFolderBackup = null;
	private JLabel lblNumFile = null;
	private JTextField txtNumFile = null;
	private JLabel lblOpen = null;
	private JTextField txtOpen = null;
	private JLabel lblClose = null;
	private JTextField txtClose = null;
	private JLabel lblUpdate = null;
	private JTextField txtUpdate = null;
	private JLabel lblDelete = null;
	private JTextField txtDelete = null;
	private JLabel lblInsert = null;
	private JTextField txtInsert = null;

    /**
     * This is the default constructor
     */
    public DBJPanelConfigBackup() {
	super();
	initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
	this.setSize(434, 253);
	this.setLayout(new BorderLayout());
	this.add(getPnlNord(), BorderLayout.NORTH);  // Generated
	this.add(getPnlCentro(), BorderLayout.CENTER);  // Generated

	// leggiamo il file di proprieta ed impostiamo i campi
	p=new Properties();
	try {
	    // crichiamo i dati
	    p.load(new FileReader("configurazione.properties"));
	    // impostiamo tutti i campi di testo
	    txtNomeDB.setText(p.getProperty("nameDb"));
	    txtPAthCommand.setText(p.getProperty("pathCommand"));
	    txtCmdBackup.setText(p.getProperty("cmdBackup"));
	    txtCmdRestore.setText(p.getProperty("cmdRestore"));
	    txtFolderBackup.setText(p.getProperty("folderBackup"));
	    txtNumFile.setText(p.getProperty("nFileBackup"));
	    txtOpen.setText(p.getProperty("open"));
	    txtClose.setText(p.getProperty("close"));
	    txtUpdate.setText(p.getProperty("update"));
	    txtDelete.setText(p.getProperty("delete"));
	    txtInsert.setText(p.getProperty("insert"));
	} catch (FileNotFoundException e) {
	    JOptionPane.showMessageDialog(getParent(), "File di propriet\u00E0 non trovato", "FILE MANCANTE", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(getParent(), "Errore di IOException", "ERRORE I/O", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	}

    }

	/**
	 * This method initializes pnlNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord() {
	    if (pnlNord == null) {
		try {
		    FlowLayout flowLayout = new FlowLayout();
		    flowLayout.setAlignment(FlowLayout.LEFT);  // Generated
		    pnlNord = new JPanel();
		    pnlNord.setLayout(flowLayout);  // Generated
		    pnlNord.setPreferredSize(new Dimension(0, 40));  // Generated
		    pnlNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));  // Generated
		    pnlNord.add(getBtnSalva(), null);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlNord;
	}

	/**
	 * This method initializes pnlCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro() {
	    if (pnlCentro == null) {
		try {
		    GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		    gridBagConstraints21.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints21.gridy = 6;  // Generated
		    gridBagConstraints21.weightx = 1.0;  // Generated
		    gridBagConstraints21.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints21.gridx = 9;  // Generated
		    GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		    gridBagConstraints20.gridx = 8;  // Generated
		    gridBagConstraints20.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints20.gridy = 6;  // Generated
		    lblInsert = new JLabel();
		    lblInsert.setText("Insert");  // Generated
		    GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		    gridBagConstraints19.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints19.gridy = 6;  // Generated
		    gridBagConstraints19.weightx = 1.0;  // Generated
		    gridBagConstraints19.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints19.gridx = 7;  // Generated
		    GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		    gridBagConstraints18.gridx = 6;  // Generated
		    gridBagConstraints18.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints18.gridy = 6;  // Generated
		    lblDelete = new JLabel();
		    lblDelete.setText("Delete");  // Generated
		    GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		    gridBagConstraints17.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints17.gridy = 6;  // Generated
		    gridBagConstraints17.weightx = 1.0;  // Generated
		    gridBagConstraints17.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints17.gridx = 5;  // Generated
		    GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		    gridBagConstraints16.gridx = 4;  // Generated
		    gridBagConstraints16.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints16.gridy = 6;  // Generated
		    lblUpdate = new JLabel();
		    lblUpdate.setText("Update");  // Generated
		    GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		    gridBagConstraints15.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints15.gridy = 6;  // Generated
		    gridBagConstraints15.weightx = 1.0;  // Generated
		    gridBagConstraints15.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints15.gridx = 3;  // Generated
		    GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		    gridBagConstraints14.gridx = 2;  // Generated
		    gridBagConstraints14.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints14.gridy = 6;  // Generated
		    lblClose = new JLabel();
		    lblClose.setText("Close");  // Generated
		    GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		    gridBagConstraints13.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints13.gridy = 6;  // Generated
		    gridBagConstraints13.weightx = 1.0;  // Generated
		    gridBagConstraints13.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints13.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		    gridBagConstraints12.gridx = 0;  // Generated
		    gridBagConstraints12.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints12.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints12.gridy = 6;  // Generated
		    lblOpen = new JLabel();
		    lblOpen.setText("Open");  // Generated
		    GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		    gridBagConstraints11.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints11.gridy = 5;  // Generated
		    gridBagConstraints11.weightx = 1.0;  // Generated
		    gridBagConstraints11.gridwidth = 2;  // Generated
		    gridBagConstraints11.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints11.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		    gridBagConstraints10.gridx = 0;  // Generated
		    gridBagConstraints10.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints10.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints10.gridy = 5;  // Generated
		    lblNumFile = new JLabel();
		    lblNumFile.setText("Numero Salvataggi");  // Generated
		    lblNumFile.setToolTipText("Numero dei salvataggi da tenere memorizzati nella cartella di backup");  // Generated
		    GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		    gridBagConstraints9.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints9.gridy = 4;  // Generated
		    gridBagConstraints9.weightx = 1.0;  // Generated
		    gridBagConstraints9.gridwidth = 4;  // Generated
		    gridBagConstraints9.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints9.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		    gridBagConstraints8.gridx = 0;  // Generated
		    gridBagConstraints8.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints8.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints8.gridy = 4;  // Generated
		    lblFolderBackup = new JLabel();
		    lblFolderBackup.setText("Folder Backup");  // Generated
		    lblFolderBackup.setToolTipText("Cartella dove effettuare il salvatagio dei backup");  // Generated
		    GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		    gridBagConstraints7.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints7.gridy = 3;  // Generated
		    gridBagConstraints7.weightx = 1.0;  // Generated
		    gridBagConstraints7.gridwidth = 9;  // Generated
		    gridBagConstraints7.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints7.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		    gridBagConstraints6.gridx = 0;  // Generated
		    gridBagConstraints6.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints6.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints6.gridy = 3;  // Generated
		    lblCmdRestore = new JLabel();
		    lblCmdRestore.setText("Command Restore");  // Generated
		    lblCmdRestore.setToolTipText("Comando per effettuare il restore dei dati");  // Generated
		    GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		    gridBagConstraints5.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints5.gridy = 2;  // Generated
		    gridBagConstraints5.weightx = 1.0;  // Generated
		    gridBagConstraints5.gridwidth = 9;  // Generated
		    gridBagConstraints5.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints5.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		    gridBagConstraints4.gridx = 0;  // Generated
		    gridBagConstraints4.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints4.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints4.gridy = 2;  // Generated
		    lblCmdBackup = new JLabel();
		    lblCmdBackup.setText("Command Backup");  // Generated
		    lblCmdBackup.setToolTipText("Comando per effettuare il backup dei dati");  // Generated
		    GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		    gridBagConstraints3.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints3.gridy = 1;  // Generated
		    gridBagConstraints3.weightx = 1.0;  // Generated
		    gridBagConstraints3.gridwidth = 9;  // Generated
		    gridBagConstraints3.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints3.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		    gridBagConstraints2.gridx = 0;  // Generated
		    gridBagConstraints2.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints2.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints2.gridy = 1;  // Generated
		    lblPathCommand = new JLabel();
		    lblPathCommand.setText("Path Command");  // Generated
		    lblPathCommand.setToolTipText("Percorso dove sono situati i comandi del DB Postgre - cartella BIN");  // Generated
		    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		    gridBagConstraints1.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints1.gridy = 0;  // Generated
		    gridBagConstraints1.weightx = 1.0;  // Generated
		    gridBagConstraints1.gridwidth = 4;  // Generated
		    gridBagConstraints1.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints1.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.gridx = 0;  // Generated
		    gridBagConstraints.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints.insets = new Insets(2, 2, 2, 2);  // Generated
		    gridBagConstraints.gridy = 0;  // Generated
		    lblNomeDB = new JLabel();
		    lblNomeDB.setText("Nome DB");  // Generated
		    lblNomeDB.setToolTipText("Nome del DataBase dei backup");  // Generated
		    pnlCentro = new JPanel();
		    pnlCentro.setLayout(new GridBagLayout());  // Generated
		    pnlCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Configurazione gestione backup", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));  // Generated
		    pnlCentro.add(lblNomeDB, gridBagConstraints);  // Generated
		    pnlCentro.add(getTxtNomeDB(), gridBagConstraints1);  // Generated
		    pnlCentro.add(lblPathCommand, gridBagConstraints2);  // Generated
		    pnlCentro.add(getTxtPAthCommand(), gridBagConstraints3);  // Generated
		    pnlCentro.add(lblCmdBackup, gridBagConstraints4);  // Generated
		    pnlCentro.add(getTxtCmdBackup(), gridBagConstraints5);  // Generated
		    pnlCentro.add(lblCmdRestore, gridBagConstraints6);  // Generated
		    pnlCentro.add(getTxtCmdRestore(), gridBagConstraints7);  // Generated
		    pnlCentro.add(lblFolderBackup, gridBagConstraints8);  // Generated
		    pnlCentro.add(getTxtFolderBackup(), gridBagConstraints9);  // Generated
		    pnlCentro.add(lblNumFile, gridBagConstraints10);  // Generated
		    pnlCentro.add(getTxtNumFile(), gridBagConstraints11);  // Generated
		    pnlCentro.add(lblOpen, gridBagConstraints12);  // Generated
		    pnlCentro.add(getTxtOpen(), gridBagConstraints13);  // Generated
		    pnlCentro.add(lblClose, gridBagConstraints14);  // Generated
		    pnlCentro.add(getTxtClose(), gridBagConstraints15);  // Generated
		    pnlCentro.add(lblUpdate, gridBagConstraints16);  // Generated
		    pnlCentro.add(getTxtUpdate(), gridBagConstraints17);  // Generated
		    pnlCentro.add(lblDelete, gridBagConstraints18);  // Generated
		    pnlCentro.add(getTxtDelete(), gridBagConstraints19);  // Generated
		    pnlCentro.add(lblInsert, gridBagConstraints20);  // Generated
		    pnlCentro.add(getTxtInsert(), gridBagConstraints21);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlCentro;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
	    if (btnSalva == null) {
		try {
		    btnSalva = new JButton();
		    btnSalva.setText("Salva");  // Generated
		    btnSalva.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent e) {

		            salva();
		        }
		    });
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return btnSalva;
	}

	protected void salva() {
	    // salviamo tutti i dati di configurazione
	    p.setProperty("nameDb", txtNomeDB.getText());
	    p.setProperty("pathCommand", txtPAthCommand.getText());
	    p.setProperty("cmdBackup", txtCmdBackup.getText());
	    p.setProperty("cmdRestore", txtCmdRestore.getText());
	    p.setProperty("folderBackup", txtFolderBackup.getText());
	    p.setProperty("nFileBackup", txtNumFile.getText());
	    p.setProperty("open", txtOpen.getText());
	    p.setProperty("close", txtClose.getText());
	    p.setProperty("update", txtUpdate.getText());
	    p.setProperty("insert", txtInsert.getText());
	    p.setProperty("delet", txtDelete.getText());
	    try {
		p.store(new FileWriter("configurazione.properties"),null);
	    } catch (IOException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nel salvataggio del\nfile di configurazione", "ERRORE SALVATAGGIO", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		return;
	    }
	    // avviso di riavviare il sistema
	    JOptionPane.showMessageDialog(getParent(), "Riavviare il programma per\nrendere effettive le modifiche", "RIAVVIO SISTEMA", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * This method initializes txtNomeDB
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNomeDB() {
	    if (txtNomeDB == null) {
		try {
		    txtNomeDB = new JTextField();
		    txtNomeDB.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtNomeDB;
	}

	/**
	 * This method initializes txtPAthCommand
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPAthCommand() {
	    if (txtPAthCommand == null) {
		try {
		    txtPAthCommand = new JTextField();
		    txtPAthCommand.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtPAthCommand;
	}

	/**
	 * This method initializes txtCmdBackup
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCmdBackup() {
	    if (txtCmdBackup == null) {
		try {
		    txtCmdBackup = new JTextField();
		    txtCmdBackup.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtCmdBackup;
	}

	/**
	 * This method initializes txtCmdRestore
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCmdRestore() {
	    if (txtCmdRestore == null) {
		try {
		    txtCmdRestore = new JTextField();
		    txtCmdRestore.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtCmdRestore;
	}

	/**
	 * This method initializes txtFolderBackup
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFolderBackup() {
	    if (txtFolderBackup == null) {
		try {
		    txtFolderBackup = new JTextField();
		    txtFolderBackup.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtFolderBackup;
	}

	/**
	 * This method initializes txtNumFile
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumFile() {
	    if (txtNumFile == null) {
		try {
		    txtNumFile = new JTextField();
		    txtNumFile.setPreferredSize(new Dimension(250, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtNumFile;
	}

	/**
	 * This method initializes txtOpen
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtOpen() {
	    if (txtOpen == null) {
		try {
		    txtOpen = new JTextField();
		    txtOpen.setPreferredSize(new Dimension(20, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtOpen;
	}

	/**
	 * This method initializes txtClose
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtClose() {
	    if (txtClose == null) {
		try {
		    txtClose = new JTextField();
		    txtClose.setPreferredSize(new Dimension(20, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtClose;
	}

	/**
	 * This method initializes txtUpdate
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUpdate() {
	    if (txtUpdate == null) {
		try {
		    txtUpdate = new JTextField();
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtUpdate;
	}

	/**
	 * This method initializes txtDelete
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDelete() {
	    if (txtDelete == null) {
		try {
		    txtDelete = new JTextField();
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtDelete;
	}

	/**
	 * This method initializes txtInsert
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtInsert() {
	    if (txtInsert == null) {
		try {
		    txtInsert = new JTextField();
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtInsert;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
