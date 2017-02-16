package com.jdap.auction.service.bid;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import com.jdap.auction.common.xml.apiconfiguration.AddBidResponseType;
import com.jdap.auction.common.xml.apiconfiguration.AddBidType;
import com.jdap.auction.exceptions.ActionServiceException;
import com.jdap.auction.model.Bid;

//~--- JDK imports -------------------------------------------------------------

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.May.06 10:02 AM
 * @author         JDAP Corporation    
 */
@WebService(
    serviceName = "ApiBidService",
    name = "ApiBid",
    targetNamespace = "http://www.auction.com/api/adf/bidservice/service"
)
@SOAPBinding
@Stateless( name = "BidService", mappedName = "ejb.jdap.auction.service.BidService" )
public class BidService
{
    /** Field description */
    @EJB
    private BidLocal bidBean;

    /**
     * Constructs ...
     *
     */
    public BidService()
    {
    }

    /**
     * Method description
     *
     *
     * @param bidrequest
     *
     * @return
     *
     * @throws ActionServiceException
     */
    @WebMethod( operationName = "addNewBid",
    			action = "http://localhost.localdomain:7011/BidService/ApiBidService/addNewBid" )
    @RequestWrapper( targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm", 
    				 className = "AddBidType" )
    @ResponseWrapper( targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm",
                      className = "AddBidResponseType" )

    @SOAPBinding
    @WebResult(
        targetNamespace = "http://www.auction.com/api/adf/bidservice/ebo",
        partName = "payload",
        name = "bidResponse"
    )
    public AddBidResponseType addBid( @WebParam(
        targetNamespace = "http://www.auction.com/api/adf/bidservice/ebo",
        partName = "payload",
        name = "bid"
    ) AddBidType bidrequest ) throws ActionServiceException
    {
        System.out.println( "JA--Invoked: " + this.getClass().getName() );

        return bidBean.addBid( bidrequest );
    }
    
    
    @WebMethod( operationName = "changeBidAmount",
			action = "http://localhost.localdomain:7011/BidService/ApiBidService/changeBid")
    @SOAPBinding
    @WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm",
    		   name = "changeBidAmountResponse")
    public String changeBid(@WebParam (name = "bidId") String bidId,
    						@WebParam (name = "newAmount") double amount) throws ActionServiceException{
    	
    	bidBean.changeBidAmount(bidId, new Double(amount));
    	
    	return "Succesfully Bid Amount Changed";
    	
    }
    
    @WebMethod( operationName = "removeBid",
			action = "http://localhost.localdomain:7011/BidService/ApiBidService/removeBid")
	@SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm",
			   name = "removeBidResponse")
	public String removeBid(@WebParam (name = "bidId") String bidId) throws ActionServiceException{
		
		bidBean.removeBid(bidId);
		
		return "Succesfully Bid Removed";
		
	}

    @WebMethod( operationName = "getAllBids",
			action = "http://localhost.localdomain:7011/BidService/ApiBidService/allBids")
    @SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm",
	   name = "getAllBidsResponse")
    public List<String> getBids() throws ActionServiceException
    {
    	List<String> bidIds = new ArrayList<String>();
    	
    	for(Bid bid : bidBean.getAllBids())
    	{
    		bidIds.add(bid.getId().toString());
    	}
    	
    	return bidIds;
    }
    
    
    @WebMethod( operationName = "getHigherBids",
			action = "http://localhost.localdomain:7011/BidService/ApiBidService/higherBids")
    @SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/bidservice/ebm",
	   name = "getHighBidsResponse")
    public List<String> getHighBids(@WebParam (name = "amountHigherThan") double amount) throws ActionServiceException
    {
    	List<String> bidIds = new ArrayList<String>();
    	
    	for(Bid bid : bidBean.getHigherBids(amount))
    	{
    		bidIds.add(bid.getId().toString());
    	}
    	
    	return bidIds;
    }
    

}
