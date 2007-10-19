/**
 * 
 */
package rf.myswing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Hunter
 * 
 */
public class GregorianCalendarFormat extends GregorianCalendar {

	public GregorianCalendarFormat() {
		super();
	}

	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date d = this.getTime();
		return format.format(d);
	}
	
	public String getOra() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date d = this.getTime();
		return format.format(d);
	}

	public String printDataAndOra() {
		return toString()+" / "+getOra();
	}
}
