package com.jdap.auction.service.item;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

//import com.jdap.auction.common.xml.apiconfiguration.AddItemResponseType;
//import com.jdap.auction.common.xml.apiconfiguration.AddItemType;
import com.jdap.auction.exceptions.ActionServiceException;
import com.jdap.auction.model.Item;

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
    serviceName = "ApiItemService",
    name = "ApiItem",
    targetNamespace = "http://www.auction.com/api/adf/itemservice/service"
)
@SOAPBinding
@Stateless( name = "ItemService", mappedName = "ejb.jdap.auction.service.ItemService" )
public class ItemService
{
    /** Field description */
    @EJB
    private ItemLocal itemBean;

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
     *
     * @return
     *
     * @throws ActionServiceException
     */
    @WebMethod( operationName = "addNewItem",
    			action = "http://localhost.localdomain:7011/ItemService/ApiItemService/addNewItem" )
    @RequestWrapper( targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm", 
    				 className = "AddItemType" )
    @ResponseWrapper( targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm",
                      className = "AddItemResponseType" )
    @SOAPBinding
    @WebResult(
        targetNamespace = "http://www.auction.com/api/adf/itemservice/ebo",
        name = "addItemResponse"
    )
    public String addItem( @WebParam (name = "title") String title,
    		@WebParam (name = "description") String description,
			@WebParam (name = "initialAmount") double initialAmount) throws ActionServiceException
    {

        return itemBean.addItem(title, description, initialAmount);
    }
    
    
    @WebMethod( operationName = "changeItemAmount",
			action = "http://localhost.localdomain:7011/ItemService/ApiItemService/changeItem")
    @SOAPBinding
    @WebResult(targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm",
    		   name = "changeItemAmountResponse")
    public String changeItem(@WebParam (name = "itemId") String itemId,
    						@WebParam (name = "newAmount") double amount) throws ActionServiceException{
    	
    	itemBean.changeItemAmount(itemId, new Double(amount));
    	
    	return "Succesfully Item Amount Changed";
    	
    }
    
    @WebMethod( operationName = "removeItem",
			action = "http://localhost.localdomain:7011/ItemService/ApiItemService/removeItem")
	@SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm",
			   name = "removeItemResponse")
	public String removeItem(@WebParam (name = "itemId") String itemId) throws ActionServiceException{
		
		itemBean.removeItem(itemId);
		
		return "Succesfully Item Removed";
		
	}

    @WebMethod( operationName = "getAllItems",
			action = "http://localhost.localdomain:7011/ItemService/ApiItemService/allItems")
    @SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm",
	   name = "getAllItemsResponse")
    public List<String> getItems() throws ActionServiceException
    {
    	List<String> itemIds = new ArrayList<String>();
    	
    	for(Item item : itemBean.getAllItems())
    	{
    		itemIds.add(item.getId().toString());
    	}
    	
    	return itemIds;
    }
    
    
    @WebMethod( operationName = "getHigherItems",
			action = "http://localhost.localdomain:7011/ItemService/ApiItemService/higherItems")
    @SOAPBinding
	@WebResult(targetNamespace = "http://www.auction.com/api/adf/itemservice/ebm",
	   name = "getHighItemsResponse")
    public List<String> getHighItems(@WebParam (name = "amountHigherThan") double amount) throws ActionServiceException
    {
    	List<String> itemIds = new ArrayList<String>();
    	
    	for(Item item : itemBean.getHigherItems(amount))
    	{
    		itemIds.add(item.getId().toString());
    	}
    	
    	return itemIds;
    }
    

}
