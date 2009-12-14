package it.infolabs.hibernate.exception;

public class FindByNotFoundException extends Exception {
	
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
		return "La ricerca non ha restituito l'elemento cercato.";
	}

}
