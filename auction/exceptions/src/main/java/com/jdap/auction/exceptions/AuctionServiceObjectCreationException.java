package com.jdap.auction.exceptions;

//import javax.ejb.ApplicationException;
//import com.jdap.auction.common.xml.apiconfiguration.RuntimeFaultMessageType;

public class AuctionServiceObjectCreationException extends AuctionServiceException {
	

	private static final long serialVersionUID = -629385526925985448L;

	public AuctionServiceObjectCreationException (String message) {
		super (message);
		
	}
	
	public AuctionServiceObjectCreationException ( Throwable cause) {
		super(cause);
	}
	
	public AuctionServiceObjectCreationException ( String message, Throwable cause) {
		super(message,cause);
	}

	/*
	public AuctionServiceObjectCreationException (String message, RuntimeFaultMessageType faultinfo) {
		super (message, faultinfo);
		
	}
	
	public AuctionServiceObjectCreationException (RuntimeFaultMessageType faultinfo, Throwable cause) {
		super(faultinfo,cause);
	}
	
	public AuctionServiceObjectCreationException ( String message, RuntimeFaultMessageType faultinfo, Throwable cause) {
		super(message,faultinfo, cause);
	}
	*/
}
