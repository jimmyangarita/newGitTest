package com.jdap.auction.persistence.exceptions;

import com.jdap.auction.exceptions.AuctionRuntimeException;

public class AuctionPersistenceException extends AuctionRuntimeException{

	private static final long serialVersionUID = 4182702418583182496L;
	
	public AuctionPersistenceException (String message) {
		super(message);
	}
	
	public AuctionPersistenceException (Throwable cause) {
		super(cause);
	}
	
	public AuctionPersistenceException (String message, Throwable cause) {
		super(message, cause);
	}
	
	
	
}
