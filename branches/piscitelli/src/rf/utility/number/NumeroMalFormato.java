/**
 * 
 */

package rf.utility.number;

/**
 * @author hunterbit
 */
public class NumeroMalFormato extends NumberFormatException {

	@Override
	public String getMessage() {
		return "NUMERO MAL FORMATO";

	}

	@Override
	public String toString() {
		return getMessage();
	}

}
