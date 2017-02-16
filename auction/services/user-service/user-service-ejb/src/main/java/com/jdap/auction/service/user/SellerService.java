
package com.jdap.auction.service.user;

// ~--- non-JDK imports ---------------------------------------------------------

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

//import com.jdap.auction.common.xml.discover.ResumeTaskRequestEBM;

import com.jdap.auction.common.xml.user.MessageHeaderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.exceptions.AuctionServiceException;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.May.06 10:02 AM
 * @author JDAP Corporation
 */
/*
 @SecurityPolicies (
				{@SecurityPolicy ( uri = "policy:oracle/wss10_username_id_propagation_with_msg_protection_service_policy" )} )
*/
	   //,   @SecurityPolicy(uri="policy:oracle/authorization_polic")

@WebService(serviceName = "WebSellerService", name = "WebSeller",
			targetNamespace = "http://www.auction.com/api/adf/userservice/service" )
		//targetNamespace = "http://www.auction.com/api/adf/sellerservice/service")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@Stateless(name = "SellerService", mappedName = "ejb.jdap.auction.service.SellerService")
public class SellerService
{
	/** Field description */
	@EJB
	private UserLocal userBean;

	@Resource
	WebServiceContext wsctx;
	
	public static Logger logger = Logger.getLogger(SellerService.class.getName());
	
	/**
	 * Constructs ...
	 * 
	 */
	public SellerService()
	{
	}

	/**
	 * 
	 * @param header
	 * @param newSeller
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod(operationName = "CreateSeller", action = "createSeller")
	@RequestWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "CreateSellerRequestMessageType")
	@ResponseWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "CreateSellerResponseMessageType")
	@WebResult(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2", name = "seller")
	public SellerType addSeller(@WebParam(targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
			name = "MessageHeader") MessageHeaderType header, @WebParam(
			targetNamespace = "http://xmlns.jdap.com/auction/Seller/Objects/v2", name = "seller") SellerType newSeller)
			throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.addSeller(newSeller);
		header.setRequestId("OK");

		return newSeller;
	}

	/**
	 * 
	 * @param header
	 * @param updatedSeller
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod(operationName = "UpdateSeller", action="updateSeller")
	@RequestWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "UpdateSellerRequestMessageType")
	@ResponseWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "UpdateSellerResponseMessageType")
	@WebResult(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2", name = "seller")
	public SellerType updateSeller(@WebParam(targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
			name = "MessageHeader") MessageHeaderType header, @WebParam(
			targetNamespace = "http://xmlns.jdap.com/auction/Seller/Objects/v2", name = "seller") SellerType updatedSeller)
			throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.updateSeller(updatedSeller);
		header.setRequestId("OK");

		return updatedSeller;
	}

	/**
	 * 
	 * @param header
	 * @param seller
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod(operationName = "RemoveSeller", action="removeSeller")
	@RequestWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "DeleteSellerRequestMessageType")
	@ResponseWrapper(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2",
			className = "DeleteSellerResponseMessageType")
	@WebResult(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2", name = "MessageHeader")
	public MessageHeaderType deleteSeller(
			@WebParam(targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",name = "MessageHeader") MessageHeaderType header,
			@WebParam(targetNamespace = "http://xmlns.jdap.com/auction/Seller/Objects/v2",name = "seller") SellerType seller)
			throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.deleteSeller(seller);
		header.setRequestId("OK");
		return header;

	}

	@WebMethod ( operationName = "QuerySellers" , action = "querySeller" )
	@RequestWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2" ,
	                  className = "QuerySellerRequestMessageType" )
	@ResponseWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2" ,
	                   className = "QuerySellerResponseMessageType" )
	@WebResult ( targetNamespace = "http://xmlns.jdap.com/auction/Seller/Messages/v2" , name = "seller" )
	public List<SellerType> QuerySellers(@WebParam ( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2" ,
	                                             name = "MessageHeader" )
	MessageHeaderType header) throws AuctionServiceException
	{
		header.setRequestId("FAULT");

		return userBean.getSellersType();
	}
/*
	@SOAPBinding ( parameterStyle = SOAPBinding.ParameterStyle.BARE )
	@WebMethod
	public void process(@WebParam ( name = "ResumeTaskRequestEBM" )
	ResumeTaskRequestEBM parameters)
	{
		
		String taskID = parameters.getTaskIdEBO().getTaskId();
		String taskActionID = parameters.getTaskActionIdEBO().getTaskActionId();
		// long time = dateUtilBeanLocal.asUTCLong( System.currentTimeMillis() ); 
		
		logger.info("\nDISCOVERY-ON-DEMAND::PROCESS received request with taskID: " + taskID + " and taskActionID: "
		                + taskActionID);
		
		byte[] taskActionParameters = parameters.getTaskActionParameters();
		String definition = new String(taskActionParameters);
		
		logger.info("\nDISCOVERY-ON-DEMAND::PROCESS task action parameters: " + definition);
		
		logger.info("\n DISCOVERY-ON-DEMAND::PROCESS task action Status: " + parameters.getJobStatusEBOType().getProgress());
		
		logger.info("\n\n************ Seller Service--------------\n");
		
		MessageContext msgctx = wsctx.getMessageContext();
		
		logger.info("\n\nUSERNAME : " + wsctx.getUserPrincipal().getName());
		
	}
*/
}
