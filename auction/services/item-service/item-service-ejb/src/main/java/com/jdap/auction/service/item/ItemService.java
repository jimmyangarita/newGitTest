package com.jdap.auction.service.item;

//~--- non-JDK imports ---------------------------------------------------------

//import com.jdap.auction.common.xml.discover.ResumeTaskRequestEBM;
import com.jdap.auction.common.xml.item.ItemType;
import com.jdap.auction.common.xml.item.MessageHeaderType;
import com.jdap.auction.exceptions.AuctionServiceException;

//~--- JDK imports -------------------------------------------------------------

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;

/**
 * Class description
 *
 *
 * @version 9.0, 2013.May.06 10:02 AM
 * @author JDAP Corporation
 */

/*@SecurityPolicies ( {@SecurityPolicy ( uri =
* "policy:oracle/wss10_username_id_propagation_with_msg_protection_service_policy" )} ) */
@WebService(
    serviceName = "WebItemService",
    name = "WebItem",
    targetNamespace = "http://xmlns.jdap.com/auction/Item/Services/v2" )
    //targetNamespace = "http://xmlns.dcim.emerson.com/cps/scheduler/quartz-scheduler/ebm"


//@SOAPBinding(style= Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@SOAPBinding(
    style = Style.DOCUMENT,
    use = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@Stateless( name = "ItemService", mappedName = "ejb.jdap.auction.service.ItemService" )
public class ItemService
{
    /** Field description */
    @EJB
    private ItemLocal itemBean;

    /** Field description */
    public static Logger logger = Logger.getLogger( ItemService.class.getName() );
    @Resource
    WebServiceContext wsctx;
    @Resource
    SessionContext context;

    /**
     * Constructs ...
     *
     */
    public ItemService()
    {
    }

    /**
     * Method description
     *
     *
     * @param itemrequest
     * @param createCategories
     *
     * @param header
     * @param newItem
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "CreateItem", action = "createItem" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                     className = "CreateItemRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                      className = "CreateItemResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
        partName = "output",
        name = "item"
    )
    public ItemType addItem( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Objects/v2",
        partName = "input",
        name = "item"
    ) ItemType newItem, @WebParam( name = "createCategories" ) boolean createCategories ) throws AuctionServiceException
    {
        if( ( null != header ) && ( null != header.getSender() ) )
        {
            logger.info( "header.getSender.getSenderid: " + header.getSender().getSenderId() );
        }

        header.setRequestId( "FAULT" );
        itemBean.addItem( newItem, createCategories );
        header.setRequestId( "OK" );

        return newItem;
    }

    /* action = "http://localhost.localdomain:7011/ItemService/ApiItemService/changeItem") */

    /**
     * Method description
     *
     *
     * @param header
     * @param updatedItem
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "UpdateItem", action = "updateItem" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                     className = "UpdateItemRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                      className = "UpdateItemResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
        partName = "output",
        name = "item"
    )
    @SOAPBinding()

    /* (style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED) */
    public ItemType updateItem( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Objects/v2",
        partName = "input",
        name = "item"
    ) ItemType updatedItem ) throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );
        itemBean.updateItem( updatedItem );
        header.setRequestId( "OK" );

        return updatedItem;
    }

    /**
     * Method description
     *
     *
     * @param header
     * @param item
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "RemoveItem", action = "deleteItem" )
    @SOAPBinding
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                     className = "DeleteItemRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                      className = "DeleteItemResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
        partName = "output",
        name = "messageHeader"
    )
    public MessageHeaderType deleteItem( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Item/Objects/v2",
        partName = "input",
        name = "item"
    ) ItemType item ) throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );
        itemBean.deleteItem( item );
        header.setRequestId( "OK" );

        return header;
    }

    /**
     * Method description
     *
     *
     * @param header
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "QueryItems", action = "queryItem" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                     className = "QueryItemRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2",
                      className = "QueryItemResponseMessageType" )
    @WebResult( targetNamespace = "http://xmlns.jdap.com/auction/Item/Messages/v2", name = "item" )
    public
    List<ItemType> QueryItems( @WebParam( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
                                          name = "MessageHeader" ) MessageHeaderType header )
                                                  throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );

        return itemBean.getItemsType();
    }

    /**
     * Method description
     *
     *
     * @param parameters
     */
    /*
     @SOAPBinding( parameterStyle = SOAPBinding.ParameterStyle.BARE )
     
    @WebMethod
    public void process( @WebParam( name = "ResumeTaskRequestEBM" ) ResumeTaskRequestEBM parameters )
    {
        String taskID = parameters.getTaskIdEBO().getTaskId();
        String taskActionID = parameters.getTaskActionIdEBO().getTaskActionId();

        // long time = dateUtilBeanLocal.asUTCLong( System.currentTimeMillis() ); 
        logger.info( "\nDISCOVERY-ON-DEMAND::PROCESS received request with taskID: " + taskID + " and taskActionID: "
                + taskActionID );

        byte[] taskActionParameters = parameters.getTaskActionParameters();
        String definition = new String( taskActionParameters );

        logger.info( "\nDISCOVERY-ON-DEMAND::PROCESS task action parameters: " + definition );
        logger.info( "\n DISCOVERY-ON-DEMAND::PROCESS task action Status: " + parameters.getJobStatusEBOType()
                .getProgress() );
        logger.info( "\n************ Item Service--------------\n" );

        MessageContext msgctx = wsctx.getMessageContext();

        logger.info( "\n\nUSERNAME : " + wsctx.getUserPrincipal().getName() );
        logger.info( "Itemservice, PRINCIPAL :: " + context.getCallerPrincipal().getName() );

        
           the principal is propagated try { this.addItem(new MessageHeaderType() , new ItemType()); } catch
          (AuctionServiceException e) { logger.severe("ERROR calling ItemService.Process.addItem :: " +
         e.getMessage()); e.printStackTrace(); }
         
    }
    */
}
