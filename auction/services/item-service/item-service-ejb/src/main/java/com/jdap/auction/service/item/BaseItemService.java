
package com.jdap.auction.service.item;

// ~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.xml.fault.ApiFaultType;
import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.ItemType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.exceptions.AuctionServiceValidationException;
import com.jdap.auction.model.Category;
import com.jdap.auction.model.Item;
import com.jdap.auction.model.type.ItemCondition;
import com.jdap.auction.model.type.PaymentMethod;
import com.jdap.auction.persistence.repository.ItemRepository;
import com.jdap.auction.persistence.repository.UserRepository;
import com.jdap.auction.service.ActionInterceptorExceptions;
import com.jdap.auction.service.BaseActionService;

// ~--- JDK imports -------------------------------------------------------------

// ~--- non-JDK imports ---------------------------------------------------------
import java.math.BigInteger;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;

import javax.interceptor.Interceptors;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.May.06 11:57 AM
 * @author JDAP Corporation
 */
@Interceptors (
	{ActionInterceptorExceptions.class} )
public abstract class BaseItemService extends BaseActionService
{
	/** Field description */
	protected static final String SERVICE_NAME = "ItemService";
	
	/** Field description */
	protected static final String ERR_CODE_RESERVE_PRICE = "20002";
	
	/** Field description */
	protected static final String ERR_MSG_RESERVE_PRICE = " The reserve price has to be higer than the Initial Price";
	
	/** Field description */
	protected static final String ERR_CODE_ITEM_CONDITION = "20003";
	
	/** Field description */
	protected static final String ERR_MSG_ITEM_CONDITION = " Item condition is not a valid value";
	
	/** Field description */
	protected static final String ERR_CODE_PAYMENT_METHOD = "20004";
	
	/** Field description */
	protected static final String ERR_MSG_PAYMENT_METHOD = " Payment Method is not a valid Value";
	
	/** Field description */
	public static Logger logger = Logger.getLogger(BaseItemService.class.getName());
	
	/** Field description */
	@Resource ( name = "serviceTimeout" , mappedName = "com.jdap.auction.service.main.serviceTimeout" )
	protected Integer serviceTimeout;
	
	/** Field description */
	@EJB
	protected ItemRepository itemRepository;	// can be injected on the
	
	/** Field description */
	@EJB
	protected UserRepository userRepository;
	
	Throwable cause = null;
	
	// setBibEJB() in the ItemEjbBean
	@Resource
	SessionContext context;
	
	/** Field description */
	protected static final int CLIENT_CODE_STACK_INDEX;
	
	static
	{
		
		// Finds out the index of "this code" in the returned stack trace -
		// funny but it differs in JDK 1.5 and 1.6
		int i = 0;
		
		for (StackTraceElement ste : Thread.currentThread().getStackTrace())
		{
			i++;
			
			if (ste.getClassName().equals(BaseItemService.class.getName()))
			{
				break;
			}
		}
		
		CLIENT_CODE_STACK_INDEX = i;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param depth
	 * 
	 * @return
	 */
	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		
		// System.
		// out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
		// return ste[ste.length - depth].getMethodName(); //Wrong, fails for
		// depth = 0
		return ste[ste.length - 1 - depth].getMethodName();		// Thank you Tom
		
		// Tresansky
	}
	
	/**
	 * Map Enumeration "ItemCondition" From ItemType to Item in the Model
	 * 
	 * @param itemType
	 * @param itemModel
	 * 
	 * @throws AuctionServiceException
	 */
	private void mapItemTypetoItemModelCondition(ItemType itemType, Item itemModel) throws AuctionServiceException
	{
		String itemTypeCondition = "";
		
		try
		{
			itemTypeCondition = itemType.getCondition().value();
			itemModel.setItemCondition(ItemCondition.valueOf(itemTypeCondition));
		}
		catch (Exception ie)
		{
			ie.printStackTrace();
			cause = (ie.getCause() != null) ? ie.getCause() : ie;
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_ITEM_CONDITION, ERR_MSG_ITEM_CONDITION, ie
			                                .getMessage(), cause.getMessage(), itemTypeCondition);
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName()
			                + ".getItemCondition errorCode: " + ERR_CODE_ITEM_CONDITION, apiExceptionMsg);
		}
	}
	
	/**
	 * Map Enumeration "Item PaymentMethod" From ItemType to Item in the Model
	 * 
	 * @param itemType
	 * @param itemModel
	 * 
	 * @throws AuctionServiceException
	 */
	private void mapItemTypetoItemModelPaymentMethod(ItemType itemType, Item itemModel) throws AuctionServiceException
	{
		String ItemTypePaymentMethod = "";
		
		try
		{
			ItemTypePaymentMethod = itemType.getPaymentMethod().value();
			itemModel.setPaymentMethod(PaymentMethod.fromValue(ItemTypePaymentMethod));	// get Key
		}
		catch (Exception ie)
		{
			ie.printStackTrace();
			cause = (ie.getCause() != null) ? ie.getCause() : ie;
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_PAYMENT_METHOD, ERR_MSG_PAYMENT_METHOD, ie
			                                .getMessage(), cause.getClass().getName(), ItemTypePaymentMethod);
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ".getIPaymentMethod"
			                + ERR_CODE_PAYMENT_METHOD, apiExceptionMsg);
		}
	}
	
	/**
	 * recursive walk all the sub categories and return a a composition of all the sub categories, using
	 * recursion.
	 * 
	 * 
	 * @param categoryTypeParent
	 * @param categoryModelParent
	 * 
	 * @return
	 * @throws AuctionServiceException
	 * 
	 */
	protected List<Category> mapSubCategoryTypetoSubCategoryModel(CategoryType categoryTypeParent,
	                                                              Category categoryModelParent)
	    throws AuctionServiceException
	{
		List<Category> subCategoriesModel = new ArrayList<Category>();		// to void Concurrency
		Category categoryModel = null;
		List<Category> subSubCategoriesModel;
		
		for (CategoryType subCategoryType : categoryTypeParent.getSubcategory())
		{
			if ((null != subCategoryType.getName()) && !subCategoryType.getName().equals(""))		// to exit
			
			// recursion
			{
				logger.log(Level.INFO, "\n mapSubCategoryTypetoSubCategoryModel we got ItemType subcategory: "
				                + subCategoryType.getName());
				categoryModel = new Category();
				categoryModel.setName(subCategoryType.getName());
				
				String subcatDesc = (null != subCategoryType.getDescription())
				                ? (subCategoryType.getDescription().equals("")
				                                ? subCategoryType.getName()
				                                : subCategoryType.getDescription())
				                : subCategoryType.getName();
				
				categoryModel.setDescription(subcatDesc);
				
				/* logger.log(Level.INFO, "\n setParentCategory, categoryModelParent: " +
				 * categoryModelParent.getName()); */
				categoryModel.setParentCategory(categoryModelParent);
				subSubCategoriesModel = mapSubCategoryTypetoSubCategoryModel(subCategoryType, categoryModel);
				
				for (Category subSubCategoriyModel : subSubCategoriesModel)
				{
					
					// SubCategoryModel != object from categoryModel, to avoid concurrency
					categoryModel.getSubCategories().add(subSubCategoriyModel);
				}
			}
			
			subCategoriesModel.add(categoryModel);	// the first level of sub categories of the root Parent
			
			// Categories
		}
		
		return subCategoriesModel;
	}
	
	/**
	 * 
	 * Given the Item Type from APIS or Webs services recover Its Category and mapped to a ItemMode Category.
	 * subCategoriesModel == receive the the list of Sub categories with all its own sub categories included
	 * in the object, therefore all you need to do is add this node to the root of the Category tree
	 * "categoryModel"
	 * 
	 * @param itemType
	 * @param itemModel
	 * @throws AuctionServiceException
	 */
	protected void mapItemCategoryTypetoItemCategoryModel(ItemType itemType, Item itemModel)
	    throws AuctionServiceException
	{
		Category categoryModel;
		List<Category> subCategoriesModel;
		
		for (CategoryType categoryType : itemType.getCategory())
		{
			logger.log(Level.INFO, "\n into mapCategoryTypetoCategoryModel, categoryType.getName(): "
			                + categoryType.getName());
			validateCategory(categoryType);
			categoryModel = new Category();
			
			/* if (null != categoryType.getName() && !categoryType.getName().equals("")) { */
			categoryModel.setName(categoryType.getName());
			
			String catDesc = (null != categoryType.getDescription()) ? (categoryType.getDescription().equals("")
			                ? categoryType.getName()
			                : categoryType.getDescription()) : categoryType.getName();
			
			logger.log(Level.INFO, "categoryType.getDescription()" + catDesc);
			categoryModel.setDescription(catDesc);
			
			// categoryModel.setParentCategory(categoryModel); root Category do not need Parent
			subCategoriesModel = mapSubCategoryTypetoSubCategoryModel(categoryType, categoryModel);
			
			for (Category subCategoryModel : subCategoriesModel)
			{
				
				/* "subCategoriesModel" has to be != "categoryModel" , to avoid concurrency */
				categoryModel.getSubCategories().add(subCategoryModel);
			}
			
			// Making ManyToMany Between Items and categories
			itemModel.getCategories().add(categoryModel);
			categoryModel.getItems().add(itemModel);
		}
	}
	
	/**
	 * Map the ItemType coming from APIs WebServices, to Item in the domain Model
	 * 
	 * 
	 * @param itemType
	 * @param itemModel
	 * @param createCategories
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	protected Item mapItemTypetoItemModel(ItemType itemType, Item itemModel, boolean createCategories)
	    throws AuctionServiceException
	{
		// set Item categories
		if (createCategories)
		{
			mapItemCategoryTypetoItemCategoryModel(itemType, itemModel);
		}
		
		itemModel.setTitle(itemType.getTitle());
		itemModel.setDescription(itemType.getDescription());
		
		Integer auctionDuration = (itemType.getAuctionDuration() != null)
		                ? itemType.getAuctionDuration().intValue()
		                : 10;
		
		itemModel.setAuctionDuration(auctionDuration);
		itemModel.setInitialPrice(itemType.getInitialPrice());
		
		if (null != itemType.getReservePrice())
		{
			itemModel.setReservedPrice(itemType.getReservePrice());
		}
		
		itemModel.setBidStartDate(bUtil.getCurrentTimeStamp());
		
		Timestamp bidStartDate = (null != itemType.getStartTime()) ? new Timestamp(itemType.getStartTime()
		                .toGregorianCalendar().getTimeInMillis()) : bUtil.getCurrentTimeStamp();
		
		itemModel.setBidStartDate(bidStartDate);
		
		Timestamp bidEndDate = bUtil.getCurretTimestampPlusDays(auctionDuration);
		
		itemModel.setBidEndDate(bidEndDate);
		
		// Validate Enumerations, and add the values to itemModel
		mapItemTypetoItemModelCondition(itemType, itemModel);
		mapItemTypetoItemModelPaymentMethod(itemType, itemModel);
		
		// additional fields
		itemModel.setPostDate(bUtil.getCurrentTimeStamp());
		
		// /dbItem.setPostageCost
		
		return itemModel;
	}
	
	/**
	 * Using Recursion, walk all the sub categories and return a a composition of all the sub categories.
	 * Technically the categoryTypeParent is never used, it just in case you need to put the bidirectional
	 * relation to the Parent Category like we do in the CategoryModel with ItemModel
	 * 
	 * 
	 * @param categoryModelParent
	 * @param categoryTypeParent
	 * 
	 * @return
	 */
	protected List<CategoryType> mapSubCategoryModeltoSubCategoryType(Category categoryModelParent,
	                                                                  CategoryType categoryTypeParent)
	{
		List<CategoryType> subCategoriesType = new ArrayList<CategoryType>();		// to avoid Concurrency
		CategoryType categoryType = null;
		List<CategoryType> subSubCategoriesType;
		
		for (Category subCategoryModel : categoryModelParent.getSubCategories())
		{
			if ((null != subCategoryModel) && (null != subCategoryModel.getId()))
			{
				logger.log(Level.INFO, "\n mapSubCategoryModeltoSubCategoryType, we got subCategoryModel: "
				                + subCategoryModel.getId().toString());
				categoryType = new CategoryType();
				categoryType.setId(subCategoryModel.getId().toString());
				
				String subcatName = (subCategoryModel.getName() == null) ? "" : subCategoryModel.getName();
				
				categoryType.setName(subcatName);
				
				String subcatDesc = (subCategoryModel.getDescription() == null) ? subcatName : subCategoryModel
				                .getDescription();
				
				categoryType.setDescription(subcatDesc);
				categoryType.setCategory(false);
				subSubCategoriesType = mapSubCategoryModeltoSubCategoryType(subCategoryModel, categoryTypeParent);
				
				for (CategoryType subSubCategoryType : subSubCategoriesType)
				{
					logger.log(Level.INFO, "\n mapSubCategoryModeltoSubCategoryType, add subSubCategoryType: "
					                + subSubCategoryType.getName());
					categoryType.getSubcategory().add(subSubCategoryType);
				}
				
				/* the first level of sub categories to be added to the root Category */
				subCategoriesType.add(categoryType);
			}
		}
		
		return subCategoriesType;
	}
	
	/**
	 * Given the Item Type from Item Category in the Model map to APIS or Webs services ItemType -
	 * CategoryType
	 * 
	 * receive the the List of Sub categories with all its own sub sub... categories included in the object,
	 * therefore all you need to do is add this node to the root of the Category tree "categoryType". use
	 * different object for the return subCategoriesType and categoryType to avoid Concurrency
	 * 
	 * @param itemModel
	 * @param itemType
	 * @throws AuctionServiceException
	 */
	protected void mapItemCategoryModeltoItemCategoryType(Item itemModel, ItemType itemType)
	    throws AuctionServiceException
	{
		List<CategoryType> itemTypeCategories = itemType.getCategory();
		
		itemTypeCategories.clear();
		
		CategoryType categoryType;
		List<CategoryType> subCategoriesType;
		
		for (Category categoryModel : itemModel.getCategories())
		{
			
			// String categoriId = categoryModel.getId().toString() ? null
			logger.log(Level.INFO, "\n into mapItemCategoryModeltoItemCategoryType, categoryModel: "
			                + categoryModel.getId().toString());
			categoryType = new CategoryType();
			
			if ((null != categoryModel) && (null != categoryModel.getId()) && (null != categoryModel.getName()))
			{
				categoryType.setId(categoryModel.getId().toString());
				categoryType.setName(categoryModel.getName());
				
				String catDesc = (categoryModel.getDescription() == null) ? categoryModel.getName() : categoryModel
				                .getDescription();
				
				categoryType.setDescription(catDesc);
				categoryType.setCategory(true);
				
				// categoryModel.setParentCategory(categoryModel); Root category do not need parent Category
				subCategoriesType = mapSubCategoryModeltoSubCategoryType(categoryModel, categoryType);
				
				for (CategoryType subCategoryType : subCategoriesType)
				{
					categoryType.getSubcategory().add(subCategoryType);
				}
				
				itemTypeCategories.add(categoryType);
			}
		}
	}
	
	/**
	 * Map Item in the domain Model to the ItemType going to APIs WebServices.
	 * 
	 * 
	 * @param itemModel
	 * @param itemType
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	protected ItemType mapItemModeltoItemType(Item itemModel, ItemType itemType) throws AuctionServiceException
	{
		logger.log(Level.INFO, "\n\n into mapItemModeltoItemType, \n");
		
		// map the ItemCategories Model to types
		mapItemCategoryModeltoItemCategoryType(itemModel, itemType);
		
		if (null != itemModel.getId())
		{
			itemType.setId(itemModel.getIdAsString());
		}
		
		if (null != itemModel.getSeller())
		{
			itemType.setSellerId(itemModel.getSeller().getId().toString());
		}
		
		itemType.setTitle(itemModel.getTitle());
		itemType.setDescription(itemModel.getDescription());
		itemType.setInitialPrice(itemModel.getInitialPrice());
		itemType.setReservePrice(itemModel.getReservedPrice());
		
		String itemModelCondition = itemModel.getItemCondition().toString();
		
		itemType.setCondition(com.jdap.auction.common.xml.item.ItemCondition.valueOf(itemModelCondition));
		
		String itemModelPaymentMethod = itemModel.getPaymentMethod().getValue();	// get value
		
		itemType.setPaymentMethod(com.jdap.auction.common.xml.item.PaymentMethod.valueOf(itemModelPaymentMethod));
		itemType.setPostageCost(new Double(itemModel.getPostageCost()).toString());
		itemType.setAuctionDuration(BigInteger.valueOf(itemModel.getAuctionDuration().intValue()));
		
		XMLGregorianCalendar xmlgc = bUtil.converToXmlCurrentime(itemModel.getBidStartDate());
		
		itemType.setStartTime(xmlgc);
		
		return itemType;
	}
	
	protected void mapCategoryModeltoCategoryType(Category categoryModel, CategoryType categoryType)
	    throws AuctionServiceException
	{
		// String categoriId = categoryModel.getId().toString() ? null
		logger.log(Level.INFO, "\n into mapItemCategoryModeltoItemCategoryType(Category categoryModel, "
		                + "CategoryType categoryType) " + categoryModel.getId().toString());
		
		if ((null != categoryModel) && (null != categoryModel.getId()) && (null != categoryModel.getName()))
		{
			categoryType.setId(categoryModel.getId().toString());
			categoryType.setName(categoryModel.getName());
			
			String catDesc = (categoryModel.getDescription() == null) ? categoryModel.getName() : categoryModel
			                .getDescription();
			
			categoryType.setDescription(catDesc);
			
			if (null == categoryModel.getParentCategory())
			{
				categoryType.setCategory(true);
			}
			else
			{
				categoryType.setCategory(false);
			}
			
			if (null != categoryModel.getSubCategories())
			{
				logger.log(Level.INFO, "mapCategoryModeltoCategoryType, before "
				                + "mapSubCategoryModeltoSubCategoryType, CategoryName: " + categoryModel.getName());
				
				List<CategoryType> categoriesType = mapSubCategoryModeltoSubCategoryType(categoryModel, categoryType);
				
				for (CategoryType tCategory : categoriesType)
				{
					categoryType.getSubcategory().add(tCategory);
				}
			}
			
		}
	}
	
	/**
	 * Validate just the Id
	 * 
	 * @param itemType
	 * @throws AuctionServiceException
	 */
	protected void validateItemId(ItemType itemType) throws AuctionServiceException
	{
		if (null != itemType.getId())
		{
			super.validateId(SERVICE_NAME, itemType.getId());
		}
		else
		{
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
			                		"Item Id is Null", "", "");
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
			                + ERR_NULL_PARAMETERS, apiExceptionMsg);
		}
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param itemType
	 * @param createCategory
	 * 
	 * @throws AuctionServiceException
	 */
	protected void validateItemTypeRefId(ItemType itemType, boolean createCategory) throws AuctionServiceException
	{
		boolean exception = false;
		
		if (null != itemType.getSellerId())
		{
			super.validateId(SERVICE_NAME, itemType.getSellerId());
		}
		else
		{
			exception = true;
		}
		
		// If you do not have to create the category is because the category is already in the DB
		if (!createCategory)
		{
			// so validate the OID passed by the client.
			if ((null != itemType.getCategory()) && (null != itemType.getCategory().get(0))
			                && (null != itemType.getCategory().get(0).getId()))
			{
				super.validateId(SERVICE_NAME, itemType.getCategory().get(0).getId());
			}
			else
			{
				exception = true;
			}
		}
		
		if (exception)
		{
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
			                		"Item References ids are null", "", "");
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
			                + ERR_NULL_PARAMETERS, apiExceptionMsg);
		}
	}
	
	/**
	 * Validates the basic Item fields, Name and Description, and when need it Validate ID Also call price
	 * validation for the item
	 * 
	 * @param itemType
	 * @param validateItemId
	 * @throws AuctionServiceException
	 * 
	 * 
	 */
	protected void validateItemType(ItemType itemType, boolean validateItemId) throws AuctionServiceException
	{
		boolean exception = false;
		
		if (validateItemId)
		{
			this.validateItemId(itemType);
		}
		
		if ((!super.bUtil.textHasContent(itemType.getTitle()))
		                || (!super.bUtil.textHasContent(itemType.getDescription())))
		{
			exception = true;
		}
		
		/* The Item must belong to a Category */
		if ((null == itemType.getCategory()) || (itemType.getCategory().size() == 0)
		                || (null == itemType.getCategory().get(0).getId())
		                || !super.bUtil.textHasContent(itemType.getCategory().get(0).getId()))
		{
			exception = true;
		}
		
		if (exception)
		{
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
			                		"Item basic values are null", "name or description are null", "");
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
			                + ERR_NULL_PARAMETERS, apiExceptionMsg);
		}
		
		validateInitialPrice(itemType.getInitialPrice(), itemType.getReservePrice());
	}
	
	/**
	 * 
	 * @param initialPrice
	 * @param reservePrice
	 * @throws AuctionServiceException
	 */
	protected void validateInitialPrice(Double initialPrice, Double reservePrice) throws AuctionServiceException
	{
		super.validatePrice(SERVICE_NAME, initialPrice);
		
		if ((null != initialPrice) && (null != reservePrice) && (reservePrice <= initialPrice))
		{
			ApiFaultType apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_RESERVE_PRICE, ERR_MSG_RESERVE_PRICE,
			                		"reserve price is not higher than initial price", "ADF-AuctionServiceValidationException",
			                		"initialPrice " + initialPrice + ", reservePrice " + reservePrice);
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
			                + ERR_CODE_RESERVE_PRICE, apiExceptionMsg);
		}
	}
	
	/**
	 * The minimum values for the Category, only when we need to create category
	 * 
	 * @param categoryType
	 * @throws AuctionServiceException
	 */
	protected void validateCategory(CategoryType categoryType) throws AuctionServiceException
	{
		if ((null == categoryType.getName()) || categoryType.getName().equals(""))
		{
			apiExceptionMsg = bUtil
			                .buildApiExceptionMsg(SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
			                		"Categories basic values are null", "name or description are null", "");
			
			throw new AuctionServiceValidationException(this.getClass().getSimpleName() + ", ERROR: "
			                + ERR_NULL_PARAMETERS, apiExceptionMsg);
		}
	}
}
