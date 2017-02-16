package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.model.type.ItemCondition;
import com.jdap.auction.model.type.PaymentMethod;

//~--- non-JDK imports ---------------------------------------------------------
import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

//~--- JDK imports -------------------------------------------------------------

//~--- JDK imports -------------------------------------------------------------
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class description
 *
 *
 * @version 9.0, 2013.February.27 11:29 AM
 * @author JDAP Corporation
 */
@Entity
@NamedQueries( { @NamedQuery( name = "item.getItemsByparentcategory",
                              query = "SELECT i FROM  Item i JOIN i.categories c WHERE c.parentCategory = :parentcategory" ) ,
                 @NamedQuery( name = "item.getItemsBycategory",
                              query = "SELECT i FROM  Item i WHERE i.categories = :category" ),
                 @NamedQuery( name = "item.getItemsBytitle",
                              query = "SELECT i FROM  Item i WHERE UPPER(i.title) LIKE :title" )} )
public class Item extends AbstractPersistentObject
{
    /** Field description */
    private String title;

    /** Field description */
    @Column( length = 2000 )
    private String description;

    /** Field description */
    @Temporal( TemporalType.TIMESTAMP )
    private Date postDate;

    /** Field description */
    private double initialPrice;

    /** Field description */
    private double reservedPrice;

    /** Field description for Cost of listing the Article */
    private double postageCost;

    /** Field description */
    @Enumerated( EnumType.STRING )
    private ItemCondition itemCondition;

    /** Field description */
    @Enumerated( EnumType.STRING )

    // @Column( name = "PAYMENTMETHOD", nullable = false )
    private PaymentMethod paymentMethod;

    /** Field description */
    private Integer auctionDuration;

    /** Field description */
    @Column( name = "PICTURE" )
    @Lob
    @Basic( fetch = FetchType.LAZY )
    private byte[] picture;

    /** Field description */
    @Temporal( TemporalType.TIMESTAMP )
    private Date bidStartDate;

    /** Field description */
    @Temporal( TemporalType.TIMESTAMP )
    private Date bidEndDate;

    /** Field description */
    @ManyToOne( optional = false )
    @JoinColumn( name = "sellerid", referencedColumnName = "oid" )
    private Seller seller;

    /** Field description */
    @ManyToOne
    @JoinColumn( name = "orderid", referencedColumnName = "oid" )
    private Order order;

    /** Field description */
    @OneToMany( mappedBy = "item", cascade = CascadeType.REMOVE )
    private List<Bid> bids;

    /** Field description */
    @ManyToMany( cascade = CascadeType.PERSIST )	// just to create the categories when the Item come with one
    @JoinTable(
        name = "item_category",
        joinColumns = @JoinColumn( name = "itemid", referencedColumnName = "oid" ) ,
        inverseJoinColumns = @JoinColumn( name = "categoryid", referencedColumnName = "oid" )
    )
    private List<Category> categories;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Method description
     *
     *
     * @param title
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method description
     *
     *
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getPostDate()
    {
        return postDate;
    }

    /**
     * Method description
     *
     *
     * @param postDate
     */
    public void setPostDate( Date postDate )
    {
        this.postDate = postDate;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Seller getSeller()
    {
        return seller;
    }

    /**
     * Method description
     *
     *
     * @param seller
     */
    public void setSeller( Seller seller )
    {
        this.seller = seller;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Bid> getBids()
    {
        return bids;
    }

    /**
     * Method description
     *
     *
     * @param bids
     */
    public void setBids( List<Bid> bids )
    {
        this.bids = bids;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public double getInitialPrice()
    {
        return initialPrice;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public byte[] getPicture()
    {
        return picture;
    }

    /**
     * Method description
     *
     *
     * @param picture
     */
    public void setPicture( byte[] picture )
    {
        this.picture = picture;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Order getOrder()
    {
        return order;
    }

    /**
     * Method description
     *
     *
     * @param order
     */
    public void setOrder( Order order )
    {
        this.order = order;
    }

    /**
     * Method description
     *
     *
     * @param initialPrice
     */
    public void setInitialPrice( double initialPrice )
    {
        this.initialPrice = initialPrice;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getBidStartDate()
    {
        return bidStartDate;
    }

    /**
     * Method description
     *
     *
     * @param bidStartDate
     */
    public void setBidStartDate( Date bidStartDate )
    {
        this.bidStartDate = bidStartDate;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getBidEndDate()
    {
        return bidEndDate;
    }

    /**
     * Method description
     *
     *
     * @param bidEndDate
     */
    public void setBidEndDate( Date bidEndDate )
    {
        this.bidEndDate = bidEndDate;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Category> getCategories()
    {
        if( categories == null )
        {
            categories = new ArrayList<Category>();
        }

        return this.categories;
    }

    /**
     * Method description
     *
     *
     * @param categories
     */
    public void setCategories( List<Category> categories )
    {
        this.categories = categories;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public double getReservedPrice()
    {
        return reservedPrice;
    }

    /**
     * Method description
     *
     *
     * @param reservedPrice
     */
    public void setReservedPrice( double reservedPrice )
    {
        this.reservedPrice = reservedPrice;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public double getPostageCost()
    {
        return postageCost;
    }

    /**
     * Method description
     *
     *
     * @param postageCost
     */
    public void setPostageCost( double postageCost )
    {
        this.postageCost = postageCost;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public ItemCondition getItemCondition()
    {
        return itemCondition;
    }

    /**
     * Method description
     *
     *
     * @param itemCondition
     */
    public void setItemCondition( ItemCondition itemCondition )
    {
        this.itemCondition = itemCondition;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * Method description
     *
     *
     * @param paymentMethod
     */
    public void setPaymentMethod( PaymentMethod paymentMethod )
    {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAuctionDuration()
    {
        return auctionDuration;
    }

    /**
     * Method description
     *
     *
     * @param auctionDuration
     */
    public void setAuctionDuration( Integer auctionDuration )
    {
        this.auctionDuration = auctionDuration;
    }
}
