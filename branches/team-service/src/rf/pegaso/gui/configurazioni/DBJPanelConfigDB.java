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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class DBJPanelConfigDB extends JPanel {

    private static final long serialVersionUID = 1L;
	private JPanel pnlBottoni = null;
	private MyJButton btnSalva = null;
	private JPanel pnlCentro = null;
	private JLabel lblUrl = null;
	private JTextField txtUrl = null;
	private JLabel lblJdbc = null;
	private JTextField txtDriver = null;
	private JLabel lblUser = null;
	private JLabel lblPwd = null;
	private JTextField txtUser = null;
	private JPasswordField pwdPass = null;
	private Properties p;  //  @jve:decl-index=0:

    /**
     * This is the default constructor
     */
    public DBJPanelConfigDB() {
	super();
	initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
	this.setSize(439, 191);
	this.setLayout(new BorderLayout());
	this.add(getPnlBottoni(), BorderLayout.NORTH);  // Generated
	this.add(getPnlCentro(), BorderLayout.CENTER);

	//carichiamo i dati dal file di properties
	p=new Properties();
	try {
	    p.load(new FileReader("configurazioni.properties"));

	    //ora impostiamo i textfiled
	    txtDriver.setText(p.getProperty("driverjdbc"));
	    txtUrl.setText(p.getProperty("urldb"));
	    txtUser.setText(p.getProperty("user"));
	    pwdPass.setText(p.getProperty("password"));
	} catch (FileNotFoundException e) {
	    JOptionPane.showMessageDialog(getParent(), "File configurazione mancante", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

	/**
	 * This method initializes pnlBottoni
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBottoni() {
	    if (pnlBottoni == null) {
		try {
		    FlowLayout flowLayout = new FlowLayout();
		    flowLayout.setAlignment(FlowLayout.LEFT);  // Generated
		    flowLayout.setVgap(5);  // Generated
		    pnlBottoni = new JPanel();
		    pnlBottoni.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));  // Generated
		    pnlBottoni.setLayout(flowLayout);  // Generated
		    pnlBottoni.setPreferredSize(new Dimension(10, 40));  // Generated
		    pnlBottoni.add(getBtnSalva(), null);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlBottoni;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private MyJButton getBtnSalva() {
	    if (btnSalva == null) {
		try {
		    btnSalva = new MyJButton();
		    btnSalva.setText("Salva");
		    btnSalva.setEnabled(true);
		    btnSalva.setPreferredSize(new Dimension(65, 25));  // Generated
		    btnSalva.addActionListener(new java.awt.event.ActionListener() {
		        public void actionPerformed(java.awt.event.ActionEvent e) {

		            salvaConfigurazioni();
		        }
		    });


		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return btnSalva;
	}

	protected void salvaConfigurazioni() {
	    p.setProperty("user", txtUser.getText());
	    p.setProperty("password", new String(pwdPass.getPassword()).toString());
	    p.setProperty("driverjdbc", txtDriver.getText());
	    p.setProperty("urldb", txtUrl.getText());
	    try {
		p.store(new FileWriter("configurazioni.properties"),null);
	    } catch (IOException e) {
		JOptionPane.showMessageDialog(getParent(), "Errore nel salvataggio del\nfile di configurazione", "ERRORE SALVATAGGIO", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    }
	    // avviso di riavviare il sistema
	    JOptionPane.showMessageDialog(getParent(), "Riavviare il programma per\nrendere effettive le modifiche", "RIAVVIO SISTEMA", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * This method initializes pnlCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro() {
	    if (pnlCentro == null) {
		try {
		    GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		    gridBagConstraints7.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints7.gridy = 3;  // Generated
		    gridBagConstraints7.weightx = 1.0;  // Generated
		    gridBagConstraints7.anchor = GridBagConstraints.WEST;  // Generated
		    gridBagConstraints7.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints7.weighty = 0.0;  // Generated
		    gridBagConstraints7.ipadx = 1;  // Generated
		    gridBagConstraints7.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		    gridBagConstraints6.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints6.gridy = 2;  // Generated
		    gridBagConstraints6.weightx = 1.0;  // Generated
		    gridBagConstraints6.anchor = GridBagConstraints.WEST;  // Generated
		    gridBagConstraints6.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints6.weighty = 0.0;  // Generated
		    gridBagConstraints6.ipadx = 1;  // Generated
		    gridBagConstraints6.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		    gridBagConstraints5.gridx = 0;  // Generated
		    gridBagConstraints5.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints5.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints5.gridy = 3;  // Generated
		    lblPwd = new JLabel();
		    lblPwd.setText("Password DB");  // Generated
		    GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		    gridBagConstraints4.gridx = 0;  // Generated
		    gridBagConstraints4.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints4.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints4.gridy = 2;  // Generated
		    lblUser = new JLabel();
		    lblUser.setText("User DB");  // Generated
		    GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		    gridBagConstraints3.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints3.gridy = 1;  // Generated
		    gridBagConstraints3.weightx = 1.0;  // Generated
		    gridBagConstraints3.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints3.anchor = GridBagConstraints.WEST;  // Generated
		    gridBagConstraints3.weighty = 0.0;  // Generated
		    gridBagConstraints3.ipadx = 1;  // Generated
		    gridBagConstraints3.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		    gridBagConstraints2.gridx = 0;  // Generated
		    gridBagConstraints2.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints2.weightx = 0.0;  // Generated
		    gridBagConstraints2.weighty = 0.0;  // Generated
		    gridBagConstraints2.gridy = 1;  // Generated
		    lblJdbc = new JLabel();
		    lblJdbc.setText("Nome driver JDBC");  // Generated
		    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		    gridBagConstraints1.fill = GridBagConstraints.BOTH;  // Generated
		    gridBagConstraints1.gridy = 0;  // Generated
		    gridBagConstraints1.weightx = 1.0;  // Generated
		    gridBagConstraints1.anchor = GridBagConstraints.WEST;  // Generated
		    gridBagConstraints1.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints1.weighty = 0.0;  // Generated
		    gridBagConstraints1.ipadx = 0;  // Generated
		    gridBagConstraints1.ipady = 0;  // Generated
		    gridBagConstraints1.gridx = 1;  // Generated
		    GridBagConstraints gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.gridx = 0;  // Generated
		    gridBagConstraints.ipadx = 0;  // Generated
		    gridBagConstraints.gridheight = 1;  // Generated
		    gridBagConstraints.anchor = GridBagConstraints.EAST;  // Generated
		    gridBagConstraints.insets = new Insets(3, 3, 3, 3);  // Generated
		    gridBagConstraints.gridy = 0;  // Generated
		    lblUrl = new JLabel();
		    lblUrl.setText("URL DB");  // Generated
		    pnlCentro = new JPanel();
		    pnlCentro.setLayout(new GridBagLayout());  // Generated
		    pnlCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Dati configurazione connessione db postgre", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));  // Generated
		    pnlCentro.add(lblUrl, gridBagConstraints);  // Generated
		    pnlCentro.add(getTxtUrl(), gridBagConstraints1);  // Generated
		    pnlCentro.add(lblJdbc, gridBagConstraints2);  // Generated
		    pnlCentro.add(getTxtDriver(), gridBagConstraints3);  // Generated
		    pnlCentro.add(lblUser, gridBagConstraints4);  // Generated
		    pnlCentro.add(lblPwd, gridBagConstraints5);  // Generated
		    pnlCentro.add(getTxtUser(), gridBagConstraints6);  // Generated
		    pnlCentro.add(getPwdPass(), gridBagConstraints7);  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pnlCentro;
	}

	/**
	 * This method initializes txtUrl
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUrl() {
	    if (txtUrl == null) {
		try {
		    txtUrl = new JTextField();
		    txtUrl.setPreferredSize(new Dimension(300, 20));  // Generated

		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtUrl;
	}

	/**
	 * This method initializes txtDriver
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDriver() {
	    if (txtDriver == null) {
		try {
		    txtDriver = new JTextField();
		    txtDriver.setPreferredSize(new Dimension(300, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtDriver;
	}

	/**
	 * This method initializes txtUser
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUser() {
	    if (txtUser == null) {
		try {
		    txtUser = new JTextField();
		    txtUser.setPreferredSize(new Dimension(300, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return txtUser;
	}

	/**
	 * This method initializes pwdPass
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getPwdPass() {
	    if (pwdPass == null) {
		try {
		    pwdPass = new JPasswordField();
		    pwdPass.setPreferredSize(new Dimension(300, 20));  // Generated
		} catch (java.lang.Throwable e) {
		    // TODO: Something
		}
	    }
	    return pwdPass;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
