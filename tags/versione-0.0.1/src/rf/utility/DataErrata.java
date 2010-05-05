/**
 * 
 */

package rf.utility;

/**
 * @author hunterbit
 */
public class DataErrata extends Exception {
    public String getMessage() {
	return "DATA ERRATA";
    }

    @Override
    public String toString() {

	return getMessage();
    }

}
