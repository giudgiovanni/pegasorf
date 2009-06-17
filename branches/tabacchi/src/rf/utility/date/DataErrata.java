/**
 * 
 */

package rf.utility.date;

/**
 * @author hunterbit
 */
public class DataErrata extends Exception {
	@Override
	public String getMessage() {
		return "DATA ERRATA";
	}

	@Override
	public String toString() {

		return getMessage();
	}

}
