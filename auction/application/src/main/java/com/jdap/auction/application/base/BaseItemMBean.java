package com.jdap.auction.application.base;

import java.util.List;

import com.jdap.auction.application.model.CategoryMBean;
import com.jdap.auction.application.model.ItemMBean;
import com.jdap.auction.common.xml.item.ItemCondition;
import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.ItemType;

public abstract class BaseItemMBean extends BaseMBean
{

	/**
	 * Map Category Type (JAXB) to Category MBean(Apps)
	 * map only the category no subcategories
	 * @param tCategory
	 * @return
	 */
	protected CategoryMBean mapCategoryTypeToCategoryMBean(CategoryType tCategory)
	{
		CategoryMBean categoryMBean = null;
		
		String id = tCategory.getId();
		String name = tCategory.getName();//.toLowerCase();
		String desc = tCategory.getDescription();
		boolean rootCategory = tCategory.isCategory();
		List<CategoryType> subCategories = tCategory.getSubcategory();

		categoryMBean = new CategoryMBean(id,rootCategory,name,desc, null);
		
		return categoryMBean;
	}
	
	protected ItemMBean mapItemTypeToItemMBean(ItemType itemType)
	{
		ItemMBean itemMBean = null;
		
		String id = itemType.getId();
		itemType.getCategory();
		String title = itemType.getTitle();
		String description = itemType.getDescription();
		Double initialPrice = itemType.getInitialPrice();
		ItemCondition itemCondition = itemType.getCondition();
		
		itemMBean = new ItemMBean(id,title,description,initialPrice, itemCondition);
		
		return itemMBean;
	}
	
	/**
	 * Map the Category MBean (Apps) to the the Category Type (JAXB object)
	 * 
	 * @param categoryMBean
	 * @param tCategory
	 * @return
	 */
	/*protected <T extends CategoryType> T mapCategoryMBeanToCategoryType(CategoryMBean categoryMBean, T tCategory)
	{
		// SellerType tCategory = new SellerType();
		AddressType tAddress = new AddressType();
		
		tCategory.setId(categoryMBean.getId());
		tCategory.setCategorytype(CategoryKind.fromValue(categoryMBean.getCategoryKind()));
		tCategory.setFirstName(categoryMBean.getFirstName());
		tCategory.setLastName(categoryMBean.getLastName());
		tCategory.setEmail(categoryMBean.getCategoryName());
		tCategory.setCategoryname(categoryMBean.getCategoryName());
		tCategory.setPassword(categoryMBean.getPassword());
		tCategory.setPhone(new Long (categoryMBean.getPhone()).toString());
		
		if (null != categoryMBean.getAddressMBean())
		{
			AddressMBean addressBean = categoryMBean.getAddressMBean();
			tAddress.setLine1(addressBean.getLine1());
			tAddress.setLine2(addressBean.getLine2());
			tAddress.setCountry(addressBean.getCountry());
			tAddress.setState(addressBean.getState());
			tAddress.setCity(addressBean.getCity());
			tAddress.setZip(addressBean.getZipcode());
		}
		
		tCategory.setAddress(tAddress);
		
		return tCategory;
	}*/
	
}
