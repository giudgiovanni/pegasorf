/**
 * 
 */
package rf.utility.gui;

/**
 * @author Hunter
 * 
 */
public class ComboBoxUtil {

	public static int estraiCodice(String string) {
		int index = string.indexOf("-");
		if (index != -1) {
			string = string.substring(0, index - 1);
			return Integer.parseInt(string);
		}
		return 0;

	}

	public ComboBoxUtil() {

	}
}
