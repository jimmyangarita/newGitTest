package com.jdap.auction.service;

//~--- non-JDK imports ---------------------------------------------------------

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.Local;
import javax.xml.datatype.XMLGregorianCalendar;

import com.jdap.auction.common.xml.apiconfiguration.RuntimeFaultMessageType;
import com.jdap.auction.common.xml.fault.ApiFaultType;
import com.jdap.auction.exceptions.AuctionServiceException;

/**
 * Interface description
 *
 *
 * @version        9.0, 2012.November.08 02:25 PM
 * @author         jdap Corporation    
 */
@Local
public interface BaseUtilLocal
{
    /**
     * Method description
     *
     */
    public void baseUtil();
    
    public Timestamp getCurrentTimeStamp();
    
    public XMLGregorianCalendar getXmlCurrentime() throws AuctionServiceException;
    
	public XMLGregorianCalendar converToXmlCurrentime(Date date) throws AuctionServiceException;
    
    public Timestamp getCurretTimestampPlusDays(int numberDays);
    
    public RuntimeFaultMessageType buildFaultMsg(String code, String summary, String detailMsg, 
    		String coreFaul, String originalPayload);
    
    public ApiFaultType buildApiExceptionMsg(String serviceId, String code, String summary, String detailMsg, 
    		String coreFaul, String originalPayload) throws AuctionServiceException;
    
    public boolean textHasContent(String aText);
}
