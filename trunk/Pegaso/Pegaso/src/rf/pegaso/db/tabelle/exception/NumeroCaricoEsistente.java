package rf.pegaso.db.tabelle.exception;

public class NumeroCaricoEsistente extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public String toString() {
		return "Numero documento carico già esistente";
	}
}
