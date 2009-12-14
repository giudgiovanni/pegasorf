package rf.utility.text;

/**
 * @author hunterbit
 */
public class UtilityCaratteri {
	public static String addCaractersAfter(String sDaAggiungere,
			String sDopoQuale, String oldString) {
		String s = "";
		s = oldString.replace(sDopoQuale, sDaAggiungere);
		return s;
	}

	public static boolean contieneCaratteri(String contiene) {
		return contiene.contains(contiene);
	}

	public UtilityCaratteri() {

	}

}
