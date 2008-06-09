/**
 * 
 */
package rf.utility.number;

/**
 * @author Hunter
 * 
 */
public class Arrays {

	// Ricerca lineare di interi
	public static int ricercaLineare(int[] array, int e) {
		boolean trovato = false;
		int pos = 0;
		// ricerchiamo l'elemento
		for (pos = 0; pos < array.length && !trovato; pos++) {
			int k = array[pos];
			if (k == e) {
				trovato = true;
			}
		}// fine for
		if (trovato)
			return pos;
		return -1;
	}

	// ricerca lineare di stringhe
	public static int ricercaLineare(String[] array, String e) {
		boolean trovato = false;
		int pos = 0;
		// ricerchiamo l'elemento
		for (pos = 0; pos < array.length && !trovato; pos++) {
			String k = array[pos];
			if (k.equals(e)) {
				trovato = true;
				// lo abbiamo trovato e ritorniamo l'indice
				return pos;
			}
		}// fine for
		// se siamo qui è perchè non ha trovato 
		// nessun elemento nell'array
		return -1;
	}

	// ricerca lineare di stringhe con possibilità di opzione caseSensitive
	public static int ricercaLineare(String[] array, String e, boolean mod) {
		boolean trovato = false;
		int pos = 0;
		// ricerchiamo l'elemento
		for (pos = 0; pos < array.length && !trovato; pos++) {
			String k = array[pos];
			if (mod) {
				if (k.equals(e)) {
					trovato = true;
				}
			} else {
				if (k.equalsIgnoreCase(e)) {
					trovato = true;
				}
			}

		}// fine for
		if (trovato)
			return pos;
		return -1;
	}

	//copia il contenuto di un array di strighe 
	// in un nuovo array e lo restituisce
	public static String[] copyOf(String[] codId) {
		String tmp[]=new String[codId.length];
		for(int i=0;i<tmp.length;i++){
			tmp[i]=codId[i];
		}
		return tmp;
	}

	public static String[] merge(String[] a1, String[] a2) {
		String tmp[]=new String[a1.length+a2.length];
		//aggiungiamo primo array
		for(int i=0;i<a1.length;i++){
			tmp[i]=a1[i];
		}
		//aggiungiamo secondo array
		int k=0;
		for(int i=a1.length;i<tmp.length;i++){
			tmp[i]=a1[k];
			k++;
		}
		return tmp;
	}

}
