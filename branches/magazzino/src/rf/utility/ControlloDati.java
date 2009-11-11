package rf.utility;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControlloDati {

	static public double arrotondaPrezzo(double prezzo) {
		double p = Math.rint(prezzo * 100) / 100;
		return p;
	}

	static public void attenzioneVisualizzaTabellaPrima(JFrame frame_parente) {
		Icon iconaPass = new ImageIcon("resource/tabellaNonVisualizzata.jpg");
		JOptionPane.showMessageDialog(frame_parente, iconaPass);
	}

	/**
	 * metodo per il controllo dei dati obbligatori da inserire
	 * 
	 * @param Genere
	 * @param Titolo
	 * @param ISBN
	 * @param frame
	 * @return vero se icampi sono vuoti false altrimenti
	 */
	static public boolean campiVuoti(String Genere, String Titolo, String ISBN,
			JFrame frame) {

		Icon icona2 = new ImageIcon("resource/campiMancanti.jpg");
		JLabel etichetta2 = new JLabel(icona2);
		if ((Genere.equals("")) || (Titolo.equals("")) || ((ISBN.equals("")))) {
			JOptionPane.showMessageDialog(frame, etichetta2);
			return true;
		}
		return false;
	} // fine metodo

	static public int confrontaDate(Date data1, Date data2) {
		GregorianCalendar gc = new GregorianCalendar();
		int a1, m1, g1, a2, m2, g2;
		gc.setTime(data1);
		a1 = gc.get(Calendar.YEAR);
		m1 = gc.get(Calendar.MONTH);
		g1 = gc.get(Calendar.DAY_OF_MONTH);
		gc.setTime(data2);
		a2 = gc.get(Calendar.YEAR);
		m2 = gc.get(Calendar.MONTH);
		g2 = gc.get(Calendar.DAY_OF_MONTH);
		if (a1 == a2) {
			if (m1 == m2) {
				return (g1 == g2) ? 0 : (g1 < g2) ? -1 : 1;

			} else
				return (m1 < m2) ? -1 : 1;
		} else if (a1 < a2) {
			return -1;
		}
		return 1;
	}

	static public String convertDataToString(Date date) {
		String s = "";
		Date d = date;
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		int g = -1, m = -1, a = -1;
		g = c.get(Calendar.DAY_OF_MONTH);
		m = (c.get(Calendar.MONTH)) + 1;
		a = c.get(Calendar.YEAR);
		String g1 = controlloCifre(new Integer(g).toString());
		String m1 = controlloCifre(new Integer(m).toString());
		s += a + "-" + m1 + "-" + g1;
		// s+=g1+"/"+m1+"/"+a;
		return s;
	}

	static public String convertDataToStringItaly(Date date) {
		String s = "";
		Date d = date;
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		int g = -1, m = -1, a = -1;
		g = c.get(Calendar.DAY_OF_MONTH);
		m = (c.get(Calendar.MONTH)) + 1;
		a = c.get(Calendar.YEAR);
		String g1 = controlloCifre(new Integer(g).toString());
		String m1 = controlloCifre(new Integer(m).toString());
		s += g1 + "/" + m1 + "/" + a;
		return s;
	}

	public static String convertDoubleToPrezzo(double prezzo) {

		/*
		 * String p = new Double(prezzo).toString(); p = sostituisciPunto(p);
		 * String invertita = ""; for (int i = p.length() - 1; i >= 0; i--) {
		 * invertita = invertita + p.charAt(i); } int virgola =
		 * invertita.indexOf(","); String nonDecimale =
		 * invertita.substring(virgola + 1); p = invertita.substring(0,
		 * virgola); p = p + ","; for (int i = 0; i < nonDecimale.length(); i++) {
		 * p = p + nonDecimale.charAt(i); } invertita = ""; for (int i =
		 * p.length() - 1; i >= 0; i--) { invertita = invertita + p.charAt(i); }
		 * return
		 * ControlloDati.smorzaPrezzo(ControlloDati.filtroPrezzo(invertita));
		 */

		String format = "#,##0.00";
		DecimalFormat df = new DecimalFormat(format);
		return df.format(prezzo);

	}

	public static double convertPrezzoToDouble(String prezzo)
			throws NumberFormatException, ParseException {
		/*
		 * if(prezzo==null || prezzo.equalsIgnoreCase("")) return 0.0;
		 * StringBuffer nuovoPrezzo = null; String[] split = prezzo.split(".");
		 * if (split.length > 0) { for (int i = 0; i < split.length; i++) {
		 * nuovoPrezzo.append(split[i]); }
		 * 
		 * String tmp1 = prezzo.substring(0, punto); String tmp2 =
		 * prezzo.substring(punto + 1); nuovoPrezzo = tmp1 +","+ tmp2; } else
		 * nuovoPrezzo = new StringBuffer(prezzo);
		 * 
		 * return new
		 * Double(ControlloDati.sostituisciVirgola(nuovoPrezzo.toString())).doubleValue();
		 */
		prezzo = filtroPrezzo(prezzo);

		String format = "#,##0.00";
		DecimalFormat df = new DecimalFormat(format);
		double x = 0.0;

		Number numero = df.parse(prezzo);
		x = numero.doubleValue();

		return x;
	}

	static public Date convertStringToData(String s) {
		StringTokenizer st = new StringTokenizer(s, "-", false);

		int a = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		Calendar c = new GregorianCalendar(a, m - 1, g);
		Date d = c.getTime();
		return d;

	}

	/**
	 * Il metodo statico controlla la validità della data immessa passata
	 * sottoforma di stringa nel seguente formato GG/MM/AAAA
	 */
	public static boolean dataCorretta(String dataDaControllare) {
		if (dataDaControllare.equals("")) {
			return true;
		}
		StringTokenizer st = new StringTokenizer(dataDaControllare, "/");
		if (st.countTokens() != 3) {

			return false;
		}
		String giorno = st.nextToken();
		if (giorno.length() != 2) {

			return false;
		} else if (!isNumero(giorno)) {

			return false;
		}
		int gg = Integer.parseInt(giorno);
		String mese = st.nextToken();
		if (mese.length() != 2) {

			return false;
		} else if (!isNumero(mese)) {

			return false;
		}
		int mm = Integer.parseInt(mese);
		String anno = st.nextToken();
		if (anno.length() != 4) {

			return false;
		} else if (!isNumero(anno)) {

			return false;
		}
		int aa = Integer.parseInt(anno);
		boolean corretta = dataCorretta(gg, mm, aa);
		if (corretta) {
			return corretta;
		} else {

			return false;
		}

	}

	/**
	 * @param text
	 * @return
	 */
	public static String eliminaPunti(String prezzo) {
		StringBuffer buff = new StringBuffer();
		StringTokenizer token = new StringTokenizer(prezzo, ".", false);
		int index = prezzo.indexOf(".");
		if (index > 0) {
			while (token.hasMoreTokens()) {
				buff.append(token.nextToken());

			}
		} else {
			buff = new StringBuffer(prezzo);
		}
		return buff.toString();
	}
	
	
	/**
	 * @param text
	 * @return
	 */
	public static String eliminaVirgole(String prezzo) {
		StringBuffer buff = new StringBuffer();
		StringTokenizer token = new StringTokenizer(prezzo, ",", false);
		int index = prezzo.indexOf(",");
		if (index > 0) {
			while (token.hasMoreTokens()) {
				buff.append(token.nextToken());

			}
		} else {
			buff = new StringBuffer(prezzo);
		}
		return buff.toString();
	}

	public static String filtroPrezzo(String prezzo) {
		String pCorretto = prezzo;
		StringTokenizer token = new StringTokenizer(prezzo, ",.", true);
		int nToken = token.countTokens();
		if (nToken == 1) {
			pCorretto += ",00";
		}
		if (nToken == 2) {
			token.nextToken();
			String controllo = token.nextToken();
			if ((controllo.length() == 1)) {
				pCorretto += "00";
			}
		}
		if (nToken == 3) {
			token.nextToken();
			token.nextToken();
			String controllo = token.nextToken();
			if ((controllo.length() == 1)) {
				pCorretto += "0";
			}
		}
		return ControlloDati.sostituisciPunto(pCorretto);
	}

	static public String formatEuro(String euro) throws NumberFormatException,
			ParseException {
		double x = ControlloDati.convertPrezzoToDouble(euro);
		String xx = ControlloDati.convertDoubleToPrezzo(x);
		return xx;
	}

	/**
	 * metodo privato di supporto per il controllo della data se è un numero o
	 * una stringa alfanumerica
	 * 
	 * @param numero
	 * @return
	 */
	public static boolean isNumero(String numero) {
		if (numero.equalsIgnoreCase(""))
			return true;
		for (int i = 0; i < numero.length(); i++) {
			if (numero.charAt(i) < '0' || numero.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	public static boolean maxLengthNumber(int maxLengthNumber, String number) {
		int max = maxLengthNumber;
		boolean isNumber = ControlloDati.isNumero(number);
		if (!isNumber)
			return false;
		if (number.length() > max)
			return false;
		return true;
	}

	/**
	 * Metodo statico per il controllo del prezzo con il terzo parametro il
	 * campo testo che si vuole azzerare nel caso di errori
	 * 
	 * @param prezzo
	 * @param frame
	 * @param campoDaAzzerare
	 * @return
	 */
	static public boolean prezzoCorretto(String prezzo) {

		StringTokenizer token = new StringTokenizer(prezzo, ",.", true);
		int nToken = token.countTokens();
		if (nToken > 3 || nToken == 2) {
			return false;
		}
		if (nToken == 1) {
			String numero = token.nextToken();
			return (!isNumero(numero)) ? false : true;
		}
		while (token.hasMoreTokens()) {
			{
				String controllo = token.nextToken();
				for (int i = 0; i <= controllo.length() - 1; i++) {
					char c = controllo.charAt(i);
					if ((c < '0') || (c > '9')) {
						return false;
					}
				} // fine for interno ciclo di while
			}
			{
				String controllo = token.nextToken();
				if (!(',' == controllo.charAt(0))
						&& !('.' == controllo.charAt(0))) {
					return false;
				}
			} // secondo controllo
			{
				String controllo = token.nextToken();
				if (controllo.length() > 2) {
					return false;
				}
				if (controllo.length() < 2) {
					controllo = controllo + "0";
				}
				for (int i = 0; i <= controllo.length() - 1; i++) {
					char c = controllo.charAt(i);
					if ((c < '0') || (c > '9')) {
						return false;
					}
				} // fine secondo for interno all'else e al while
			} // fine else interno al while
		} // fine while ciclo di controllo prezzo
		return true;
	} // fine Metodo controlloPrezzo

	public static String smorzaPrezzo(String prezzo) {
		int virgola = prezzo.indexOf(',');
		return prezzo.substring(0, virgola + 3);
	}

	public static String sostituisciPunto(String daModificare) {
		return daModificare.replace('.', ',');
	}
	
	public static String sostituisciVirgola(String daModificare) {
		return daModificare.replace(',', '.');
	}

	public static boolean superataDimNumero(String numero, int dim) {

		return (numero.length() > dim);
	}

	static private String controlloCifre(String s) {
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * metodo per il controllo dell'esattezza della data
	 * 
	 * @param g
	 * @param m
	 * @param a
	 * @return
	 */
	private static boolean dataCorretta(int g, int m, int a) {
		if (g < 1 || g > 31) {
			return false;
		}
		if (m < 1 || m > 12) {
			return false;
		}
		if (a < Short.MIN_VALUE || a > Short.MAX_VALUE) {
			return false;
		}
		if (g == 31 && (m == 4 || m == 6 || m == 9 || m == 11)) {
			return false;
		}
		if (m == 2) {
			if (g > 29 || g == 29 && a % 4 != 0) {
				return false;
			}
			if (g == 29 && a % 100 == 0 && (a / 100) % 4 != 0) {
				return false;
			}
		}
		return true;
	}

	public ControlloDati() {
	}

}
