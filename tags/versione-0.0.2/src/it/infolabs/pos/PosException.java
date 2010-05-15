/**
 * 
 */
package it.infolabs.pos;

/**
 * @author Admin
 *
 */
public class PosException extends Exception {
	
	
	public PosException(String message){
		super(message);
	}
	
	public PosException(String message, Throwable e){
		super(message,e);
	}

}
