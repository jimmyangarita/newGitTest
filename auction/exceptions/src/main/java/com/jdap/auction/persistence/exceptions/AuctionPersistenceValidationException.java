package com.jdap.auction.persistence.exceptions;

import com.jdap.auction.common.xml.fault.ApiFaultType;
import com.jdap.auction.exceptions.AuctionServiceException;

public class AuctionPersistenceValidationException extends AuctionServiceException {
	

	private static final long serialVersionUID = -629385526920985448L;

	public AuctionPersistenceValidationException (String message) {
		super (message);
		
	}
	
	public AuctionPersistenceValidationException ( Throwable cause) {
		super(cause);
	}
	
	public AuctionPersistenceValidationException ( String message, Throwable cause) {
		super(message,cause);
	}

	
	public AuctionPersistenceValidationException (String message, ApiFaultType faultinfo) {
		super (message, faultinfo);
		
	}
	
	public AuctionPersistenceValidationException (ApiFaultType faultinfo, Throwable cause) {
		super(faultinfo,cause);
	}
	
	public AuctionPersistenceValidationException ( String message, ApiFaultType faultinfo, Throwable cause) {
		super(message,faultinfo, cause);
	}
	
	
}
