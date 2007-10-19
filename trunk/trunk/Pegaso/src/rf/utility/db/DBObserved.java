/**
 * 
 */
package rf.utility.db;

/**
 * @author Hunter
 * 
 */
public interface DBObserved {
	// aggiunta e rimozione dati
	public void addDBStateChange(DBStateChange dbChange);

	public void notifyDBStateChange();

	// metodi per la notifica
	public void notifyDBStateChange(DBEvent dbe);

	public void removeDBStateChange(DBStateChange dbChange);

}
