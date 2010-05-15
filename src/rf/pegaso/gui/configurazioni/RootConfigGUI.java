package rf.pegaso.gui.configurazioni;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import rf.utility.gui.UtilGUI;

public class RootConfigGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel pnlCentro = null;
    private JXTaskPaneContainer taskPaneContainer; // @jve:decl-index=0:
    private JScrollPane jScrollPane = null;
    private DBJPanelConfigDB DBJPanelConfig = null;
	private DBJPanelBackup DBJPanelBackup = null;
	private DBJPanelConfigBackup DBJPanelConfigBackup = null;

    /**
         * @param owner
         */
    public RootConfigGUI() {
	super();
	initialize();
    }

    /**
         * This method initializes this
         *
         * @return void
         */
    private void initialize() {
	this.setSize(750, 440);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  // Generated
	this.setTitle("Configurazioni di Amministrazione"); // Generated
	this.setResizable(true); // Generated
	this.setContentPane(getJContentPane());

	// un contenitore per mettere tutti i JXTaskPane insieme
	JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

	// aggiungiamo i taskPane al contenitore
	JXTaskPane dbConfig = createDBConfig();
	JXTaskPane dbBackup = createDBBackup();
	taskPaneContainer.add(dbConfig);
	taskPaneContainer.add(dbBackup);

	// mettiamo tutto in uno scrollpane per avere la certezza
	// che si vedano tutti.
	jContentPane.add(new JScrollPane(taskPaneContainer), BorderLayout.WEST);

	//centriamo
	UtilGUI.centraFrame(this);

    }

    private JXTaskPane createDBBackup() {
	// create a first taskPane with common actions
	JXTaskPane backup = new JXTaskPane();
	backup.setTitle("Gestione Backup");
	backup.setSpecial(true);

	// creiamo l'action che verràpoi aggunta
	Action gestioneBackup = createActionGestioneBackup();
	Action configBackup=createActionConfigBackup();
	backup.add(gestioneBackup);
	backup.add(configBackup);
	return backup;
    }

    private Action createActionConfigBackup() {
	AbstractAction action = new AbstractAction(
	"Apri configurazione backup") {

    public void actionPerformed(ActionEvent e) {

	// visualizziamo il pannello interessato
	CardLayout card = (CardLayout) pnlCentro.getLayout();
	card.show(pnlCentro, "configbackup");
    }

};
return action;
    }

    private Action createActionGestioneBackup() {
	AbstractAction action = new AbstractAction(
		"Apri gestione backup") {

	    public void actionPerformed(ActionEvent e) {

		// visualizziamo il pannello interessato
		CardLayout card = (CardLayout) pnlCentro.getLayout();
		card.show(pnlCentro, "backup");
	    }

	};
	return action;
    }

    private JXTaskPane createDBConfig() {
	// create a first taskPane with common actions
	JXTaskPane config = new JXTaskPane();
	config.setTitle("Configurazioni DataBase Postgres");
	config.setSpecial(true);

	// creiamo l'action che verràpoi aggunta
	Action configDB = createActionConfigDB();
	config.add(configDB);
	return config;
    }

    private Action createActionConfigDB() {
	AbstractAction action = new AbstractAction(
		"Apri configurazione postgre") {

	    public void actionPerformed(ActionEvent e) {

		// visualizziamo il pannello interessato
		CardLayout card = (CardLayout) pnlCentro.getLayout();
		card.show(pnlCentro, "dbconfig");
	    }

	};
	return action;
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
	    jContentPane.add(getPnlCentro(), BorderLayout.CENTER); // Generated

	}
	return jContentPane;
    }

    /**
         * This method initializes pnlCentro
         *
         * @return javax.swing.JPanel
         */
    private JPanel getPnlCentro() {
	if (pnlCentro == null) {
	    try {
		CardLayout card = new CardLayout(5, 5);
		pnlCentro = new JPanel();
		pnlCentro.setLayout(card); // Generated
		pnlCentro.add(getDBJPanelConfig(), "dbconfig"); // Generated
		pnlCentro.add(getDBJPanelBackup(), "backup");  // Generated
		pnlCentro.add(getDBJPanelConfigBackup(), "configbackup");  // Generated
	    } catch (java.lang.Throwable e) {
		// TODO: Something
	    }
	}
	return pnlCentro;
    }



    /**
         * This method initializes DBJPanelConfig
         *
         * @return rf.pegaso.gui.configurazioni.DBJPanelConfig
         */
    private DBJPanelConfigDB getDBJPanelConfig() {
	if (DBJPanelConfig == null) {
	    try {
		DBJPanelConfig = new DBJPanelConfigDB();
		DBJPanelConfig.setName("DBJPanelConfig"); // Generated
	    } catch (java.lang.Throwable e) {
		// TODO: Something
	    }
	}
	return DBJPanelConfig;
    }

	/**
	 * This method initializes DBJPanelBackup
	 *
	 * @return rf.pegaso.gui.configurazioni.DBJPanelBackup
	 */
	private DBJPanelBackup getDBJPanelBackup() {
	    if (DBJPanelBackup == null) {
		try {
		    DBJPanelBackup = new DBJPanelBackup();
		    DBJPanelBackup.setName("DBJPanelBackup");  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return DBJPanelBackup;
	}

	/**
	 * This method initializes DBJPanelConfigBackup
	 *
	 * @return rf.pegaso.gui.configurazioni.DBJPanelConfigBackup
	 */
	private DBJPanelConfigBackup getDBJPanelConfigBackup() {
	    if (DBJPanelConfigBackup == null) {
		try {
		    DBJPanelConfigBackup = new DBJPanelConfigBackup();
		    DBJPanelConfigBackup.setName("DBJPanelConfigBackup");  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return DBJPanelConfigBackup;
	}

} // @jve:decl-index=0:visual-constraint="10,120"
