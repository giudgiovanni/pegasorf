package rf.pegaso.db.tabelle.exception;

public class NumeroOrdineEsistente extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public String toString() {
		return "Numero documento ordine gi\u00E0 esistente";
	}


}
