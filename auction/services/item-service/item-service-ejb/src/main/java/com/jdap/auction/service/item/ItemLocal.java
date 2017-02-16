package com.jdap.auction.service.item;
import java.util.List;

import javax.ejb.Local;

//import com.jdap.auction.common.xml.apiconfiguration.AddItemType;
//import com.jdap.auction.common.xml.apiconfiguration.AddItemResponseType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.model.Item;
//
import com.jdap.auction.common.xml.item.ItemType;;

@Local
public interface ItemLocal {

	public ItemType addItem(ItemType newItem, boolean createCategories) throws AuctionServiceException;
    
    public ItemType updateItem(ItemType updatedItem) throws AuctionServiceException;
    
    public void deleteItem(ItemType item) throws AuctionServiceException;
    
    public void changeItemAmount(String bidId, Double newAmount) throws AuctionServiceException;
        
    public List<Item> getItems() throws AuctionServiceException;
    
	public List<ItemType> getItemsType() throws AuctionServiceException;
    
    public List<Item> getHigherItems(Double high) throws AuctionServiceException;
}
