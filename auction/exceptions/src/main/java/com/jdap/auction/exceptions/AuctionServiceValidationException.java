package com.jdap.auction.exceptions;

import com.jdap.auction.common.xml.fault.ApiFaultType;

public class AuctionServiceValidationException extends AuctionServiceException {
	

	private static final long serialVersionUID = -629385526920985448L;

	public AuctionServiceValidationException (String message) {
		super (message);
		
	}
	
	public AuctionServiceValidationException ( Throwable cause) {
		super(cause);
	}
	
	public AuctionServiceValidationException ( String message, Throwable cause) {
		super(message,cause);
	}

	
	public AuctionServiceValidationException (String message, ApiFaultType faultinfo) {
		super (message, faultinfo);
		
	}
	
	public AuctionServiceValidationException (ApiFaultType faultinfo, Throwable cause) {
		super(faultinfo,cause);
	}
	
	public AuctionServiceValidationException ( String message, ApiFaultType faultinfo, Throwable cause) {
		super(message,faultinfo, cause);
	}
	
	
}
