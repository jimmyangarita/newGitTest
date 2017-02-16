package com.jdap.auction.exceptions;

import javax.ejb.ApplicationException;
import com.jdap.auction.common.xml.fault.ApiFaultType;
import javax.xml.ws.WebFault;

@WebFault(faultBean="ApiFaultType", targetNamespace="http://xmlns.jdap.com/Common/Fault/ApiFault/v2"/*,
  name="runtimeFaultMessage", it might be useful to create objects with root element and marshal them*/)
@ApplicationException (rollback = true)
public class AuctionServiceException extends AuctionServiceRootException implements PlatformServiceException{

 	private static final long serialVersionUID = 4125932721057656812L;
	
	private static final String header = "";
	
	public AuctionServiceException() {
		super(header);
	}
	
	public AuctionServiceException(String message) {
		super(header + message);
	}
	
	public AuctionServiceException(Throwable cause) {
		super (header + cause);
	}
	
	public AuctionServiceException( String message, Throwable cause) {
		super(header + message, cause);
	}
	public AuctionServiceException(String message, ApiFaultType faultinfo) {
		super(header + message);
		super.faultInfo=faultinfo;
	}
	
	public AuctionServiceException(ApiFaultType faultinfo, Throwable cause) {
		super (header + cause);
		super.faultInfo=faultinfo;
	}
	
	public AuctionServiceException(String message, ApiFaultType faultinfo, Throwable cause) {
		super(header + message, cause);
		super.faultInfo=faultinfo;
	}
	
	public ApiFaultType getFaultInfo() {
		return super.faultInfo;
	}

	public void setFaultInfo(ApiFaultType faultInfo) {
		super.faultInfo = faultInfo;
	}
	
	/*public AuctionServiceException(String message, RuntimeFaultMessageType faultinfo) {
		super(header + message);
		//super.faultInfo=faultinfo;
	}
	
	public AuctionServiceException(RuntimeFaultMessageType faultinfo, Throwable cause) {
		super (header + cause);
		//super.faultInfo=faultinfo;
	}
	
	public AuctionServiceException(String message, RuntimeFaultMessageType faultinfo, Throwable cause) {
		super(header + message, cause);
		//super.faultInfo=faultinfo;
	}*/

	/*public RuntimeFaultMessageType getFaultInfo() {
		return super.faultInfo;
	}

	public void setFaultInfo(RuntimeFaultMessageType faultInfo) {
		super.faultInfo = faultInfo;
	}*/
	
 
	
}
