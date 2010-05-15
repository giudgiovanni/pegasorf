/**
 * 
 */
package rf.pegaso.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import rf.utility.system.SystemPrintStream;

/**
 * @author Hunter
 * 
 */
public class StartPegasoGrecoMobili {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Carichiamo le configurazioni della connessione
		Properties props = new Properties();
		try {
			// Leggiamo le configurazioni
			props.load(new FileReader("configurazioni.properties"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		// se viene impostato su yes si effettua il log
		// su un file di testo
		String log = props.getProperty("log", "yes");
		if (log.equalsIgnoreCase("yes")) {
			SystemPrintStream.setFileName(System.getProperty("user.dir")
					+ File.separator + "log.txt");
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				InitialGUI gui = new InitialGUI();
				gui.setVisible(true);
			}
		});

	}

}
