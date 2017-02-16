package com.jdap.auction.service.item;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
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

import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.MessageHeaderType;
import com.jdap.auction.exceptions.AuctionServiceException;

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
    serviceName = "WebCategoryService",
    name = "WebCategory",
    targetNamespace = "http://xmlns.jdap.com/auction/Category/Services/v2" )

//@SOAPBinding(style= Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@SOAPBinding(
    style = Style.DOCUMENT,
    use = Use.LITERAL,
    parameterStyle = ParameterStyle.WRAPPED
)
@Stateless( name = "CategoryService", mappedName = "ejb.jdap.auction.service.CategoryService" )
public class CategoryService
{
    /** Field description */
    @EJB
    private ItemRemote categoryBean;

    /** Field description */
    public static Logger logger = Logger.getLogger( CategoryService.class.getName() );
    @Resource
    WebServiceContext wsctx;
    @Resource
    SessionContext context;

    /**
     * Constructs ...
     *
     */
    public CategoryService()
    {
    }

    /**
     * Method description
     *
     *
     * @param categoryrequest
     * @param createCategories
     *
     * @param header
     * @param newCategory
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "CreateCategory", action = "createCategory" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                     className = "CreateCategoryRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                      className = "CreateCategoryResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
        partName = "output",
        name = "category"
    )
    public CategoryType addCategory( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Objects/v2",
        partName = "input",
        name = "category"
    ) CategoryType newCategory, @WebParam( name = "createCategories" ) boolean createCategories ) throws AuctionServiceException
    {
        if( ( null != header ) && ( null != header.getSender() ) )
        {
            logger.info( "header.getSender.getSenderid: " + header.getSender().getSenderId() );
        }

        header.setRequestId( "FAULT" );
        //categoryBean.addCategory( newCategory, createCategories );
        header.setRequestId( "OK" );

        return newCategory;
    }

    /* action = "http://localhost.localdomain:7011/CategoryService/ApiCategoryService/changeCategory") */

    /**
     * Method description
     *
     *
     * @param header
     * @param updatedCategory
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "UpdateCategory", action = "updateCategory" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                     className = "UpdateCategoryRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                      className = "UpdateCategoryResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
        partName = "output",
        name = "category"
    )
    @SOAPBinding()

    /* (style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED) */
    public CategoryType updateCategory( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Objects/v2",
        partName = "input",
        name = "category"
    ) CategoryType updatedCategory ) throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );
        //categoryBean.updateCategory( updatedCategory );
        header.setRequestId( "OK" );

        return updatedCategory;
    }

    /**
     * Method description
     *
     *
     * @param header
     * @param category
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod( operationName = "RemoveCategory", action = "deleteCategory" )
    @SOAPBinding
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                     className = "DeleteCategoryRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                      className = "DeleteCategoryResponseMessageType" )
    @WebResult(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
        partName = "output",
        name = "messageHeader"
    )
    public MessageHeaderType deleteCategory( @WebParam(
        targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
        partName = "input",
        name = "MessageHeader"
    ) MessageHeaderType header, @WebParam(
        targetNamespace = "http://xmlns.jdap.com/auction/Category/Objects/v2",
        partName = "input",
        name = "category"
    ) CategoryType category ) throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );
        //categoryBean.deleteCategory( category );
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
    @WebMethod( operationName = "QueryCategorys", action = "queryCategory" )
    @RequestWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                     className = "QueryCategoryRequestMessageType" )
    @ResponseWrapper( targetNamespace = "http://xmlns.jdap.com/auction/Category/Messages/v2",
                      className = "QueryCategoryResponseMessageType" )
    //I am using the namespace from Item that has category in it. temporally I need to generate the JAXb objects for Categories
    @WebResult( targetNamespace = "http://xmlns.jdap.com/auction/Item/Objects/v2", name = "category" )
    public
    List<CategoryType> QueryCategorys( @WebParam( targetNamespace = "http://xmlns.jdap.com/Common/MessageHeader/v2",
                                          name = "MessageHeader" ) MessageHeaderType header,
                                       @WebParam( name = "parentCategory" ) String parentCategory)
                                                  throws AuctionServiceException
    {
        header.setRequestId( "FAULT" );

        return categoryBean.getCategoriesType(parentCategory);
    }

    /*
    /**
     * Method description
     *
     *
     * @param parameters
     */
/*    @SOAPBinding( parameterStyle = SOAPBinding.ParameterStyle.BARE )
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
        logger.info( "\n************ Category Service--------------\n" );

        MessageContext msgctx = wsctx.getMessageContext();

        logger.info( "\n\nUSERNAME : " + wsctx.getUserPrincipal().getName() );
        logger.info( "Categoryservice, PRINCIPAL :: " + context.getCallerPrincipal().getName() );

        /*
         *  the principal is propagated try { this.addCategory(new MessageHeaderType() , new CategoryType()); } catch
         * (AuctionServiceException e) { logger.severe("ERROR calling CategoryService.Process.addCategory :: " +
         * e.getMessage()); e.printStackTrace(); }
         */
    //}*/
}
