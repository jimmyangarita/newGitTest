
package com.jdap.auction.service.user;

// ~--- non-JDK imports ---------------------------------------------------------

import java.util.List;

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

import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.MessageHeaderType;
import com.jdap.auction.exceptions.AuctionServiceException;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.May.06 10:02 AM
 * @author JDAP Corporation
 */
@WebService ( serviceName = "WebBidderService" , name = "WebBidder" ,
              targetNamespace = "http://www.auction.com/api/adf/userservice/service" )
@SOAPBinding ( style = Style.DOCUMENT , use = Use.LITERAL , parameterStyle = ParameterStyle.WRAPPED )
@Stateless ( name = "BidderService" , mappedName = "ejb.jdap.auction.service.BidderService" )
public class BidderService
{
	/** Field description */
	@EJB
	private UserLocal userBean;
	
	/**
	 * Constructs ...
	 * 
	 */
	public BidderService()
	{
	}
	
	/**
	 * 
	 * @param header
	 * @param newBidder
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod ( operationName = "CreateBidder" , action = "createBidder" )
	@RequestWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                  className = "CreateBidderRequestMessageType" )
	@ResponseWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                   className = "CreateBidderResponseMessageType" )
	@WebResult ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" , name = "user" )
	public BidderType addBidder(@WebParam ( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2" ,
	                                        name = "MessageHeader" )
	MessageHeaderType header, @WebParam ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Objects/v2" ,
	                                      name = "user" )
	BidderType newBidder) throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.addBidder(newBidder);
		header.setRequestId("OK");
		
		return newBidder;
	}
	
	/**
	 * 
	 * @param header
	 * @param updatedBidder
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod ( operationName = "UpdateBidder" , action = "updateBidder" )
	@RequestWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                  className = "UpdateBidderRequestMessageType" )
	@ResponseWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                   className = "UpdateBidderResponseMessageType" )
	@WebResult ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" , name = "user" )
	public BidderType updateBidder(@WebParam ( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2" ,
	                                           name = "MessageHeader" )
	MessageHeaderType header, @WebParam ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Objects/v2" ,
	                                      name = "user" )
	BidderType updatedBidder) throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.updateBidder(updatedBidder);
		header.setRequestId("OK");
		
		return updatedBidder;
	}
	
	/**
	 * 
	 * @param header
	 * @param user
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod ( operationName = "RemoveBidder" , action = "removeBidder" )
	@RequestWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                  className = "DeleteBidderRequestMessageType" )
	@ResponseWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                   className = "DeleteBidderResponseMessageType" )
	@WebResult ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" , name = "MessageHeader" )
	public MessageHeaderType
	                deleteBidder(@WebParam ( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2" ,
	                                         name = "MessageHeader" )
	                MessageHeaderType header,
	                             @WebParam ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Objects/v2" ,
	                                         name = "user" )
	                             BidderType user) throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		userBean.deleteBidder(user);
		header.setRequestId("OK");
		return header;
		
	}
	
	
	/**
	 * 
	 * @param header
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod ( operationName = "QueryBidders" , action = "queryBidder" )
	@RequestWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                  className = "QueryBidderRequestMessageType" )
	@ResponseWrapper ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" ,
	                   className = "QueryBidderResponseMessageType" )
	@WebResult ( targetNamespace = "http://xmlns.jdap.com/auction/Bidder/Messages/v2" , name = "bidder" )
	public List<BidderType> QueryBidders(@WebParam ( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2" ,
	                                                 name = "MessageHeader" )
	MessageHeaderType header) throws AuctionServiceException
	{
		header.setRequestId("FAULT");
		
		return userBean.getBiddersType();
	}
	
}
