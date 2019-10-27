package com.salestaxes.exception;

/**
 * Custom exception that must be thrown when an invalid quantity (zero or less) is selected for an item
 * @author fab
 *
 */
public class InvalidItemQuantityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2534908984707821943L;

	public InvalidItemQuantityException(String message){
		super(message);
	}
}
