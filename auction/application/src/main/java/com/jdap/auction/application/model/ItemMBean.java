package com.jdap.auction.application.model;

//~--- non-JDK imports ---------------------------------------------------------

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.jdap.auction.common.xml.item.CategoryType;
import com.jdap.auction.common.xml.item.ItemCondition;
import com.jdap.auction.common.xml.item.PaymentMethod;

/**
 * Class description
 *
 *
 * @version        9.0, 2014.October.31 12:07 PM
 * @author         jdap Corporation    
 */
public class ItemMBean
{
    /** Field description */
    protected String id;

    /** Field description */
    protected String sellerId;

    /** Field description */
    protected String title;

    /** Field description */
    protected String description;

    /** Field description */
    protected List<CategoryType> category;

    /** Field description */
    protected double initialPrice;

    /** Field description */
    protected Double reservePrice;

    /** Field description */
    protected ItemCondition condition;

    /** Field description */
    protected PaymentMethod paymentMethod;

    /** Field description */
    protected BigInteger auctionDuration;

    /** Field description */
    protected XMLGregorianCalendar startTime;

    /** Field description */
    protected String postageCost;

    
    
    public ItemMBean()
    {
	    super();
	    // TODO Auto-generated constructor stub
    }
    
    public ItemMBean(String id, String title, String description, Double initialPrice, ItemCondition itemCondition)
    {
	    this.id = id;
	    this.title = title;
	    this.description = description;
	    this.initialPrice = initialPrice;	
	    this.condition = itemCondition;
    }
    

	public ItemMBean(String id, String sellerId, String title, String description, List<CategoryType> category,
                    double initialPrice, Double reservePrice, ItemCondition condition, PaymentMethod paymentMethod,
                    BigInteger auctionDuration, XMLGregorianCalendar startTime, String postageCost)
    {
	    super();
	    this.id = id;
	    this.sellerId = sellerId;
	    this.title = title;
	    this.description = description;
	    this.category = category;
	    this.initialPrice = initialPrice;
	    this.reservePrice = reservePrice;
	    this.condition = condition;
	    this.paymentMethod = paymentMethod;
	    this.auctionDuration = auctionDuration;
	    this.startTime = startTime;
	    this.postageCost = postageCost;
    }
	



	/**
     * Method description
     *
     *
     * @return
     */
    public String getId()
    {
        return id;
    }

    /**
     * Method description
     *
     *
     * @param id
     */
    public void setId( String id )
    {
        this.id = id;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getSellerId()
    {
        return sellerId;
    }

    /**
     * Method description
     *
     *
     * @param sellerId
     */
    public void setSellerId( String sellerId )
    {
        this.sellerId = sellerId;
    }

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
    public List<CategoryType> getCategory()
    {
        return category;
    }

    /**
     * Method description
     *
     *
     * @param category
     */
    public void setCategory( List<CategoryType> category )
    {
        this.category = category;
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
    public Double getReservePrice()
    {
        return reservePrice;
    }

    /**
     * Method description
     *
     *
     * @param reservePrice
     */
    public void setReservePrice( Double reservePrice )
    {
        this.reservePrice = reservePrice;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public ItemCondition getCondition()
    {
        return condition;
    }

    /**
     * Method description
     *
     *
     * @param condition
     */
    public void setCondition( ItemCondition condition )
    {
        this.condition = condition;
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
    public BigInteger getAuctionDuration()
    {
        return auctionDuration;
    }

    /**
     * Method description
     *
     *
     * @param auctionDuration
     */
    public void setAuctionDuration( BigInteger auctionDuration )
    {
        this.auctionDuration = auctionDuration;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public XMLGregorianCalendar getStartTime()
    {
        return startTime;
    }

    /**
     * Method description
     *
     *
     * @param startTime
     */
    public void setStartTime( XMLGregorianCalendar startTime )
    {
        this.startTime = startTime;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPostageCost()
    {
        return postageCost;
    }

    /**
     * Method description
     *
     *
     * @param postageCost
     */
    public void setPostageCost( String postageCost )
    {
        this.postageCost = postageCost;
    }
}
