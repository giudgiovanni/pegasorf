/**
 *
 */

package rf.utility.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hunterbit
 */
public class ConnectionManager {

    // parametri di accesso DB
    private String url;

    private String user;

    private String password;

    private String driverJdbc;

    private Connection con = null;


    // metodo Costruttore
    public ConnectionManager() {
//    	 Carichiamo le configurazioni della connessione
		Properties props = new Properties();
		try {
			// Leggiamo le configurazioni
			props.load(new FileReader("configurazioni.properties"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		// Impostiamo tutti i dati
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
		this.driverJdbc = props.getProperty("driverjdbc");
		this.url = props.getProperty("urldb");
		try {
			// Carichiamo il driver
			Class.forName(driverJdbc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// Effettuiamo la connessione
			con = DriverManager.getConnection(url, this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(url, this.user,
						this.password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ritorna la connessione
		return con;
	}



}
