package rf.pegaso.gui.gestione;

import org.apache.log4j.Logger;


import it.infolabs.hibernate.CategoriaCliente;
import it.infolabs.hibernate.CategoriaClienteHome;
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

import org.hibernate.exception.ConstraintViolationException;
import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.CategorieClientiModello;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

public class CategoriaClientiGestione extends JDialog {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CategoriaClientiGestione.class);

	private class MyMouseAdapter extends MouseAdapter {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MyMouseAdapter.class);

		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2
					&& e.getSource() == tblCategorieCliente) {
				// Selezioniamo la riga e prendiamo il
				// suo ID che rappresenta la riga nel DB
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
				if (row < 0)
					return;
				// Impostiamo tutti i campi per poi eventualmente modificarli
				int id = ((Number) table.getValueAt(row, 0)).intValue();
				savedListino = id;
				CategoriaClienteHome.getInstance().begin();
				CategoriaCliente l = CategoriaClienteHome.getInstance().findById(savedListino);
				txtDescrizione.setText(l.getDescrizione());
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

	private JTextField txtDescrizione = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblCategorieCliente = null;

	private JPanel pnlBottoni = null;

	private JButton btnSalva = null;

	private JButton btnChiudi = null;

	private int savedListino=-1;

	private JButton btnElimina = null;

	/**
	 * @param owner
	 */
	public CategoriaClientiGestione(Frame owner) {
		super(owner,true);

		initialize();
	}
	public CategoriaClientiGestione(Dialog owner) {
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
		this.setTitle("Gestione Categorie");
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
			gridLayout2.setRows(1);
			gridLayout2.setColumns(1);
			pnlDati = new JPanel();
			pnlDati.setLayout(gridLayout2);
			pnlDati.setPreferredSize(new Dimension(0, 60));
			pnlDati.add(getJPanel3(), null);
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
			lblProvince.setText("Categoria");
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
		if (txtDescrizione == null) {
			txtDescrizione = new JTextField();
			txtDescrizione.setDocument(new UpperTextDocument());
		}
		return txtDescrizione;
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
			jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Elenco Categorie Cliente", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
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
		if (tblCategorieCliente == null) {
			tblCategorieCliente = new JXTable();
			tblCategorieCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblCategorieCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			try {
				CategorieClientiModello modello=new CategorieClientiModello();
				tblCategorieCliente.setModel(modello);
				DBManager.getIstanceSingleton().addDBStateChange(modello);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			configuraTabellaConvenzioni();
		}
		return tblCategorieCliente;
	}

	private void configuraTabellaConvenzioni() {
		// TODO Auto-generated method stub
		tblCategorieCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Generated
		tblCategorieCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Generated
		tblCategorieCliente.addMouseListener(new MyMouseAdapter());
		tblCategorieCliente.packAll();

		TableColumn col = tblCategorieCliente.getColumnModel().getColumn(0);
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
					salva(); 
				}
			});
		}
		return btnSalva;
	}

	protected void salva() {
		if (logger.isDebugEnabled()) {
			logger.debug("salva() - start");
		}
		if(savedListino==0){
			return;
		}
		CategoriaClienteHome.getInstance().begin();
		CategoriaCliente l = CategoriaClienteHome.getInstance().findById(savedListino);
		if(l==null){
			l=new CategoriaCliente();
		}
		l.setDescrizione(txtDescrizione.getText());
		if (l.getDescrizione().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Il campo descrizione non può essere nullo", "Errore",
					JOptionPane.ERROR_MESSAGE);

			if (logger.isDebugEnabled()) {
				logger.debug("salva() - end");
			}
			return;
		}
		CategoriaClienteHome.getInstance().attachDirty(l);
		try {
			CategoriaClienteHome.getInstance().commitAndClose();
		} catch (ConstraintViolationException e) {
			logger.error("salva()", e);
			
		}
		this.savedListino=-1;
		txtDescrizione.setText("");

		if (logger.isDebugEnabled()) {
			logger.debug("salva() - end");
		}
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
		int row=tblCategorieCliente.getSelectedRow();

		if (row < 0){
			JOptionPane.showMessageDialog(this, "Selezionare la categoria da eliminare", "AVVISSO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int scelta=JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare la categoria selezionata?", "AVVISO", JOptionPane.YES_NO_OPTION);
		if(scelta==JOptionPane.NO_OPTION ||scelta==JOptionPane.CANCEL_OPTION)
			return;
		// Impostiamo tutti i campi per poi eventualmente modificarli
		int id = ((Number) tblCategorieCliente.getValueAt(row, 0)).intValue();
		if(id==0){
			return;
		}
		CategoriaClienteHome.getInstance().begin();
		CategoriaCliente l=CategoriaClienteHome.getInstance().findById(id);
		CategoriaClienteHome.getInstance().delete(l);
		CategoriaClienteHome.getInstance().commitAndClose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
