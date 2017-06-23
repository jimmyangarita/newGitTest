package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------



//~--- JDK imports -------------------------------------------------------------

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.28 02:31 PM
 * @author         JDAP Corporation
 */
@Entity
@DiscriminatorValue( value = "B" )
@PrimaryKeyJoinColumn( name = "OID", referencedColumnName = "OID" )
public class Bidder extends User
{
    /** Field description */
    private Double bidFrecuency;

    /** Field description */
    @OneToMany( mappedBy = "bidder", cascade = CascadeType.ALL )
    private List<Bid> bids;

    /** Field description */
    @OneToMany( mappedBy = "bidder", cascade = CascadeType.ALL )
    private List<Order> orders;

    /**
     * Method description
     *
     *
     * @return
     */
    public Double getBidFrecuency()
    {
        return bidFrecuency;
    }

    /**
     * Method description
     *
     *
     * @param bidFrecuency
     */
    public void setBidFrecuency( Double bidFrecuency )
    {
        this.bidFrecuency = bidFrecuency;
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
    public List<Order> getOrders()
    {
        return orders;
    }

    /**
     * Method description
     *
     *
     * @param orders
     */
    public void setOrders( List<Order> orders )
    {
        this.orders = orders;
    }
}
