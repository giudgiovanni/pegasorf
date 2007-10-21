package rf.myswing.util;

import java.awt.Component;
import java.lang.reflect.Type;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MyTableCellRendererCentral extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		setHorizontalAlignment(CENTER);

		return this;
	}
}
