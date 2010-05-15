/**
 * 
 */
package rf.pegaso.db.exception;

/**
 * @author Hunter
 * 
 */
public class ResultSetVuoto extends Exception {
	public ResultSetVuoto() {
		super();
	}

	@Override
	public String getMessage() {
		return "Informazioni non presenti nel ResultSet";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMessage();
	}

}
