package rf.myswing.util;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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
