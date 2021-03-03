package com.anz.accounts.exception;
/**
 * This exception object will be used to show error when a requested record is not found.
 * @author Vasavi
 *
 */
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException() {
		super();
	}
}

