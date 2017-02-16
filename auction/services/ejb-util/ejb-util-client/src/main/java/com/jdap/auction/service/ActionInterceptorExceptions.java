
package com.jdap.auction.service;

// ~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.xml.fault.ApiFaultType;
import com.jdap.auction.common.xml.fault.FaultCodeCategory;
import com.jdap.auction.common.xml.fault.FaultSeverityLevel;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.persistence.exceptions.AuctionPersistenceException;

// ~--- JDK imports -------------------------------------------------------------

// import com.jdap.auction.exceptions.AuctionServiceException;
import java.lang.reflect.Type;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2012.May.14 01:46 PM
 * @author jdap Corporation
 */
public class ActionInterceptorExceptions
{
	/** Field description */
	public static Logger logger = Logger.getLogger(ActionInterceptorExceptions.class.getName());

	public XMLGregorianCalendar getXmlCurrentime()
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(gc.getTime());
		XMLGregorianCalendar xmlgc = null;

		try
		{
			xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			// throw new Exception(" ** DatatypeConfigurationException **");
		}

		return xmlgc;
	}

	public ApiFaultType buildBasicException(Throwable cause)
	{
		ApiFaultType faultInfo = new ApiFaultType();
		String serviceCause = (null != cause.getCause()) ? cause.getCause().getClass().toString() : "NO_SERVICE_ID";
		faultInfo.setFaultedServiceId(serviceCause);
		faultInfo.setFaultCode("-10");
		faultInfo.setFaultCodeCategory(FaultCodeCategory.BUSINESS);
		faultInfo.setMessage((null != cause.getMessage()) ? cause.getMessage() : "NO_MESSAAGE");
		faultInfo.setSeverityLevel(FaultSeverityLevel.FATAL);
		faultInfo.setFaultTime(getXmlCurrentime());

		return faultInfo;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param ic
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@AroundInvoke
	public Object manage(InvocationContext ic) throws Exception
	{
		Throwable cause = null;
		// RuntimeFaultMessageType faultInfo = null;
		ApiFaultType faultInfo = new ApiFaultType();

		try
		{
			logger.info("\n--------> IC, call to ======> "
					+ ((null != ic.getTarget())
							? ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName()
							: ic.getMethod().getName()));

			return ic.proceed();
		} catch (AuctionServiceException e)
		{
			cause = (e.getCause() != null) ? e.getCause() : e;
			faultInfo = e.getFaultInfo();

			logger.log(Level.INFO, "STEP 1.0 :: "
					+ ((null != ic.getTarget())
							? ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName()
							: ic.getMethod().getName()) + " \n  AuctionServiceException get class : "
					+ e.getClass().getName() + "\n cause class: " + cause.getClass().getName() + "\n faultInfo: "
					+ ((faultInfo.getMessage() != null) ? faultInfo.getMessage() : "NO Apply!!"));

		} catch (Exception e)
		{
			cause = (e.getCause() != null) ? e.getCause() : e;
			logger.log(Level.INFO, "STEP 1.1 :: "
					+ ((null != ic.getTarget())
							? ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName()
							: ic.getMethod().getName()) + " \n  Exception class : " + e.getClass().getName()
					+ "\n cause class: " + cause.getClass().getName());
		}

		/* In case we need to deal wit exceptions. */
		if (cause instanceof AuctionServiceException)
		{
			throw new AuctionServiceException(cause.getMessage(), faultInfo, cause);
		}

		if (cause instanceof AuctionPersistenceException)// runtime
		{

			faultInfo = this.buildBasicException(cause);
			
			logger.log(Level.INFO, "STEP 20.0, --RUNTIME FAULT--  :: "
					+ ((null != ic.getTarget())
							? ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName()
							: ic.getMethod().getName()) + " \n  AuctionServiceException get class : "
					+ this.getClass().getName()
					+ ((faultInfo.getMessage() != null) ? faultInfo.getMessage() : "NO Apply!!"));

			throw new AuctionServiceException(cause.getMessage(), faultInfo, cause);
			// return handleLaunderedException(ic, new AuctionServicePersistenceException(cause.getMessage(),
			// cause));

		} else
		{
			return handleLaunderedException(ic, (Exception) cause);
		}
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param ic
	 * @param exception
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	private Object handleLaunderedException(InvocationContext ic, Exception exception) throws Exception
	{
		// Exceptions declared to be thrown in method signature
		Class<?>[] types = ic.getMethod().getExceptionTypes();

		logger.log(Level.FINE, " \n ic.getMethod().getExceptionTypes() :: " + types.length);

		if ((null != types) && (types.length > 0))
		{

			// Method declares exceptions to be thrown
			for (Class<?> clazz : types)
			{
				logger.log(Level.INFO, " \n STEP 2 , Super clazz (IC): " + clazz.getName() + "\n Sub class (cause): "
						+ exception.getClass().getName());

				if (clazz.isAssignableFrom(exception.getClass()))
				{
					logger.log(Level.SEVERE, "\n-----> IC, re-throw laundered exception ===> : ", exception);

					throw (exception);
				}
			}
		}

		// if we want to let the caller that something went wrong
		if (exception != null)
		{
			logger.log(Level.SEVERE, "-------> IC, Handle unexpected exception===>: \n", exception);

			if (RuntimeException.class.isAssignableFrom(exception.getClass()))
			{
				throw new AuctionServiceException("Unexpected Runtime Exception, please review the logs.", exception);
			} else
			{
				throw new AuctionServiceException("Unexpected not runtime Exception, please review the logs.",
						exception);
			}
		}

		// else Swallow the exception and log the error!, The caller doesn't
		// understand what is wrong
		Type returnValue = ic.getMethod().getGenericReturnType();

		if (null != returnValue)
		{
			logger.warning("#######################IC#################################" + "\n STEP 3, returnValue: "
					+ returnValue);

			// method expects a return value
			return (null);
		} else
		{
			logger.warning("*********************************IC********************" + "\n STEP 4, returnValue: "
					+ returnValue);

			// Method signature is void, handle it
			return null;
		}
	}
}
