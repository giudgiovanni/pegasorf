package rf.utility.gui.text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.autocomplete.AbstractAutoCompleteAdaptor;

import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author hunterbit
 */
public class AutoCompleteTextComponent extends AbstractAutoCompleteAdaptor
	implements DBStateChange {
    private DBManager dbm = null;

    private ResultSet rs = null;

    private JTextComponent component;

    private LinkedList<String> valori;

    private TreeSet<String> set = null;

    private int selezionato = -1;

    private String tabella;

    private String colonna;

    /**
     * Il metodo costruttore è astratto e deve essere implementato in modo che
     * in fase di inizializzazione recupera i dati in base al tipo di tabella di
     * testo
     */
    public AutoCompleteTextComponent(JTextComponent textComponent,
	    DBManager dbm, String tabella, String colonna) {
	this.component = textComponent;
	this.dbm = dbm;
	this.tabella = tabella;
	this.colonna = colonna;
	inizialize(tabella);

    }

    public AutoCompleteTextComponent() {

    }

    public void inizialize(String tabella) {

	this.selezionato = -1;
	valori = new LinkedList<String>();
	set = new TreeSet<String>();
	String query = "";
	query = "SELECT * FROM " + tabella;
	rs = dbm.executeQuery(query);
	try {
	    while (rs.next()) {
		Object o = rs.getObject(colonna);
		if (o instanceof String)
		    set.add((String) o);
		else
		    set.add(o.toString());
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	valori = new LinkedList<String>(set);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jdesktop.swingx.autocomplete.AbstractComponentAdaptor#getSelectedItem()
     */
    @Override
    public Object getSelectedItem() {
	if (selezionato != -1)
	    return valori.get(selezionato);
	return "";

    }

    /*
     * (non-Javadoc)
     *
     * @see org.jdesktop.swingx.autocomplete.AbstractComponentAdaptor#setSelectedItem(java.lang.Object)
     */
    @Override
    public void setSelectedItem(Object o) {
	String s = (String) o;
	ListIterator<String> it = valori.listIterator();
	selezionato = -1;
	boolean fine = false;
	while (it.hasNext() && !fine) {
	    selezionato++;
	    String tmp = it.next();
	    if (s.equalsIgnoreCase(tmp))
		fine = true;
	}

    }

    /*
     * (non-Javadoc)
     *
     * @see org.jdesktop.swingx.autocomplete.AbstractComponentAdaptor#getItemCount()
     */
    @Override
    public int getItemCount() {
	return valori.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jdesktop.swingx.autocomplete.AbstractComponentAdaptor#getItem(int)
     */
    @Override
    public Object getItem(int pos) {
	return valori.get(pos);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jdesktop.swingx.autocomplete.AbstractComponentAdaptor#getTextComponent()
     */
    @Override
    public JTextComponent getTextComponent() {
	return this.component;
    }

    /*
     * (non-Javadoc)
     *
     * @see rf.hotel.db.DBStateChange#stateChange()
     */
    public void stateChange() {
	inizialize(this.tabella);

    }

    /*
     * (non-Javadoc)
     *
     * @see rf.utility.db.DBStateChange#getTableName()
     */
    public String getTableName() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see rf.hotel.db.DBStateChange#getNomeTabella()
     */
    public String getNomeTabella() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see rf.hotel.db.DBStateChange#rowStateChange(rf.hotel.db.RowEvent)
     */
    public void rowStateChange(RowEvent re) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see rf.hotel.db.DBStateChange#stateChange(rf.hotel.db.DBEvent)
     */
    public void stateChange(DBEvent dbe) {
	// TODO Auto-generated method stub

    }

}
