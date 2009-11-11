/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.utility.db.DBManager;

/**
 * @author Hunter
 * 
 */
public class Giacenze {
	public static double getTotImponibile() throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		double totImp = 0.0;
		Statement st = null;
		ResultSet rs = null;
		String query = "select sum(prezzo_tot) from giacenza_articoli_view";
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		totImp = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();

		return totImp;
	}

	/**
	 * @param dbm
	 * @return
	 * @throws SQLException
	 */
	public static double getTotImposta() throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum((a.prezzo_acquisto / 100 * a.iva)* g.deposito) from giacenza_articoli_view g,articoli a where g.idarticolo=a.idarticolo";
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public Giacenze() {

	}

}
