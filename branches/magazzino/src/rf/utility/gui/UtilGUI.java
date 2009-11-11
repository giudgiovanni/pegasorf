/**
 * 
 */
package rf.utility.gui;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * @author Hunter
 * 
 */
public class UtilGUI {

	public static void centraDialog(JDialog dialog) {
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - dialog
				.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - dialog
				.getHeight()) / 2;
		dialog.setLocation(x, y);
	}

	public static void centraFrame(JFrame frame) {

		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame
				.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame
				.getHeight()) / 2;
		frame.setLocation(x, y);

	}

}
