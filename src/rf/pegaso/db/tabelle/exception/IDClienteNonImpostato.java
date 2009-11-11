
/**
 * 
 */

package rf.pegaso.db.tabelle.exception;

/**
 * @author hunterbit
 */
public class IDClienteNonImpostato extends Exception {

    /**
     * 
     */
    public IDClienteNonImpostato() {
	super();

    }

    @Override
    public String getMessage() {

	return toString();
    }

    @Override
    public String toString() {
	return "ID cliente non impostato";
    }

}
