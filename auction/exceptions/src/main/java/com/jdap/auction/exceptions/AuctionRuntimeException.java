package com.jdap.auction.exceptions;

import javax.ejb.ApplicationException;


@ApplicationException (rollback = true)
public class AuctionRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1597042526102859725L;

	public  AuctionRuntimeException () {
		super ();
	}
	
	public  AuctionRuntimeException (String message) {
		super (message);
	}
	
	public  AuctionRuntimeException (Throwable cause) {
		super (cause);
	}
	
	public  AuctionRuntimeException (String message, Throwable cause) {
		super (message, cause);
	}
	
}
