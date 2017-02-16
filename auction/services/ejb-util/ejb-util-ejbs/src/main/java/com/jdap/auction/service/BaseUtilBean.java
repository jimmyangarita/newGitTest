
package com.jdap.auction.service;

// ~--- non-JDK imports ---------------------------------------------------------

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.jdap.auction.common.xml.apiconfiguration.RuntimeFaultMessageType;
import com.jdap.auction.common.xml.fault.ApiFaultType;

import com.jdap.auction.common.xml.fault.FaultCodeCategory;
import com.jdap.auction.common.xml.fault.FaultSeverityLevel;
import com.jdap.auction.common.xml.fault.RequestCorrelationIdsType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.BaseUtilLocal;
import com.jdap.auction.service.JmsPublisherUtilBeanLocal;
import com.jdap.auction.service.XmlUtilBeanLocal;

/**
 * Session Bean implementation class BaseUtilBean
 */
@Stateless(name = "BaseUtilLocal", mappedName = "ejb.jdap.auction.service.BaseUtilLocal")
public class BaseUtilBean implements BaseUtilLocal
{
	/** Field description */
	public static Logger logger = Logger.getLogger(BaseUtilBean.class.getName());

	String EMPTY_STRING = "";

	/**
	 * Default constructor.
	 */
	public BaseUtilBean()
	{

		// TODO Auto-generated constructor stub
	}

    @EJB
    protected XmlUtilBeanLocal xmlUtil;
	
    @EJB
    protected JmsPublisherUtilBeanLocal jmsPublisherUtil;
	/**
	 * Method description
	 * 
	 */
	public void baseUtil()
	{

		// TODO Auto-generated method stub
	}

	public Timestamp getCurrentTimeStamp()
	{

		Calendar calendar = Calendar.getInstance();

		//LOG.info( "JDAP TIME Format10: " + calendar.getTime() + "" + "\n Format20: " + calendar.getTime().getTime() );

		Long timeStamp = calendar.getTime().getTime();
		Timestamp currentTime = new Timestamp(timeStamp);

		return currentTime;
	}

	public XMLGregorianCalendar getXmlCurrentime() throws AuctionServiceException
	{

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(gc.getTime());
		XMLGregorianCalendar xmlgc;

		try
		{
			xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			throw new AuctionServiceException(" ** DatatypeConfigurationException **");
		}

		return xmlgc;
	}
	
	public XMLGregorianCalendar converToXmlCurrentime(Date date) throws AuctionServiceException
	{

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		XMLGregorianCalendar xmlgc;

		try
		{
			xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			throw new AuctionServiceException(" ** DatatypeConfigurationException **");
		}

		return xmlgc;
	}


	public Timestamp getCurretTimestampPlusDays(int numberDays)
	{

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DAY_OF_MONTH, numberDays);
		Timestamp futureTime = new Timestamp(calendar.getTime().getTime());

		return futureTime;
	}

	public RuntimeFaultMessageType buildFaultMsg(String code, String summary, String detailMsg, String coreFaul,
			String originalPayload)
	{

		RuntimeFaultMessageType faultMessage = new RuntimeFaultMessageType();
		RuntimeFaultMessageType.Detail detail = new RuntimeFaultMessageType.Detail();

		faultMessage.setCode(code);
		faultMessage.setSummary(summary);
		detail.setMessage(detailMsg);
		detail.setCoreFault(coreFaul);
		detail.setOriginalPayload(originalPayload);
		faultMessage.setDetail(detail);

		return faultMessage;
	}

	public ApiFaultType buildApiExceptionMsg(String serviceId, String code, String summary, String detailMsg,
			String coreFaul, String originalPayload) throws AuctionServiceException
	{

		ApiFaultType apiFaultMessage = new ApiFaultType();
		RequestCorrelationIdsType requestCorrelation = new RequestCorrelationIdsType();

		apiFaultMessage.setFaultedServiceId(serviceId);
		apiFaultMessage.setFaultCode(code);
		apiFaultMessage.setFaultCodeCategory(FaultCodeCategory.BUSINESS);
		apiFaultMessage.setMessage(summary);
		apiFaultMessage.setSeverityLevel(FaultSeverityLevel.ERROR);
		apiFaultMessage.getStack().add(detailMsg);

		XMLGregorianCalendar xmlgc = getXmlCurrentime();
		apiFaultMessage.setFaultTime(xmlgc);
		
		publishApiFaultMessage(serviceId, code, summary, detailMsg, coreFaul, originalPayload);

		return apiFaultMessage;
	}

	private void publishApiFaultMessage(String serviceId, String code, String summary, String detailMsg,
			String coreFaul, String originalPayload) throws AuctionServiceException
	{

		com.jdap.auction.common.xml.apiconfiguration.ApiFaultType apiFaultMsg =
				new com.jdap.auction.common.xml.apiconfiguration.ApiFaultType();

		com.jdap.auction.common.xml.apiconfiguration.RequestCorrelationIdsType requestCorrelation = 
				new com.jdap.auction.common.xml.apiconfiguration.RequestCorrelationIdsType();

		apiFaultMsg.setFaultedServiceId(serviceId);
		apiFaultMsg.setFaultCode(code);
		apiFaultMsg
				.setFaultCodeCategory(com.jdap.auction.common.xml.apiconfiguration.FaultCodeCategory.BUSINESS);
		apiFaultMsg.setMessage(summary);
		apiFaultMsg.setSeverityLevel(com.jdap.auction.common.xml.apiconfiguration.FaultSeverityLevel.ERROR);
		apiFaultMsg.getStack().add(detailMsg);

		XMLGregorianCalendar xmlgc = getXmlCurrentime();
		apiFaultMsg.setFaultTime(xmlgc);
		
		String xmlDefinition = xmlUtil.marshal(apiFaultMsg);
		jmsPublisherUtil.send(xmlDefinition);
	}

	public boolean textHasContent(String aText)
	{

		return (aText != null) && (!aText.trim().equals(EMPTY_STRING));
	}
}
