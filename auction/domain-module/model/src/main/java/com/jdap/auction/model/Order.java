	package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

//~--- JDK imports -------------------------------------------------------------

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.28 02:09 PM
 * @author         JDAP Corporation    
 */
@Entity
@Table(name="ORDERS")
public class Order extends AbstractPersistentObject
{
    /** Field description */
    @Temporal( TemporalType.TIMESTAMP )
    private Date createDate;

    /** Field description */
    @OneToMany( mappedBy = "order", cascade =CascadeType.ALL )
    private List<Item> items;

    /** Field description */
    @ManyToOne
    @JoinColumn( name = "bidderid", referencedColumnName = "oid" )
    private Bidder bidder;
}
