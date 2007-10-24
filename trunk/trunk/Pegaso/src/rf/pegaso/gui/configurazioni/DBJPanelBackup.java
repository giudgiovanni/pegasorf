package rf.pegaso.gui.configurazioni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import rf.pegaso.db.UtilityDBManager;

public class DBJPanelBackup extends JPanel {

    private static final long serialVersionUID = 1L;
	private JPanel pnlNord = null;
	private JButton btnBackup = null;
	private JButton btnRestore = null;
	private JPanel pnlCentro = null;
	private JList lstElencoBackup = null;
	private JScrollPane jScrollPane = null;
	private JPanel pnlSud = null;
	private JScrollPane jScrollPane1 = null;
	private JList lstMessaggi = null;
	private DefaultListModel listModel;

    /**
     * This is the default constructor
     */
    public DBJPanelBackup() {
	super();
	initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
	this.setSize(569, 261);
	this.setLayout(new BorderLayout());
	this.add(getPnlNord(), BorderLayout.NORTH);  // Generated
	this.add(getPnlCentro(), BorderLayout.CENTER);  // Generated
	this.add(getPnlSud(), BorderLayout.SOUTH);  // Generated
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
		    pnlNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));  // Generated
		    pnlNord.setLayout(flowLayout);  // Generated
		    pnlNord.setPreferredSize(new Dimension(0, 40));  // Generated
		    pnlNord.add(getBtnBackup(), null);  // Generated
		    pnlNord.add(getBtnRestore(), null);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlNord;
	}

	/**
	 * This method initializes btnBackup
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnBackup() {
	    if (btnBackup == null) {
		try {
		    btnBackup = new JButton();
		    btnBackup.setText("Crea Backup");  // Generated
		    btnBackup.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent e) {
		    		creaBakup();
		        }
		    });
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return btnBackup;
	}

	protected void creaBakup() {
	    try {
		UtilityDBManager.getSingleInstance().backupDataBase();

//		 ricarichiamo la lista dei backup effettuati
		caricaLista();
	    } catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nel file di configurazione", "ERRORE", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }




	}

	private void caricaLista() {
	    try {
		// ricarichiamo la lista dei backup effettuati
		DefaultListModel model=new DefaultListModel();
		// ritardiamo di 1sec per far si che prima vengano calcellati i file del tutto
		// altrimenti visualizza alcuni dati che poi effettivamente ancano.
		Thread.sleep(1000);
		String nf[]=UtilityDBManager.getSingleInstance().elencoFileBackup();
		for(String tmp : nf){
		    model.addElement(tmp);
		}
		lstElencoBackup.setModel(model);
	    } catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nel file di configurazione", "ERRORE", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes btnRestore
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRestore() {
	    if (btnRestore == null) {
		try {
		    btnRestore = new JButton();
		    btnRestore.setText("Ripristina Backup");  // Generated
		    btnRestore.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent e) {

		            restoreBackup();
		        }
		    });
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return btnRestore;
	}

	protected void restoreBackup() {
	    try {

		UtilityDBManager.getSingleInstance().restoreBackup((String)lstElencoBackup.getSelectedValue(),listModel);
	    } catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nel file di configurazione", "ERRORE", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SQLException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nell'inserimento dei\ndati nel db postgre", "ERRORE SQL", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    }

	}

	/**
	 * This method initializes pnlCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro() {
	    if (pnlCentro == null) {
		try {
		    pnlCentro = new JPanel();
		    pnlCentro.setLayout(new BorderLayout());  // Generated
		    pnlCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Elenco backup effettuati", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));  // Generated
		    pnlCentro.add(getJScrollPane(), BorderLayout.CENTER);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlCentro;
	}

	/**
	 * This method initializes lstElencoBackup
	 *
	 * @return javax.swing.JList
	 */
	private JList getLstElencoBackup() {
	    if (lstElencoBackup == null) {
		try {
		    lstElencoBackup = new JList();
		    caricaLista();
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return lstElencoBackup;
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
		    jScrollPane.setViewportView(getLstElencoBackup());  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return jScrollPane;
	}

	/**
	 * This method initializes pnlSud
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud() {
	    if (pnlSud == null) {
		try {
		    pnlSud = new JPanel();
		    pnlSud.setLayout(new BorderLayout());  // Generated
		    pnlSud.add(getJScrollPane1(), BorderLayout.CENTER);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlSud;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
	    if (jScrollPane1 == null) {
		try {
		    jScrollPane1 = new JScrollPane();
		    jScrollPane1.setPreferredSize(new Dimension(3, 150));  // Generated
		    jScrollPane1.setViewportView(getTxtMessaggi());  // Generated
		    jScrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Comandi SQL inviati al DB", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return jScrollPane1;
	}

	/**
	 * This method initializes txtMessaggi
	 *
	 * @return javax.swing.JTextArea
	 */
	private JList getTxtMessaggi() {
	    if (lstMessaggi == null) {
		try {
		    listModel=new DefaultListModel();
		    lstMessaggi = new JList(listModel);
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return lstMessaggi;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
