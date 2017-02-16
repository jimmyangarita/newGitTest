package com.jdap.auction.model;

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
 * @version        9.0, 2013.February.27 02:53 PM
 * @author         JPA Corporation
 */
@Entity
@DiscriminatorValue( value = "S" )
@PrimaryKeyJoinColumn( name = "OID", referencedColumnName = "OID" )
public class Seller extends User
{
    /** Field description */
    private double creditWorth;

    /** Field description */
    @OneToMany( mappedBy = "seller", cascade = CascadeType.REMOVE )
    private List<Item> item;

    /**
     * Method description
     *
     *
     * @return
     */
    public double getCreditWorth()
    {
        return creditWorth;
    }

    /**
     * Method description
     *
     *
     * @param creditWorth
     */
    public void setCreditWorth( double creditWorth )
    {
        this.creditWorth = creditWorth;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Item> getItem()
    {
        return item;
    }

    /**
     * Method description
     *
     *
     * @param item
     */
    public void setItem( List<Item> item )
    {
        this.item = item;
    }
}
