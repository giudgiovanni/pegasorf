/**
 * 
 */
package rf.utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author hunterbit
 * 
 */
public class DateManager {
	/**
	 * @param date
	 * @return
	 */
	public static long addDay(Date date) {
		Date nuova = new Date(date.getTime() + 86400000);
		return nuova.getTime();
	}

	/**
	 * @param dal
	 * @return
	 */
	public static java.sql.Date convertDateToSqlDate(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String text = sdf.format(data);
		java.sql.Date nuova = null;
		try {
			Date d = sdf.parse(text);
			nuova = new java.sql.Date(d.getTime());
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return nuova;
	}

	/**
	 * @param string
	 * @param gg
	 * @return
	 */
	public static String convertDateToString(String formato, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(data);

	}

	public static Date convertStringToDate(String formato, String data) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Date d = null;
		try {
			d = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * @param date
	 * @return
	 */
	public static long getDateInLong(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat();
		String text = sdf.format(date);
		Date nuova = null;
		try {
			nuova = sdf.parse(text);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return nuova.getTime();
	}

	/**
	 * @param diff
	 * @return
	 */
	public static int getDay(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String text = sdf.format(data);
		return Integer.parseInt(text);
	}

	public static int getGiorniDiDifferenza(Date dal, Date al) {
		long d = dal.getTime();
		long a = al.getTime();
		long diff = a - d;
		// 1 giorno medio = 1000*60*60*24 ms = 86400000 ms
		double giorniFraDueDate = Math.round(diff / 86400000.0);
		return (int) giorniFraDueDate;
	}

	/**
	 * Questo metodo non fa altro che modificare una data eliminando appunto la
	 * parte riguardande l'orario che potrebbe non interessare nel caso di
	 * confronti fra date dove la parte che interessa è solo la data vera e
	 * propria. Bisogna passare la data da modificare ed inoltre il formato che
	 * potrebbe per esempio essere dd/MM/yyyy o altri.
	 * 
	 * @param daModificare
	 *            è appunto la data da modificare
	 * @param formato
	 *            è la stringa che identifica il formato
	 * @return una nuova data formattata.
	 */
	public static Date onlyDate(Date daModificare, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String text = sdf.format(daModificare);
		Date nuova = null;
		try {
			nuova = sdf.parse(text);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return nuova;
	}

	/**
	 * Questo metodo non fa altro che modificare una data eliminando appunto la
	 * parte riguardande l'orario che potrebbe non interessare nel caso di
	 * confronti fra date dove la parte che interessa è solo la data vera e
	 * propria. Bisogna passare la data da modificare ed il formato che potrebbe
	 * per esempio essere dd/MM/yyyy o altri ed infine si passa un oggetto
	 * Locale che rappresenta la lingua o lo stato (per esempio Locale.ITALY).
	 * 
	 * @param daModificare
	 *            è appunto la data da modificare
	 * @param formato
	 *            è la stringa che identifica il formato
	 * @param locale
	 *            è la rappresentazione della nazione
	 * @return una nuova data formattata.
	 */
	public static Date onlyDate(Date daModificare, String formato, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato, locale);
		String text = sdf.format(daModificare);
		Date nuova = null;
		try {
			nuova = sdf.parse(text);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return nuova;
	}

	public static long removeDay(Date date) {
		Date nuova = new Date(date.getTime() - 86400000);
		return nuova.getTime();
	}

	public DateManager() {

	}

}
