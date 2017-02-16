
package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.jdap.auction.application.base.BaseItemMBean;
import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.item.ItemRemote;

@ManagedBean ( name = "tableCategories" )
@SessionScoped
public class TableCategoryMBean extends BaseItemMBean implements Serializable
{
	/* To diplay the table CategoryMBean */
	private ArrayList<CategoryMBean> categories = new ArrayList<CategoryMBean>();
	
	/* TO take the categories CategoryType */
	private List<CategoryType> CategoriesType = null;
	
	/** Field description */
	@ManagedProperty ( value = "#{tableItems}" )
	private TableItemMBean tableItemMBean;
	
	/** Field description */
	@EJB
	private ItemRemote itemRemote;
	
	public TableCategoryMBean() throws AuctionServiceException
	{
		logger.info("Creating Mbean TableCategoryMBean()2 ...");
		
		// queryCategories("");
	}
	
	/**
	 * The first call to the DB load all the categories from the root It will load sub categories
	 * (recursively) to this.CategoriesType as well I can reuse this function to load one level down the
	 * categories going to the DB But, SINCE we already have all the categories load in tCategoryType we will
	 * use a different fuction to load it from memory
	 * 
	 * @throws AuctionServiceException
	 */
	public void queryCategories() throws AuctionServiceException
	{
		logger.info("TableCategoryMBean.querycategory, no id");
		
		/* would need Id to get the subcateories by id from DB It would just load the categories form the
		 * root. */
		this.CategoriesType = this.itemRemote.getCategoriesType("");
		
		this.categories.clear();
		
		for (CategoryType tCategory : CategoriesType)
		{
			// Map one category one level at the time,
			CategoryMBean categoryMBean = super.mapCategoryTypeToCategoryMBean(tCategory);
			this.categories.add(categoryMBean);
		}
		
		logger.info("TableCategoryMBean.querycategory, + BEFORE queryItems().." );
		tableItemMBean.queryItems();
	}
	
	/* Load a sub category level at the time, ONE level down at the time taking all the subcategories from
	 * this.CategoriesType */
	public void queryCategories(String categotyid) throws AuctionServiceException
	{
		logger.info("TableCategoryMBean.querycategory, id: " + categotyid);

		List<CategoryType> subCategoriesType = null;
		ArrayList<CategoryMBean> tempCategories = new ArrayList<CategoryMBean>(this.categories);

		this.categories.clear();
		
		for (CategoryType tCategory : this.CategoriesType)
		{
			if (tCategory.getId().equals(categotyid))
			{
				subCategoriesType = tCategory.getSubcategory();
				
				for (CategoryType tSubCategory : subCategoriesType)
				{
					// Map one category one level at the time,
					CategoryMBean categoryMBean = super.mapCategoryTypeToCategoryMBean(tSubCategory);
					this.categories.add(categoryMBean);
				}
			}
		}

		// if it is the latest level keep it, in other way the list of categories will be reset
		if (this.categories.size() == 0)
		{
			this.categories = tempCategories;
		}
		
		// Reset the category to the lower level (sub category), clicked by the user
		this.CategoriesType = (subCategoriesType != null) ? subCategoriesType: this.CategoriesType;
		
		logger.info("TableCategoryMBean.querycategory, + BEFORE queryItemsByCategory.." );
		tableItemMBean.queryItemsByCategory(categotyid);
	}
	
	public ArrayList<CategoryMBean> getCategories()
	{
		return categories;
	}
	
	public void setCategories(ArrayList<CategoryMBean> categories)
	{
		this.categories = categories;
	}

	
	public TableItemMBean getTableItemMBean()
    {
    	return tableItemMBean;
    }

	public void setTableItemMBean(TableItemMBean tableItemMBean)
    {
    	this.tableItemMBean = tableItemMBean;
    }
	
}
