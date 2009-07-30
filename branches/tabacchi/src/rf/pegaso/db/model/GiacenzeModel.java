// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 24/06/2008 20.45.51
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   GiacenzeModel.java

package rf.pegaso.db.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

public class GiacenzeModel extends AbstractTableModel
    implements DBStateChange
{

    private Date data;

	public GiacenzeModel(Date dataGiacenza)
        throws SQLException
    {
    	this.data=dataGiacenza;
        pst = null;
        query = "";
        rs = null;
        rsmd = null;
        this.dbm = DBManager.getIstanceSingleton();
        recuperaDati();
    }


	public void setDataGiacenza(Date dataGiacenza) throws SQLException {
		this.data=dataGiacenza;
		stateChange();
	}

    public int getColumnCount()
    {
        int nColonne = 0;
        try
        {
            nColonne = rsmd.getColumnCount();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return nColonne;
    }

    public String getColumnName(int col)
    {
        String nome = "";
        try
        {
            nome = rsmd.getColumnLabel(col + 1);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return nome;
    }

    public int getRowCount()
    {
        if(rs == null)
            return -1;
        int nRighe = 0;
        try
        {
            rs.last();
            nRighe = rs.getRow();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return nRighe;
    }

    public String getTableName()
    {
        return "clienti";
    }

    public Object getValueAt(int r, int c)
    {
        if(rs == null)
            return Integer.valueOf(-1);
        Object o = null;
        try
        {
        	
            //rs.beforeFirst();
            rs.absolute(r + 1);
            o = rs.getObject(c + 1);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        if(o instanceof Double)
        {
            Double d = (Double)o;
            DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
            numberFormatter.setMaximumFractionDigits(2);
            numberFormatter.setMinimumFractionDigits(2);
            return numberFormatter.format(d);
        } else
        {
            return o;
        }
    }

    public void stateChange()
    {
        try
        {
            recuperaDati();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }

    public void stateChange(DBEvent arg0)
    {
        stateChange();
    }

    private void recuperaDati()
        throws SQLException
    {
    	rs=null;
    	//vecchia query sostituita dalla seguente in quanto permette di vedere la giacenza
    	//ad un dato periodo.
        //query = "select * from giacenza_articoli_view order by codice";
    	query="SELECT c.idarticolo, a.codbarre AS codice, a.descrizione, um.nome AS um, c.sum AS carico, o.sum AS scarico, c.sum - o.sum AS deposito, c.prezzo_acquisto, c.prezzo_acquisto * (c.sum - o.sum)::double precision AS prezzo_tot "+
    	"FROM articoli a JOIN ( (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum, d.prezzo_acquisto FROM articoli a, carichi c, dettaglio_carichi d WHERE d.idcarico = c.idcarico AND a.idarticolo = d.idarticolo and c.data_carico<=? and c.iddocumento <> 0 GROUP BY a.idarticolo, a.codbarre,d.prezzo_acquisto) c "+
    	"LEFT JOIN (SELECT a.idarticolo, a.codbarre, sum(d.qta) AS sum FROM articoli a, ordini c, dettaglio_ordini d WHERE d.idordine = c.idordine AND a.idarticolo = d.idarticolo and c.data_ordine<=? GROUP BY a.idarticolo, a.codbarre) o ON c.idarticolo = o.idarticolo) "+
    	"ON a.idarticolo = c.idarticolo JOIN um ON a.um = um.idum WHERE (c.sum - o.sum) > 0::numeric order by codice;";
        pst = dbm.getNewPreparedStatement(query);
        pst.setDate(1, data);
        pst.setDate(2, data);
        rs = pst.executeQuery();
        rsmd = rs.getMetaData();
    }

    private DBManager dbm;
    private PreparedStatement pst;
    private String query;
    private ResultSet rs;
    private ResultSetMetaData rsmd;

	@Override
	public String getNomeTabella() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void rowStateChange(RowEvent re) {
		// TODO Auto-generated method stub
		
	}
}