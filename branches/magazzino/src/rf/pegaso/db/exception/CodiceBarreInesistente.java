/**
 * 
 */
package rf.pegaso.db.exception;

/**
 * @author Hunter
 * 
 */
public class CodiceBarreInesistente extends Exception {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public void printStackTrace() {

		super.printStackTrace();
	}

	@Override
	public String toString() {
		return "Codice a barre non trovato";
	}

}
