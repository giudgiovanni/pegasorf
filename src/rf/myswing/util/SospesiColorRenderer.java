package rf.myswing.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import rf.pegaso.db.model.CarichiViewModel;

public class SospesiColorRenderer extends DefaultTableCellRenderer {

	private static final Color SELECTED_COLOR = new Color(61, 128, 223);

	// private static final Color ALTERNATE_COLOR = new Color(232,242,254);
	private static final Color RED_COLOR = Color.RED;

	private JLabel testLabel;

	private JTable table;

	private int row;

	private int col;

	private Object value;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		testLabel = new JLabel(value==null ? "" :value.toString());
		this.table = table;
		this.row = row;
		this.col = col;
		this.value = value==null ? "" :value;

		testLabel.setOpaque(true);
		if (isSelected) {
			testLabel.setForeground(Color.WHITE);
			testLabel.setBackground(SELECTED_COLOR);
		} else {
			if (table.getModel() instanceof CarichiViewModel) {
				coloredCarichiViewModel();
			}// qui a cascata inseriamo veri altri tipi di evidenzizione in
				// base alla tbella
		}
		return testLabel;
	}

	private void coloredCarichiViewModel() {
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);

		if (this.col == 6) {
			col = table.getColumn("totale_documento");
			String s = value.toString();
			testLabel = new JLabel(s, SwingConstants.RIGHT);
			col.setPreferredWidth(40);
		} else if(this.col==7){
			col = table.getColumn("sospeso");
			String s = value.toString();
			testLabel = new JLabel(s, SwingConstants.CENTER);
			col.setPreferredWidth(40);
		}

		testLabel.setOpaque(true);
		testLabel.setForeground(table.getForeground());
		if (table.getModel().getValueAt(row, 7).equals("SOSPESO")) {
			testLabel.setBackground(Color.RED);
		} else if (table.getModel().getValueAt(row, 7).equals("PAGATO")) {
			testLabel.setBackground(Color.GREEN);
		} else {
			testLabel.setBackground(table.getBackground());
		}



	}
}
