package rf.myswing.util;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import rf.pegaso.db.DBManager;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.UpperAutoCompleteDocument;

public class MyTableCellRendererAlignment extends DefaultTableCellRenderer {

	public static final int CENTRO=CENTER;
	public static final int SINISTRA=LEFT;
	public static final int DESTRA=RIGHT;
	private int allineamento=-1;

	public MyTableCellRendererAlignment(int allineamento){
		if(allineamento!=CENTRO || allineamento!=SINISTRA || allineamento != DESTRA)
				this.allineamento=SINISTRA;
		else this.allineamento=allineamento;
	}
	public MyTableCellRendererAlignment(){
		this.allineamento=SINISTRA;
	}
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		if(column==1 || column ==2){
			setHorizontalAlignment(LEFT);
		}else if(column==3 ||column==4||column==5||column==6){
			setHorizontalTextPosition(CENTER);
		}else setHorizontalAlignment(RIGHT);

		//setHorizontalAlignment(this.allineamento);

		return this;
	}
}
