package com.jdap.auction.exceptions;

public class AuctionServicePersistenceException extends
		AuctionServiceRootException {


	private static final long serialVersionUID = -1514828013325917503L;

	public AuctionServicePersistenceException(String message) {
		super(message);
	}
	
	public AuctionServicePersistenceException (Throwable cause) {
		super(cause);
	}
	
	public AuctionServicePersistenceException(String message, Throwable cause) {
		super (message,cause);
	}



}
