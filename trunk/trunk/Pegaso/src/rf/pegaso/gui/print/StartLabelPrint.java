/**
 * 
 */
package rf.pegaso.gui.print;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import rf.utility.gui.UtilGUI;
import javax.swing.JTextField;

/**
 * @author Hunter
 * 
 */
public class StartLabelPrint extends JDialog {

	private class MyToggleButton extends JToggleButton {
		private int pos;

		public MyToggleButton(int pos) {
			this.pos = pos;
		}

		public int getPosizione() {
			return this.pos;
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlCenter = null;
	private int row;
	private int column;
	private JPanel pnlNord = null;
	private JButton btnOK = null;
	private int[] startPos;
	private ButtonGroup group = null;
	private MyToggleButton allToggleButton[];
	private JSpinner spnCopie = null;
	private int[] nCopie;

	/**
	 * @param owner
	 */
	public StartLabelPrint(JDialog owner, int row, int column, 
			int[] startPos /*copia per riferimento*/,
			int[] nCopie /*copia per riferimento*/) {
		
		super(owner, true);
		this.row = row;
		this.column = column;
		this.startPos = startPos;
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public StartLabelPrint(JFrame owner, int row, int column, 
			int[] startPos /*copia per riferimento*/,
			int[] nCopie /*copia per riferimento*/) {
		
		
		super(owner, true);
		this.row = row;
		this.column = column;
		this.startPos = startPos;
		this.nCopie=nCopie;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(274, 353);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setTitle("Selezionare etichetta di partenza"); // Generated
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
			jContentPane.add(getPnlCenter(), BorderLayout.CENTER); // Generated
			jContentPane.add(getPnlNord(), BorderLayout.NORTH); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlCenter
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCenter() {
		if (pnlCenter == null) {
			try {
				GridLayout gridLayout = new GridLayout();
				gridLayout.setRows(this.row);
				gridLayout.setColumns(this.column);
				pnlCenter = new JPanel();
				pnlCenter.setLayout(gridLayout);
				int nLabels = this.row * this.column;
				// per tenere traccia di tutti i bottoni
				this.allToggleButton = new MyToggleButton[nLabels];
				// per raggruppare tutti i bottoni
				this.group = new ButtonGroup();

				for (int i = 0; i < nLabels; i++) {
					MyToggleButton btn = new MyToggleButton(i + 1);
					btn.setBackground(Color.green);
					btn.setText(new Integer(i + 1).toString());
					btn.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							selezioneAndInvio();
						}
					});
					group.add(btn);
					this.allToggleButton[i] = btn;
					pnlCenter.add(btn, null); // Generated
				}
				this.allToggleButton[0].setSelected(true);

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCenter;
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
				flowLayout.setAlignment(FlowLayout.LEFT); // Generated
				pnlNord = new JPanel();
				pnlNord.setLayout(flowLayout); // Generated
				pnlNord.setPreferredSize(new Dimension(0, 40)); // Generated
				pnlNord.add(getBtnOK(), null); // Generated
				pnlNord.add(getSpnCopie(), null);  // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes btnOK
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			try {
				btnOK = new JButton();
				btnOK.setText("OK"); // Generated
				btnOK.setPreferredSize(new Dimension(51, 20)); // Generated
				btnOK.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						chiudi();
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnOK;
	}

	/**
	 * 
	 */
	protected void chiudi() {
		// copiamo il numero di copie per
		// etichetta
		this.nCopie[0]=(Integer)spnCopie.getValue();
		
		// chiudiamo la schermata
		dispose();

	}

	/**
	 * 
	 */
	protected void selezioneAndInvio() {
		for (int i = 0; i < this.allToggleButton.length; i++) {
			MyToggleButton btn = this.allToggleButton[i];
			if (btn.isSelected()) {
				this.startPos[0] = btn.getPosizione();
				return;
			}
		}
	}

	/**
	 * This method initializes spnCopie	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JSpinner getSpnCopie() {
		if (spnCopie == null) {
			try {
				SpinnerNumberModel model =new SpinnerNumberModel(1,1,100,1);
				spnCopie = new JSpinner(model);
				spnCopie.setPreferredSize(new Dimension(50, 20));  // Generated
				
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return spnCopie;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
