package com.jdap.auction.exceptions;

import javax.ejb.ApplicationException;

import com.jdap.auction.common.xml.fault.ApiFaultType;


@ApplicationException (rollback = true)
public abstract class AuctionServiceRootException extends  Exception{

	private static final long serialVersionUID = -7532066923031966281L;

	public ApiFaultType faultInfo = new ApiFaultType();
	
	public AuctionServiceRootException (String message) {
		super (message);
	}
	
	public AuctionServiceRootException ( Throwable cause) {
		super(cause);
	}
	
	public AuctionServiceRootException ( String message, Throwable cause) {
		super(message,cause);
	}

   
}
