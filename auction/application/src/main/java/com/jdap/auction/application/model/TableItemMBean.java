
package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.jdap.auction.application.base.BaseItemMBean;
import com.jdap.auction.common.xml.item.ItemType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.item.ItemRemote;

@ManagedBean ( name = "tableItems" )
@SessionScoped
public class TableItemMBean extends BaseItemMBean implements Serializable
{
	/* To diplay the table ItemMBean */
	private ArrayList<ItemMBean> items = new ArrayList<ItemMBean>();
	
	private ItemMBean tempItem = null;
	
	/* TO take the items ItemType */
	private List<ItemType> ItemsType = null;
	
	private boolean found = true;
	
	/** Field description */
	@ManagedProperty ( value = "#{search}" )
	private SearchMBean searchMBean;
	
	/** Field description */
	@EJB
	private ItemRemote itemRemote;
	
	public TableItemMBean() throws AuctionServiceException
	{
		logger.info("Creating Mbean TableItemMBean()2 ...");
		
		// queryItems("");
	}
	
	/**
	 * 
	 * @throws AuctionServiceException
	 */
	public void queryItems() throws AuctionServiceException
	{
		logger.info("TableItemMBean.queryitem, no id");
		
		this.ItemsType = this.itemRemote.getItemsType();
		
		this.items.clear();
		
		for (ItemType tItem : ItemsType)
		{
			ItemMBean itemMBean = super.mapItemTypeToItemMBean(tItem);
			this.items.add(itemMBean);
		}
	}
	
	/**
	 * 
	 * @param categoryParentid
	 * @throws AuctionServiceException
	 */
	public void queryItemsByCategory(String categoryParentid) throws AuctionServiceException
	{
		logger.info("TableItemMBean.getItemsTypeByCategory, id:" + categoryParentid);
		
		this.ItemsType = this.itemRemote.getItemsTypeByCategory(categoryParentid);
		
		logger.info("TableItemMBean.getItemsTypeByCategory, ItemsType.size(): " + ItemsType.size());
		
		if (ItemsType.size() != 0) // no match any products
		{
			this.items.clear();
			
			for (ItemType tItem : ItemsType)
			{
				ItemMBean itemMBean = super.mapItemTypeToItemMBean(tItem);
				this.items.add(itemMBean);
			}
			
			this.found = true;
		}
	}
	
	public String queryItemsByTitle(String title) throws AuctionServiceException
	{
		logger.info("TableItemMBean.queryItemsByTitle, title	:" + title);
		
		/* searchMBean.getInput(), Injection could be use if we do not have the parameter passed from the web
		 * for */
		this.ItemsType = this.itemRemote.getItemsTypeByTitle(title);
		
		if (ItemsType.size() != 0) // no match any products
		{
			this.items.clear();
			
			for (ItemType tItem : ItemsType)
			{
				ItemMBean itemMBean = super.mapItemTypeToItemMBean(tItem);
				this.items.add(itemMBean);
			}
			
			this.found = true;
		}
		else
		{
			this.found = false;
		}
		
		return "welcome";
	}
	
	public String displayItemById(String id)
	{
		logger.info("TableItemMBean.displayItemById, id:" + id);
		
		for (ItemMBean item : items)
		{
			if (item.getId().equals(id))
				this.tempItem = item;
		}
		
		/* Only one item in the collection- table */
		this.items.clear();
		this.items.add(this.tempItem);
		
		return "item";
	}
	
	public ArrayList<ItemMBean> getItems()
	{
		return items;
	}
	
	public void setItems(ArrayList<ItemMBean> items)
	{
		this.items = items;
	}
	
	public SearchMBean getSearchMBean()
	{
		return searchMBean;
	}
	
	public void setSearchMBean(SearchMBean searchMBean)
	{
		this.searchMBean = searchMBean;
	}
	
	public boolean isFound()
	{
		return found;
	}
	
	public void setFound(boolean found)
	{
		this.found = found;
	}
	
}
