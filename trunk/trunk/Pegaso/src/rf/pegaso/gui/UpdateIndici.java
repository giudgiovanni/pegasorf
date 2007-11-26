package rf.pegaso.gui;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rf.myswing.IDJComboBox;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Documento;
import rf.pegaso.db.tabelle.Fornitore;
import rf.utility.gui.text.AutoCompletion;
import javax.swing.JButton;

public class UpdateIndici extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private IDJComboBox cmbFornitori = null;

	private IDJComboBox cmbDocumenti = null;

	private JButton jButton = null;

	/**
	 * This is the default constructor
	 */
	public UpdateIndici() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		updateFirst();
		this.setSize(646, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Update Indici");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getCmbFornitori(), null);
			jContentPane.add(getCmbDocumenti(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	private void caricaDocumenti(JComboBox cmbDocumenti) {
	//		Documento f = new Documento();
	//		String tmpDocumenti[] = null;
	//		String tmpCodici[] = null;
	//		try {
	//			cmbDocumenti.removeAllItems();
	//			cmbDocumenti.addItem("");
	//			String as[] = (String[]) f.allDocumenti();
	//			tmpDocumenti = new String[as.length];
	//			tmpCodici = new String[as.length];
	//			// carichiamo tutti i dati in due array
	//			// da passre al combobox
	//			for (int i = 0; i < as.length; i++) {
	//				String tmp[] = as[i].split("-",2);
	//				tmpDocumenti[i] = tmp[1].trim();
	//				tmpCodici[i] = tmp[0].trim();
	//			}
	//			((IDJComboBox) cmbDocumenti).caricaIDAndOggetti(tmpCodici,
	//					tmpDocumenti);
	//		} catch (SQLException e) {
	//			JOptionPane.showMessageDialog(this,
	//					"Errore caricamento documenti nel combobox", "ERRORE", 0);
	//			e.printStackTrace();
	//		} catch (LunghezzeArrayDiverse e) {
	//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
	//					"ERRORE LUNGHEZZA", 0);
	//			e.printStackTrace();
	//		}
	//		AutoCompletion.enable(cmbDocumenti);
			Documento f = new Documento();
			try {

				String as[] = (String[]) f.allDocumenti();
				// carichiamo tutti i dati in due array
				// da passre al combobox
				((IDJComboBox) cmbDocumenti).caricaNewValueComboBox(as, true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento documenti nel combobox", "ERRORE", 0);
				e.printStackTrace();
			}
			AutoCompletion.enable(cmbDocumenti);
		}

	private void caricaFornitori(JComboBox cmbFornitori) {
	//		Fornitore f = new Fornitore();
	//		String tmpFornitori[] = null;
	//		String tmpCodici[] = null;
	//		try {
	//			cmbFornitori.removeAllItems();
	//			cmbFornitori.addItem("");
	//			String as[] = (String[]) f.allFornitori();
	//			tmpFornitori = new String[as.length];
	//			tmpCodici = new String[as.length];
	//			// carichiamo tutti i dati in due array
	//			// da passre al combobox
	//			for (int i = 0; i < as.length; i++) {
	//				String tmp[] = as[i].split("-",2);
	//				tmpFornitori[i] = tmp[1].trim();
	//				tmpCodici[i] = tmp[0].trim();
	//			}
	//			((IDJComboBox) cmbFornitori).caricaIDAndOggetti(tmpCodici,
	//					tmpFornitori);
	//		} catch (SQLException e) {
	//			JOptionPane.showMessageDialog(this,
	//					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
	//			e.printStackTrace();
	//		} catch (LunghezzeArrayDiverse e) {
	//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
	//					"ERRORE LUNGHEZZA", 0);
	//			e.printStackTrace();
	//		}
	//		AutoCompletion.enable(cmbFornitori);

			Fornitore f = new Fornitore();
			try {

				String as[] = (String[]) f.allFornitori();
				// carichiamo tutti i dati in due array
				// da passre al combobox
				((IDJComboBox) cmbFornitori).caricaNewValueComboBox(as, true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento fornitori nel combobox", "ERRORE", 0);
				e.printStackTrace();
			}
			AutoCompletion.enable(cmbFornitori);
		}

	/**
	 * This method initializes cmbFornitori
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbFornitori() {
		if (cmbFornitori == null) {
			cmbFornitori = new IDJComboBox();
			cmbFornitori.setBounds(new Rectangle(30, 15, 571, 31));
			caricaFornitori(cmbFornitori);
		}
		return cmbFornitori;
	}

	/**
	 * This method initializes cmbDocumenti
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbDocumenti() {
		if (cmbDocumenti == null) {
			cmbDocumenti = new IDJComboBox();
			cmbDocumenti.setBounds(new Rectangle(30, 60, 571, 31));
			caricaDocumenti(cmbDocumenti);
		}
		return cmbDocumenti;
	}

	/**
	 * This method initializes jButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(30, 120, 121, 31));
			jButton.setText("update");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					update();
				}
			});
		}
		return jButton;
	}

	protected void updateFirst(){
		String q1="update fornitori set nome=? where idfornitore=234";
		PreparedStatement pst=DBManager.getIstanceSingleton().getNewPreparedStatement(q1);
		try {
			pst.setString(1, "VOGUE DISTRIBUZIONE SELEZIONATA OGGETTI D'ARTE");
			pst.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void update() {
		
		String query="select idcarico, carichi.idfornitore, fornitori.nome, carichi.iddocumento, documento.tipo from carichi, documento, fornitori where carichi.iddocumento=documento.iddocumento and carichi.idfornitore=fornitori.idfornitore";
		Statement st=DBManager.getIstanceSingleton().getNewStatement();
		ResultSet rs=null;
		PreparedStatement pst=null;
		Connection con=DBManager.getIstanceSingleton().getConnessione();

		try {

			rs=st.executeQuery(query);
			con.setAutoCommit(false);
			pst=con.prepareStatement("update carichi set idfornitore=?, iddocumento=? where idcarico=?",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			int k=0;
			while(rs.next()){
				try{
//					 impostiamo prima il fornitore
					String nFornitore=rs.getString("nome");
					cmbFornitori.setSelectedItem(nFornitore);
					int pos=cmbFornitori.getSelectedIndex();
					pos--;
					cmbFornitori.setSelectedIndex(pos);
					pst.setInt(1, new Integer(cmbFornitori.getIDSelectedItem()).intValue());

//					 impostiamo il codice documento
					String nDocumento=rs.getString("tipo");
					cmbDocumenti.setSelectedItem(nDocumento);
					pos=cmbDocumenti.getSelectedIndex();
					cmbDocumenti.setSelectedIndex(--pos);
					pst.setInt(2, new Integer(cmbDocumenti.getIDSelectedItem()).intValue());

					pst.setInt(3, rs.getInt(1));
					//pst.addBatch();
					pst.execute();
					k++;
					System.out.println(k+" codice :"+rs.getInt(1));
					if(k==413){
						break;
					}
					
				}catch(SQLException ex){
					System.out.println("Errore sul carico con id: "+rs.getInt(1));
					ex.printStackTrace();

				}

			}
			  con.commit();

			  if(rs!=null)
				  rs.close();
			  if(st!=null)
				  st.close();
			  if(pst!=null)
				  pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
