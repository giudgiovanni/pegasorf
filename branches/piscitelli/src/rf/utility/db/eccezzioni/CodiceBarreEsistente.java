package rf.utility.db.eccezzioni;

public class CodiceBarreEsistente extends Exception{

	@Override
	public String getMessage() {

		return "Codice a Barre presente in magazzino.";
	}

	@Override
	public String toString() {

		return "Codice a Barre presente in magazzino.";
	}
}
