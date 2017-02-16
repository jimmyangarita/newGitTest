package com.jdap.auction.service.bid;

// ~--- non-JDK imports ---------------------------------------------------------

import java.util.ArrayList;
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
import javax.xml.ws.WebServiceContext;



/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.May.06 10:02 AM
 * @author JDAP Corporation
 */

/*
 * @SecurityPolicies ( {@SecurityPolicy ( uri =
 * "policy:oracle/wss10_username_id_propagation_with_msg_protection_service_policy"
 * )} )
 */
@WebService(serviceName = "WebBidService", name = "WebBid", targetNamespace = "http://xmlns.dcim.emerson.com/cps/scheduler/quartz-scheduler/ebm")
// targetNamespace = "http://www.auction.com/api/adf/bidservice/service" )
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@Stateless(name = "BidService", mappedName = "ejb.jdap.auction.service.BidService")
public class BidService {
	/** Field description */
	@EJB
	private BidLocal bidBean;

	@Resource
	WebServiceContext wsctx;

	/** Field description */
	private String password = null;

	private Integer port_ohs = null;

	/** Field description */
	/*
	 * @EJB protected SecureHttpClientBeanLocal secureHttpClientBeanLocal;
	 */
	public static Logger logger = Logger.getLogger(BidService.class.getName());

	/**
	 * Constructs ...
	 * 
	 */
	public BidService() {
	}


	/**
	 * 
	 * @param amount
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod(operationName = "getHigherBids", action = "http://localhost.localdomain:8011/BidService/ApiBidService/higherBids")
	@SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm", name = "getHighBidsResponse")
	public List<String> getHighBids(
			@WebParam(name = "amountHigherThan") double amount)
			throws Exception {
		List<String> bidIds = new ArrayList<String>();
		
		System.out.println("JA getHighBids");
		logger.warning("JA getHighBids---");
		return bidIds;
	}

	/**
	 * 
	 * @param bidId
	 * @param amount
	 * @return
	 * @throws AuctionServiceException
	 */
	@WebMethod(operationName = "ChangeBidAmount", action = "changeBid")
	@SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm", name = "changeBidAmountResponse")
	public String changeBid(@WebParam(name = "bidId") String bidId,
			@WebParam(name = "newAmount") double amount)
			throws Exception {
		System.out.println("JA Before WS changeBidAmount--");
		bidBean.changeBidAmount(bidId, new Double(amount));
		
		logger.warning("JA changeBid---");
		return "Succesfully Bid Amount Changed";
	}
}
