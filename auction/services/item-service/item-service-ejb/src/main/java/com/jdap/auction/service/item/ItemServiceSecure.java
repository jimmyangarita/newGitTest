
package com.jdap.auction.service.item;

// ~--- non-JDK imports ---------------------------------------------------------

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebServiceContext;

import weblogic.wsee.jws.jaxws.owsm.SecurityPolicies;
import weblogic.wsee.jws.jaxws.owsm.SecurityPolicy;

//import com.jdap.auction.common.xml.discover.ResumeTaskRequestEBM;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.May.06 10:02 AM
 * @author JDAP Corporation
 */
@SecurityPolicies (
				{@SecurityPolicy ( uri = "policy:oracle/wss10_username_id_propagation_with_msg_protection_service_policy" )} )
@WebService ( serviceName = "WebItemServiceSecure" , name = "WebItemSecure" ,
// targetNamespace = "http://xmlns.jdap.com/auction/Item/Services/v2" )
              targetNamespace = "http://xmlns.dcim.emerson.com/cps/scheduler/quartz-scheduler/ebm" )
// @SOAPBinding(style= Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@SOAPBinding ( style = Style.DOCUMENT , use = Use.LITERAL , parameterStyle = ParameterStyle.WRAPPED )
@Stateless ( name = "ItemServiceSecure" , mappedName = "ejb.jdap.auction.service.ItemServiceSecure" )
public class ItemServiceSecure
{
	
	public static Logger logger = Logger.getLogger(ItemServiceSecure.class.getName());
	
	@Resource
	WebServiceContext wsctx;
	
	@Resource
	SessionContext context;
	
	
	ItemService itemService;
	
	/**
	 * Constructs ...
	 * 
	 */
	public ItemServiceSecure()
	{
		itemService = new ItemService();
	}
  
	/*
	@SOAPBinding ( parameterStyle = SOAPBinding.ParameterStyle.BARE )
	@WebMethod
	public void process(@WebParam ( name = "ResumeTaskRequestEBM" )
	ResumeTaskRequestEBM parameters)
	{
		
		logger.info("\nDISCOVERY-ON-DEMAND::PROCESS received request with taskID: " );
		
		itemService.process(parameters);

		//logger.info( "Itemservice, PRINCIPAL :: " + context.getCallerPrincipal().getName());
		
		//
		// the principal is propagated
		 // try
        //{
	      //  this.addItem(new MessageHeaderType() , new ItemType());
        //}
        //catch (AuctionServiceException e)
        //{
        	//logger.severe("ERROR calling ItemServiceSecure.Process.addItem :: " + e.getMessage());
	      //  e.printStackTrace();
        //}
	}
	
	*/
}
