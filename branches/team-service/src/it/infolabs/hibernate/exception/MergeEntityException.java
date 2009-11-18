package it.infolabs.hibernate.exception;

public class MergeEntityException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public void printStackTrace() {

		super.printStackTrace();
	}

	@Override
	public String toString() {
		return "Si e' verificato un errore durante il salvataggio dell'elemento.";
	}

}

