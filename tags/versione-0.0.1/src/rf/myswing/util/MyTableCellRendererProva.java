package rf.myswing.util;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import rf.utility.db.DBManager;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.UpperAutoCompleteDocument;

public class MyTableCellRendererProva extends DefaultTableCellRenderer {

//	public static final int CENTRO=CENTER;
//	public static final int SINISTRA=LEFT;
//	public static final int DESTRA=RIGHT;
//	private int allineamento=-1;

//	public MyTableCellRendererProva(int allineamento){
//		if(allineamento!=CENTRO || allineamento!=SINISTRA || allineamento != DESTRA)
//				this.allineamento=SINISTRA;
//		else this.allineamento=allineamento;
//	}
	public MyTableCellRendererProva(){
//		this.allineamento=SINISTRA;
	}
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		//super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
			//	row, column);

		if(column == 1 ){
			JTextField txtCodice = new JTextField();
			DBManager dbm = DBManager.getIstanceSingleton();
			AutoCompleteTextComponent complete = new AutoCompleteTextComponent(
					txtCodice, dbm, "articolo", "codbarre");
			dbm.addDBStateChange(complete);

			txtCodice.setDocument(new UpperAutoCompleteDocument(complete,
					true));
			txtCodice.setBounds(new Rectangle(15, 80, 140, 24)); // Generated
//			txtCodice.addFocusListener(new java.awt.event.FocusAdapter() {
//				@Override
//				public void focusLost(java.awt.event.FocusEvent e) {
//					caricaArticoloByCodBarre(txtCodice.getText());
//
//				}
//			});
//			txtCodice.addKeyListener(new java.awt.event.KeyAdapter() {
//				@Override
//				public void keyPressed(java.awt.event.KeyEvent e) {
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						caricaArticoloByCodBarre(txtCodice.getText());
//							//inserisci();
//					}
//				}
//			});
			return txtCodice;
			//setHorizontalAlignment(LEFT);
		}else if(column==3 ||column==4||column==5||column==6){
			setHorizontalTextPosition(CENTER);
		}else setHorizontalAlignment(RIGHT);

		//setHorizontalAlignment(this.allineamento);

		return this;
	}
}
