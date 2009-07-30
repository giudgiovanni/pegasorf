// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 24/06/2008 20.47.11
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Giacenze.java

package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.utility.db.DBManager;

public class Giacenze
{

    public static double getTotImponibile()
        throws SQLException
    {
        DBManager dbm = DBManager.getIstanceSingleton();
        double totImp = 0.0D;
        Statement st = null;
        ResultSet rs = null;
        String query = "select sum(prezzo_tot) from giacenza_articoli_view";
        st = dbm.getNewStatement();
        rs = st.executeQuery(query);
        rs.next();
        totImp = rs.getDouble(1);
        if(st != null)
            st.close();
        if(rs != null)
            rs.close();
        return totImp;
    }
    
    
    public static double getTotImponibileDaData(Date data) throws SQLException {
		DBManager dbm = DBManager.getIstanceSingleton();
		double totImp = 0.0D;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT  sum(c.prezzo_acquisto * (c.sum - o.sum))  AS prezzo_tot " +
				"FROM articoli a JOIN ( (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum,d.prezzo_acquisto FROM articoli a, carichi c, dettaglio_carichi d " +
				"WHERE d.idcarico = c.idcarico AND a.idarticolo = d.idarticolo and c.data_carico<=? GROUP BY a.idarticolo, a.codbarre,d.prezzo_acquisto) c " +
				"LEFT JOIN (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articoli a, ordini c, dettaglio_ordini d " +
				"WHERE d.idordine = c.idordine AND a.idarticolo = d.idarticolo and c.data_ordine<=? GROUP BY a.idarticolo, a.codbarre) o ON c.idarticolo = o.idarticolo) ON a.idarticolo = c.idarticolo " +
				"JOIN um ON a.um = um.idum WHERE (c.sum - o.sum) > 0;";
		st = dbm.getNewPreparedStatement(query);
		st.setString(1,data.toString());
		st.setString(2,data.toString());
		rs = st.executeQuery();
		rs.next();
		totImp = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return totImp;
	}

    public static double getTotImposta()
        throws SQLException
    {
        DBManager dbm = DBManager.getIstanceSingleton();
        ResultSet rs = null;
        Statement st = dbm.getNewStatement();
        String query = "select sum((a.prezzo_acquisto / 100 * a.iva)* g.deposito) from giacenza_articoli_view g,articoli a where g.idarticolo=a.idarticolo";
        rs = st.executeQuery(query);
        rs.next();
        double tot = rs.getDouble(1);
        if(st != null)
            st.close();
        if(rs != null)
            rs.close();
        return tot;
    }
    
    public static double getTotImpostaDaData(Date data) throws SQLException {
		DBManager dbm = DBManager.getIstanceSingleton();
		ResultSet rs = null;
		PreparedStatement st = null;
		String query = "SELECT  sum((c.prezzo_acquisto/100*a.iva)*(c.sum - o.sum)) as imposta " +
				"FROM articoli a JOIN ( (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum,d.prezzo_acquisto FROM articoli a, carichi c, dettaglio_carichi d " +
				"WHERE d.idcarico = c.idcarico AND a.idarticolo = d.idarticolo and c.data_carico<=? GROUP BY a.idarticolo, a.codbarre,d.prezzo_acquisto) c " +
				"LEFT JOIN (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articoli a, ordini c, dettaglio_ordini d WHERE d.idordine = c.idordine " +
				"AND a.idarticolo = d.idarticolo and c.data_ordine<=? GROUP BY a.idarticolo, a.codbarre) o ON c.idarticolo = o.idarticolo) ON a.idarticolo = c.idarticolo " +
				"JOIN um ON a.um = um.idum WHERE (c.sum - o.sum) > 0;";
		st=dbm.getNewPreparedStatement(query);
		st.setString(1,data.toString());
		st.setString(2, data.toString());
		rs = st.executeQuery();
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

    public Giacenze()
    {
    }
}