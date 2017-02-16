package com.jdap.auction.service.item;
	

//~--- non-JDK imports ---------------------------------------------------------

import java.util.List;

import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.model.Item;
//
import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.ItemType;


//~--- JDK imports -------------------------------------------------------------

import javax.ejb.Remote;

/**
 * Interface description
 *
 *
 * @version        9.0, 2012.May.14 06:52 PM
 * @author         jdap Corporation
 */
@Remote
public interface ItemRemote
{


    /**
     * Method description
     *
     *
     * @param ItemderID
     * @param ItemID
     * @param amount
     *
     * @return
     *
     * @throws AuctionPersistenceException
     * @throws AuctionServiceException
     */
    //public String persistItem( Long ItemderID, Long ItemID, Double amount ) throws AuctionServiceException;
    //public AddItemResponseType addItem( AddItemType itemrequest ) throws AuctionServiceException;
    
	public ItemType addItem(ItemType newItem, boolean createCategories) throws AuctionServiceException;
    
    public ItemType updateItem(ItemType updatedItem) throws AuctionServiceException;

    public void deleteItem(ItemType item) throws AuctionServiceException;
    
    public void changeItemAmount(String itemId, Double newAmount) throws AuctionServiceException;
    
    public List<Item> getItems() throws AuctionServiceException;
    
    public List<ItemType> getItemsTypeByCategory(String parentcategoryid) throws AuctionServiceException;
    
	public List<ItemType> getItemsType() throws AuctionServiceException;
    
    public List<Item> getHigherItems(Double high) throws AuctionServiceException;
    
    public List<CategoryType> getCategoriesType( String parentCategoryid ) throws AuctionServiceException;
    
    public List<ItemType> getItemsTypeByTitle(String title) throws AuctionServiceException;
    
}
