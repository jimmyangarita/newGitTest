package com.jdap.auction.persistence.repository;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.model.Category;
import com.jdap.auction.model.Item;
import com.jdap.auction.patterns.OID;

//~--- JDK imports -------------------------------------------------------------

import java.util.List;

import javax.ejb.Local;

/**
 * Interface description
 *
 *
 * @version        9.0, 2014.December.08 04:32 PM
 * @author         Avocent Corporation
 */
@Local
public interface ItemRepository
{
    /**
     * Method description
     *
     *
     * @param item
     *
     * @return
     */
    public OID insertItem( Item item );		// throws AuctionPersistenceException;

    /**
     * Method description
     *
     *
     * @param item
     *
     * @return
     */
    public Item updateItem( Item item );

    /**
     * Method description
     *
     *
     * @param itemId
     */
    public void deleteItem( String itemId );	// throws Exception;

    // public void updateItemAmount(String itemId, Double newItemAmount);

    /**
     * Method description
     *
     *
     * @param item
     *
     * @return
     */
    public Item findItemById( String item );

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Item> getItems();

    /**
     * Method description
     *
     *
     * @param parentCategory
     *
     * @return
     */
    public List<Item> getItemsByParentCategory( Category parentCategory );

    /**
     * Method description
     *
     *
     * @param oid
     *
     * @param category
     *
     * @return
     */
    public List<Item> getItemsByCategory( Category category );

    
    public List<Item> getItemsByTitle( String title );
    
    // public List<Item> getHighItems(Double amountValue);

    /**
     * Method description
     *
     *
     * @param category
     *
     * @return
     */
    public OID insertCategory( Category category );

    /**
     * Method description
     *
     *
     * @param category
     *
     * @return
     */
    public Category findcategoryById( String category );

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Category> getRootCategories();

    /**
     * Method description
     *
     *
     * @param parentCategory
     *
     * @return
     */
    public List<Category> getCategoriesByParent( Category parentCategory );
}
