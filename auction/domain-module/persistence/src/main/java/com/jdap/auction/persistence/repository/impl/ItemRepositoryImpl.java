package com.jdap.auction.persistence.repository.impl;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.model.Category;
import com.jdap.auction.model.Item;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.repository.AbstractRepository;
import com.jdap.auction.persistence.exceptions.AuctionPersistenceException;
import com.jdap.auction.persistence.repository.ItemRepository;

//~--- JDK imports -------------------------------------------------------------

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.persistence.PersistenceException;

/**
 * Session Bean implementation class ItemBean
 */
@Stateless( name = "itemRepository", mappedName = "ejb.jdap.auction.service.ItemRepository" )
public class ItemRepositoryImpl extends AbstractRepository<Item> implements ItemRepository
{
    /** Field description */
    private static Logger logger = Logger.getLogger( ItemRepositoryImpl.class.getName() );

    /**
     * Constructs ...
     *
     */
    public ItemRepositoryImpl()
    {
        super( Item.class );
    }

    /**
     * Set the DaoFactory com.jdap.auction.persistence.dao.AuctionEjbDaoFactory it is a implementation of
     * DaoFactoryInterface
     *
     * @param daoFactory
     */
    @EJB( beanName = "auctionDaoFactory" )
    public void setEjbDaoFactory( DaoFactoryInterface daoFactory )
    {
        super.setDaoFactory( daoFactory );
    }

    /**
     * Method description
     *
     */
    @PostConstruct
    public void initialize()
    {
        logger.log( Level.INFO,
                "\n................Place Repository Initialize......................................\n" );
    }

    /**
     * Method description
     *
     *
     * @param item
     *
     * @return
     *
     * @throws PersistenceException
     */
    @Override
    public OID insertItem( Item item )		// throws AuctionPersistenceException
    {
        // logger.info( " Inserting Item..........."+ item.getId() );
        GenericDaoInterface<Item> daoItem = super.getDaoFactory().createDao( Item.class );

        daoItem.save( item );

        // logger.info( " Inserted Item : " + item.getId());
        return item.getId();
    }

    // another way of implement it

    /**
     * Method description
     *
     *
     * @param item
     *
     * @return
     */
    @Override
    public Item updateItem( Item item )
    {
        GenericDaoInterface<Item> daoItem = super.getDaoFactory().createDao( Item.class );
        Item dbItem = null;

        dbItem = findItemById( item.getId().toString() );
        dbItem.setTitle( item.getTitle() );
        dbItem.setDescription( item.getDescription() );
        dbItem.setAuctionDuration( item.getAuctionDuration() );
        dbItem.setInitialPrice( item.getInitialPrice() );
        dbItem.setReservedPrice( item.getReservedPrice() );
        dbItem.setBidStartDate( item.getBidStartDate() );
        dbItem.setBidEndDate( item.getBidEndDate() );
        dbItem.setItemCondition( item.getItemCondition() );
        dbItem.setPaymentMethod( item.getPaymentMethod() );
        dbItem.setPostDate( item.getPostDate() );	//
        dbItem.setPostageCost( item.getPostageCost() );
        dbItem.setBidStartDate( item.getBidStartDate() );
        dbItem.getCategories().clear();
        dbItem.getCategories().add( item.getCategories().get( 0 ) );
        daoItem.saveOrUpdate( dbItem );

        return dbItem;

        // daoItem.merge(dbItem);
        // daoItem.flush();
        // daoItem.refresh(dbItem);
    }

    // different way , we do not need to capture persistence and runtime exceptions the intercepter will

    /**
     * Method description
     *
     *
     * @param itemId
     */
    @Override
    public void deleteItem( String itemId )	// throws AuctionPersistenceException
    {
        GenericDaoInterface<Item> daoItem = super.getDaoFactory().createDao( Item.class );
        Item item = this.findItemById( itemId );

        daoItem.delete( item );
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public List<Item> getItems()
    {
        GenericDaoInterface<Item> daoItem = super.getDaoFactory().createDao( Item.class );
        String strQuery = "SELECT b FROM Item b";

        return daoItem.findByQuery( strQuery, new Object[ 0 ] );
    }

    /**
     * Method description
     * Recursively get the items than belong to any category hierarchy, Supercategory
     *
     * @param parentCategoryId
     *
     * @param parentCategory, Super Category
     *
     * @return
     */
    @Override
    public List<Item> getItemsByParentCategory( Category parentCategory )
    {
        GenericDaoInterface<Item> daoUser = super.getDaoFactory().createDao( Item.class );
        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put( "parentcategory", parentCategory );

        return daoUser.findByNamedQuery( "item.getItemsByparentcategory", param );
    }

    /**
     * Method description
     *
     *
     * @param category Items in the specific Category
     *
     * @return
     */
    @Override
    public List<Item> getItemsByCategory( Category category )
    {
        GenericDaoInterface<Item> daoUser = super.getDaoFactory().createDao( Item.class );
        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put( "category", category );

        return daoUser.findByNamedQuery( "item.getItemsBycategory", param );
    }

    /**
     * Method description
     *
     *
     * @param title
     *
     * @return
     */
    @Override
    public List<Item> getItemsByTitle( String title )
    {
        GenericDaoInterface<Item> daoUser = super.getDaoFactory().createDao( Item.class );
        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put( "title", "%" + title.toUpperCase() + "%" );

        return daoUser.findByNamedQuery( "item.getItemsBytitle", param );
    }

    /**
     * Method description
     *
     *
     * @param itemId
     *
     * @return
     */
    @Override
    public Item findItemById( String itemId )
    {
        // TODO Auto-generated method stub
        return super.findById( itemId );
    }

    /**
     * Method description
     *
     *
     * @param category
     *
     * @return
     */
    @Override
    public OID insertCategory( Category category )
    {
        GenericDaoInterface<Category> daoCategory = super.getDaoFactory().createDao( Category.class );

        daoCategory.save( category );

        return null;
    }

    /**
     * Method description
     *
     *
     * @param categoryId
     *
     * @return
     */
    @Override
    public Category findcategoryById( String categoryId )
    {
        GenericDaoInterface<Category> daoCategory = super.getDaoFactory().createDao( Category.class );
        Category type = null;

        try
        {
            type = daoCategory.findById( GUID.fromString( categoryId ) );

            if( type == null )
            {
                AuctionPersistenceException exception = new AuctionPersistenceException( "ID didn't found: "
                        + categoryId );

                throw exception;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();

            AuctionPersistenceException persistenceException = new AuctionPersistenceException( this.getClass()
                    .getName() + ", " + e.getMessage(), e.getCause() );

            throw persistenceException;
        }

        return type;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public List<Category> getRootCategories()
    {
        GenericDaoInterface<Category> daoCategory = super.getDaoFactory().createDao( Category.class );
        String strQuery = "SELECT c FROM Category c where c.parentCategory is null";

        return daoCategory.findByQuery( strQuery, new Object[ 0 ] );
    }

    /**
     * Method description
     *
     *
     * @param parentCategoryId
     *
     * @param parentCategory
     *
     * @return
     */
    @Override
    public List<Category> getCategoriesByParent( Category parentCategory )
    {
        GenericDaoInterface<Category> daoUser = super.getDaoFactory().createDao( Category.class );
        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put( "parentcategory", parentCategory );

        return daoUser.findByNamedQuery( "category.getCategoriesByParent", param );
    }
}
