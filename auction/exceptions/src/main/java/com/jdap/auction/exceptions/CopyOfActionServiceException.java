package com.jdap.auction.exceptions;

import javax.ejb.ApplicationException;
//import com.jdap.auction.common.xml.fault.ApiFaul
import javax.xml.ws.WebFault;

@WebFault(faultBean="RuntimeFaultMessageType", targetNamespace="http://www.auction.com/api/adf/runtimeFault"/*,
  name="runtimeFaultMessage", it might be useful to create objects with root element and marshal them*/)
@ApplicationException (rollback = true)
public class CopyOfActionServiceException extends AuctionServiceRootException implements PlatformServiceException{

 
	
	private static final long serialVersionUID = 4125932721057656812L;
	
	private static final String header = "";
	
	public CopyOfActionServiceException() {
		super(header);
	}
	
	public CopyOfActionServiceException(String message) {
		super(header + message);
	}
	
	public CopyOfActionServiceException(Throwable cause) {
		super (header + cause);
	}
	
	public CopyOfActionServiceException( String message, Throwable cause) {
		super(header + message, cause);
	}
	/*public CopyOfActionServiceException(String message, RuntimeFaultMessageType faultinfo) {
		super(header + message);
		super.faultInfo=faultinfo;
	}
	
	public CopyOfActionServiceException(RuntimeFaultMessageType faultinfo, Throwable cause) {
		super (header + cause);
		super.faultInfo=faultinfo;
	}
	
	public CopyOfActionServiceException(String message, RuntimeFaultMessageType faultinfo, Throwable cause) {
		super(header + message, cause);
		super.faultInfo=faultinfo;
	}

	public RuntimeFaultMessageType getFaultInfo() {
		return super.faultInfo;
	}

	public void setFaultInfo(RuntimeFaultMessageType faultInfo) {
		super.faultInfo = faultInfo;
	}*/
}
