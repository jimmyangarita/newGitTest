package com.jdap.auction.service.item;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.ItemType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.exceptions.AuctionServiceValidationException;
import com.jdap.auction.model.Category;
import com.jdap.auction.model.Item;
import com.jdap.auction.model.Seller;
import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.service.ActionInterceptorExceptions;

//~--- JDK imports -------------------------------------------------------------

//import com.jdap.auction.common.xml.apiconfiguration.AddItemResponseType;

//import com.jdap.auction.common.xml.apiconfiguration.AddItemType;
//import com.jdap.auction.common.xml.apiconfiguration.RuntimeFaultMessageType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;

import javax.ejb.Stateless;

import javax.interceptor.Interceptors;

/**
 * Session Bean implementation class ItemEjbBean
 */
@Stateless( name = "ItemEJB", mappedName = "ejb.jdap.auction.service.itemEJB" )
@Interceptors( { ActionInterceptorExceptions.class } )
public class ItemEjbBean extends BaseItemService implements ItemRemote, ItemLocal
{
    /**
     * Default constructor.
     */
    public ItemEjbBean()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * Method description
     *
     */
    @PostConstruct
    public void initialize()
    {
        logger.log( Level.WARNING, "\n\n...................ItemBean Initialize........................\n\n" );
    }

    /*
     *  @EJB( beanName = "ItemRepository" ) // another way of injection public void setitemRepository(
     * ItemLocal itemRepository ) { this.itemRepository = itemRepository; }
     */

    /**
     *
     *
     * @param newItem
     * @param createCategories
     *
     * @return
     *
     * @throws AuctionServiceException
     */

    // @RolesAllowed("sysroleplatformmss")
    // @PermitAll

    /**
     *
     */
    @Override
    public ItemType addItem( ItemType newItem, boolean createCategories ) throws AuctionServiceException
    {
        logger.info( "\n\nINTO ItemEjbBean, PRINCIPAL :: " + context.getCallerPrincipal().getName() );
        logger.info( "\n\nINTO ItemEjbBean, createCategories :: " + createCategories );

        // boolean createCategories = false;
        boolean validateItemId = false;
        Item itemModel = new Item();

        /* validation and transformation */
        validateItemType( newItem, validateItemId );
        validateItemTypeRefId( newItem, createCategories );
        mapItemTypetoItemModel( newItem, itemModel, createCategories );

        Seller seller = (Seller) userRepository.findUserById( newItem.getSellerId() );

        itemModel.setSeller( seller );

        /* Just map the category to the Item, since the Category already exist in the DB */
        if( !createCategories )
        {
            logger.info( "Step50" );

            String categoryId = newItem.getCategory().get( 0 ).getId();
            Category categoryModel = itemRepository.findcategoryById( categoryId );

            // Making ManyToMany Between Items and categories
            itemModel.getCategories().add( categoryModel );

            // categoryModel.getItems().add(itemModel);
        }

        /*
         *  We rely on JPA Persist Cascade to create the Category instead of calling insert Category and then
         * insert Item
         */
        itemRepository.insertItem( itemModel );
        logger.log( Level.INFO, "\n End Insert new item : " + itemModel.getId() );
        mapItemModeltoItemType( itemModel, newItem );

        return newItem;
    }

    /**
     *
     *
     * @param itemType
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @Override
    public ItemType updateItem( ItemType itemType ) throws AuctionServiceException
    {
        boolean createCategoies = false;
        boolean validateItemId = true;
        Item itemModel = new Item();

        /* validation and transformation */
        validateItemType( itemType, validateItemId );
        mapItemTypetoItemModel( itemType, itemModel, createCategoies );

        // Should I change the category for an Item??
        if( !createCategoies )
        {
            String categoryId = itemType.getCategory().get( 0 ).getId();
            Category categoryModel = itemRepository.findcategoryById( categoryId );

            /* ManytoMany */
            itemModel.getCategories().add( categoryModel );

            // categoryModel.getItems().add(itemModel);
        }

        OID oidItemModel = GUID.fromString( itemType.getId() );

        itemModel.setId( oidItemModel );

        Item dbItemModel = itemRepository.updateItem( itemModel );

        /* map model to type */
        mapItemModeltoItemType( dbItemModel, itemType );
        logger.log( Level.INFO, "End: updateItem" + itemType.getId() );

        return itemType;
    }

    /**
     *
     *
     * @param item
     *
     * @throws AuctionServiceException
     */
    @Override
    public void deleteItem( ItemType item ) throws AuctionServiceException
    {
        validateItemId( item );
        itemRepository.deleteItem( item.getId() );
        logger.log( Level.INFO, "End: delete item" + item.getId() );
    }

    /**
     *
     *
     * @param itemId
     * @param newItemPrice
     *
     * @throws AuctionServiceException
     */
    @Override
    public void changeItemAmount( String itemId, Double newItemPrice ) throws AuctionServiceException
    {
        logger.log( Level.INFO, "Start: change item amount for item: " + itemId );

        if( ( null == newItemPrice ) || ( null == itemId ) || ( itemId.equals( "" ) ) )
        {

            /** Field description */
            apiExceptionMsg = bUtil.buildApiExceptionMsg( SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
                    "", "", "" );

            throw new AuctionServiceValidationException( this.getClass().getName() + ".changeItemAmount"
                    + ERR_NULL_PARAMETERS, apiExceptionMsg );
        }

        validateInitialPrice( newItemPrice, null );

        // itemRepository.updateItemAmount(itemId, newItemPrice);
        logger.log( Level.INFO, "End: change Item Amount item" + itemId );
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @Override
    public List<Item> getItems() throws AuctionServiceException
    {
        logger.log( Level.INFO, "BEGIN: getAllItems item" );

        List<Item> allItems = itemRepository.getItems();

        for( Item bis : allItems )
        {
            logger.log( Level.INFO, "getAllItems:" + bis.toString() );
        }

        return allItems;
    }
    
      
    /**
     * Method description
     *
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @Override
    public List<ItemType> getItemsType() throws AuctionServiceException
    {
        logger.log( Level.INFO, "BEGIN: getItemsType----------" );

        List<Item> items = itemRepository.getItems();
        List<ItemType> ItemsType = new ArrayList<ItemType>();

        for( Item item : items )
        {
            logger.log( Level.INFO, "ItemEjbBean.getItems():" + item.toString() );

            ItemType itemType = new ItemType();

            mapItemModeltoItemType( item, itemType );
            ItemsType.add( itemType );
        }

        return ItemsType;
    }
    
    @Override
    public List<ItemType> getItemsTypeByCategory(String parentcategoryid) throws AuctionServiceException
    {
        logger.log( Level.INFO, "BEGIN: getItemsTypeByCategory parentcategoryid: " +parentcategoryid );
        
        Category categoryParent = null;
        List<ItemType> itemsType = new ArrayList<ItemType>();
        ItemType itemType = null;
        

        if( ( null != parentcategoryid ) && !parentcategoryid.equals( "" ) )
        {
			categoryParent = itemRepository.findcategoryById( parentcategoryid );
			
			/*Super Category*/
            List<Item> itemsModel = itemRepository.getItemsByParentCategory(categoryParent);
            /* If the Item does not have any super category return the category directly */
            if (itemsModel.size() == 0)
            	itemsModel = itemRepository.getItemsByCategory(categoryParent);
            
            for( Item itemModel : itemsModel )
            {
            	logger.log( Level.INFO, "getItemsType() :" +  itemModel.getId().toString() );
            
            	itemType = new ItemType();
            
            	super.mapItemModeltoItemType(itemModel, itemType);
            	itemsType.add(itemType);
            }
        }
        else
        {
        	throw new AuctionServiceException("The categoryid can't be null or empty!");
        }
        
        return itemsType;
        
    }
    
    @Override
    public List<ItemType> getItemsTypeByTitle(String title) throws AuctionServiceException
    {
        logger.log( Level.INFO, "getItemsTypeByTitle"+  title);

        List<ItemType> itemsType = new ArrayList<ItemType>();
        ItemType itemType = null;
        
        if( ( null != title ) && !title.equals( "" ) )
        {
            List<Item> itemsModel = itemRepository.getItemsByTitle(title);
            
            for( Item itemModel : itemsModel )
            {
            	logger.log( Level.INFO, "getItemsType() :" +  itemModel.getId().toString() );
            
            	itemType = new ItemType();
            
            	super.mapItemModeltoItemType(itemModel, itemType);
            	itemsType.add(itemType);
            }
        }
        /*else
        {
        	throw new AuctionServiceException("The title can't be null or empty!");
        }*/
        
        return itemsType;
        
    }

    /**
     * Method description
     *
     *
     * @param higherThan
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @Override
    public List<Item> getHigherItems( Double higherThan ) throws AuctionServiceException
    {
        logger.log( Level.INFO, "BEGIN: getHigherItems item" );
        validateInitialPrice( higherThan, null );

        List<Item> highItems = null;	// itemRepository.getHighItems(higherThan);

        /* for (Item bis: highItems) { logger.log(Level.INFO, "getHigherItems:"+ bis.getId() ); } */
        return highItems;
    }

    /**
     * Method description
     *
     *
     * @param parentCategoryid
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @Override
    public List<CategoryType> getCategoriesType( String parentCategoryid ) throws AuctionServiceException
    {
        List<Category> categories = null;
        Category categoryParent = null;
        
        List<CategoryType> CategoriesType = new ArrayList<CategoryType>();
        CategoryType categoryType = null;

        if( ( null != parentCategoryid ) &&!parentCategoryid.equals( "" ) )
        {
            categoryParent = itemRepository.findcategoryById( parentCategoryid );
            categories = itemRepository.getCategoriesByParent( categoryParent );
        }
        else
        {
            categories = itemRepository.getRootCategories();
        }

        for( Category category : categories )
        {
            logger.log( Level.INFO, "ItemEjbBean.getCategories():" + category.getName() );

            categoryType = new CategoryType();

            mapCategoryModeltoCategoryType( category, categoryType );
            CategoriesType.add( categoryType );
        }

        return CategoriesType;
    }
}
