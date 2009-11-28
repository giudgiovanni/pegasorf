package rf.pegaso.gui.gestione;


import it.infolabs.hibernate.ProvinciaHome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.ProvinceModello;
import rf.pegaso.db.tabelle.Provincia;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

public class ProvincieGestione extends JDialog {

	private class MyMouseAdapter extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2
					&& e.getSource() == tblProvince) {
				// Selezioniamo la riga e prendiamo il
				// suo ID che rappresenta la riga nel DB
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
				if (row < 0)
					return;
				// Impostiamo tutti i campi per poi eventualmente modificarli
				int id = ((Number) table.getValueAt(row, 0)).intValue();
				savedListino = id;
				ProvinciaHome.getInstance().begin();
				it.infolabs.hibernate.Provincia l = ProvinciaHome.getInstance().findById(savedListino);
				txtProvince.setText(l.getProvincia());
				txtTarga.setText(l.getTarga());
			}
		}
	}


	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private int idprovincia=-1;

	private JPanel pnlConvenzione = null;

	private JPanel pnlDati = null;

	private JPanel jPanel3 = null;

	private JLabel lblProvince = null;

	private JTextField txtProvince = null;

	private JPanel pnlNote = null;

	private JTextField txtTarga = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblProvince = null;

	private JPanel pnlBottoni = null;

	private JButton btnSalva = null;

	private JButton btnChiudi = null;

	private int savedListino=-1;

	private JButton btnElimina = null;

	private JLabel lblTarga = null;

	/**
	 * @param owner
	 */
	public ProvincieGestione(Frame owner) {
		super(owner,true);

		initialize();
	}
	public ProvincieGestione(Dialog owner) {
		super(owner,true);

		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Gestione Province");
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
			jContentPane.add(getPnlConvenzione(), BorderLayout.CENTER);
			jContentPane.add(getPnlBottoni(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlConvenzione
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlConvenzione() {
		if (pnlConvenzione == null) {
			pnlConvenzione = new JPanel();
			pnlConvenzione.setLayout(new BorderLayout());

			pnlConvenzione.setPreferredSize(new Dimension(475, 300));
			pnlConvenzione.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlConvenzione.add(getPnlDati(), java.awt.BorderLayout.NORTH);
			pnlConvenzione.add(getJPanel2(), java.awt.BorderLayout.CENTER);
		}
		return pnlConvenzione;
	}

	/**
	 * This method initializes pnlDati
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDati() {
		if (pnlDati == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(2);
			gridLayout2.setColumns(1);
			pnlDati = new JPanel();
			pnlDati.setBorder(BorderFactory.createTitledBorder(null, "Dati provincia", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlDati.setLayout(gridLayout2);
			pnlDati.setPreferredSize(new Dimension(0, 120));
			pnlDati.add(getJPanel3(), null);
			pnlDati.add(getPnlNote(), null);
		}
		return pnlDati;
	}

	/**
	 * This method initializes jPanel3
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			lblProvince = new JLabel();
			lblProvince.setText("Provincia");
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			jPanel3 = new JPanel();
			jPanel3.setLayout(gridLayout1);
			jPanel3.setPreferredSize(new Dimension(0, 50));
			jPanel3.add(lblProvince, null);
			jPanel3.add(getTxtProvince(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes txtProvince
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtProvince() {
		if (txtProvince == null) {
			txtProvince = new JTextField();
			txtProvince.setDocument(new UpperTextDocument());
		}
		return txtProvince;
	}

	/**
	 * This method initializes pnlNote
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNote() {
		if (pnlNote == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setColumns(1);
			lblTarga = new JLabel();
			lblTarga.setText("Targa");
			pnlNote = new JPanel();
			pnlNote.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlNote.setLayout(gridLayout);
			pnlNote.add(lblTarga, null);
	pnlNote.add(getTxtTarga(), null);
		}
		return pnlNote;
	}

	/**
	 * This method initializes txtTarga
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextField getTxtTarga() {
		if (txtTarga == null) {
			txtTarga = new JTextField();
			txtTarga.setDocument(new UpperTextDocument());
		}
		return txtTarga;
	}

	/**
	 * This method initializes jPanel2
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Elenco Province", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel2.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblProvince());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblConenzione
	 *
	 * @return org.jdesktop.swingx.JXTable
	 */
	private JXTable getTblProvince() {
		if (tblProvince == null) {
			tblProvince = new JXTable();
			tblProvince.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblProvince.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			try {
				ProvinceModello modello=new ProvinceModello();
				tblProvince.setModel(modello);
				DBManager.getIstanceSingleton().addDBStateChange(modello);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			configuraTabellaConvenzioni();
		}
		return tblProvince;
	}

	private void configuraTabellaConvenzioni() {
		// TODO Auto-generated method stub
		tblProvince.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Generated
		tblProvince.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Generated
		tblProvince.addMouseListener(new MyMouseAdapter());
		tblProvince.packAll();

		TableColumn col = tblProvince.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
	}

	/**
	 * This method initializes pnlBottoni
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBottoni() {
		if (pnlBottoni == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlBottoni = new JPanel();
			pnlBottoni.setLayout(flowLayout);
			pnlBottoni.setPreferredSize(new Dimension(0, 40));
			pnlBottoni.add(getBtnSalva(), null);
			pnlBottoni.add(getBtnElimina(), null);
			pnlBottoni.add(getBtnChiudi(), null);
		}
		return pnlBottoni;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Salva");
			btnSalva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					salva(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnSalva;
	}

	protected void salva() {
		ProvinciaHome.getInstance().begin();
		it.infolabs.hibernate.Provincia l = ProvinciaHome.getInstance().findById(savedListino);
		l.setProvincia(txtProvince.getText());
		l.setTarga(txtTarga.getText());
		if (l.getProvincia().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Il campo provincia non può essere nullo", "Errore",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		ProvinciaHome.getInstance().attachDirty(l);
		ProvinciaHome.getInstance().commitAndClose();
		this.savedListino=-1;
		txtProvince.setText("");
		txtTarga.setText("");
		


	}

	/**
	 * This method initializes btnChiudi
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnChiudi() {
		if (btnChiudi == null) {
			btnChiudi = new JButton();
			btnChiudi.setText("Chiudi");
			btnChiudi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnElimina
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			btnElimina = new JButton();
			btnElimina.setText("Cancella");
			btnElimina.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					elimina(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnElimina;
	}
	protected void elimina() {
		int row=tblProvince.getSelectedRow();

		if (row < 0){
			JOptionPane.showMessageDialog(this, "Selezionare la provincia da eliminare", "AVVISSO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int scelta=JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare la provincia selezionata?", "AVVISO", JOptionPane.YES_NO_OPTION);
		if(scelta==JOptionPane.NO_OPTION ||scelta==JOptionPane.CANCEL_OPTION)
			return;
		// Impostiamo tutti i campi per poi eventualmente modificarli
		int id = ((Number) tblProvince.getValueAt(row, 0)).intValue();
		ProvinciaHome.getInstance().begin();
		it.infolabs.hibernate.Provincia l=ProvinciaHome.getInstance().findById(id);
		ProvinciaHome.getInstance().delete(l);
		ProvinciaHome.getInstance().commitAndClose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
