/**
 *
 */
package rf.utility.db.eccezzioni;

/**
 * @author Hunter
 *
 */
public class IDNonValido extends Exception {

	@Override
	public String getMessage() {

		return "Id non valido";
	}

	@Override
	public String toString() {

		return "Id non valido";
	}

}
