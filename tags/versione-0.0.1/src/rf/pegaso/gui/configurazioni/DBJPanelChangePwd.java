package rf.pegaso.gui.configurazioni;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DBJPanelChangePwd extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * This is the default constructor
     */
    public DBJPanelChangePwd() {
	super();
	initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
	this.setSize(300, 200);
	this.setLayout(new GridBagLayout());
    }

}
