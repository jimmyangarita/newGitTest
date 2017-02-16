
package com.jdap.auction.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.jdap.auction.common.xml.fault.ApiFaultType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.exceptions.AuctionServiceValidationException;
import com.jdap.auction.persistence.exceptions.AuctionPersistenceException;

public abstract class BaseActionService
{

	/** Field description */
	@EJB
	protected BaseUtilLocal bUtil; // one way of injection

	/** Field description */
	@EJB
	protected XmlUtilBeanLocal xmlUtil;

	/** Field description */
	@EJB
	protected JmsPublisherUtilBeanLocal jmsPublisherUtil;

	/** Field description */
	public static Logger logger = Logger.getLogger(BaseActionService.class.getName());

	/** Field description */
	protected ApiFaultType apiExceptionMsg;

	/** Field description */
	protected static final String ERR_CODE_NULL_ID = "10001";

	/** Field description */
	protected static final String ERR_NULL_ID = " One ore more id(s) are null";

	/** Field description */
	protected static final String ERR_CODE_NULL_PARAMETER = "10002";

	/** Field description */
	protected static final String ERR_NULL_PARAMETERS = " One ore more basic values are null";

	/** Field description */
	protected static final String ERR_CODE_EMPTY_PARAMETER = "10003";

	/** Field description */
	protected static final String ERR_EMPTY_PARAMETERS = " One ore more basic values are empty";

	/** Field description */
	protected static final String ERR_CODE_ID_NOTEXIST = "10004";

	/** Field description */
	protected static final String ERR_ID_NOTEXIST = " The OID does not exist";

	/** Field description */
	protected static final String ERR_CODE_DATATYPE_ERROR_CONFIGURATION = "10005";

	/** Field description */
	protected static final String ERR_MSG_DATATYPE_ERROR_CONFIGURATION = " The OID does not exist";

	/** Field description */
	protected static final String ERR_CODE_INITIAL_PRICE = "10006";

	/** Field description */
	protected static final String ERR_MSG_INITIAL_PRICE = " The item initial price has to be more than cero and less "
			+ "than 10000000";

	/**
	 * 
	 * @param serviceName
	 * @param id
	 * @param <T>
	 * @throws AuctionServiceException
	 */
	protected <T> void validateId(String serviceName, String id) throws AuctionServiceException
	{
		if (!bUtil.textHasContent(id))
		{
			apiExceptionMsg = bUtil.buildApiExceptionMsg(serviceName, ERR_CODE_NULL_ID, ERR_NULL_ID, "", "", "");

			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: " + ERR_NULL_PARAMETERS,
					apiExceptionMsg);
		}

		try
		{
			String[] components = id.split("-");
			if (components.length != 5)
				throw new IllegalArgumentException("NOT a valid UUID string: " + id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			AuctionPersistenceException persistenceException = new AuctionPersistenceException(this.getClass()
					.getName() + ", " + e.getMessage(), e.getCause());
			throw persistenceException;
		}

	}

	/**
	 * 
	 * @param serviceName
	 * @param initialPrice
	 * @throws AuctionServiceException
	 */
	protected void validatePrice(String serviceName, Double initialPrice) throws AuctionServiceException
	{
		if (initialPrice != null && (initialPrice <= 0 || initialPrice > 1000000.0))
		{
			apiExceptionMsg = bUtil.buildApiExceptionMsg(serviceName, ERR_CODE_INITIAL_PRICE, ERR_MSG_INITIAL_PRICE,
					"AuctionServiceValidationException, amount not between 1 and 10000000",
					"ADF-AuctionServiceValidationException", initialPrice.toString());

			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
					+ ERR_CODE_INITIAL_PRICE, apiExceptionMsg);
		}

	}

	/**
	 * 
	 * @param serviceName
	 * @param className
	 * @param errorType
	 * @param msg
	 * @throws AuctionServiceException
	 */
	protected void throwBasicValidationExeption(String serviceName, String className, String errorType, String msg)
			throws AuctionServiceException
	{
		if (errorType.equals("NULL"))
		{
			apiExceptionMsg = bUtil.buildApiExceptionMsg(serviceName, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
					msg, "Please review for NULL values", "");

			throw new AuctionServiceValidationException(className + ", ERROR: " + ERR_NULL_PARAMETERS, apiExceptionMsg);
		}
		else if (errorType.equals("EMPTY"))
		{
			apiExceptionMsg = bUtil.buildApiExceptionMsg(serviceName, ERR_CODE_EMPTY_PARAMETER, ERR_EMPTY_PARAMETERS,
					msg, "Please review for EMPTY values", "");

			throw new AuctionServiceValidationException(className + ", ERROR: " + ERR_EMPTY_PARAMETERS, apiExceptionMsg);
		}
		else
		{
			logger.log(Level.WARNING, "\n...buildBasicValidationExeption , Invalid ErrorType....\n:");
		}
	}

}
