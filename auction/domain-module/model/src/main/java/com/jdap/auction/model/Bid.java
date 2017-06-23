
package com.jdap.auction.model;

// ~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

// ~--- JDK imports -------------------------------------------------------------

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2013.February.26 05:07 PM
 * @author JDAP Corporation
 */
@Entity
// @Table( name = "BIDS" )
@NamedQueries (
	{@NamedQuery ( name = "Bids.getHighBids" ,
	               query = "Select bid from Bid bid where bid.bidAmount > :amount" ),
	 @NamedQuery ( name = "Bids.getItemBids" ,
	               query = "SELECT b FROM Bid b WHERE b.item = :item ORDER BY b.bidAmount ASC" )
	} )
public class Bid extends AbstractPersistentObject
{
	/** Field description */

	/** Field description */
	@ManyToOne ( )
	// cascade = CascadeType.PERSIST) has to change not cascade, in the one to many side to delete or update
	@JoinColumn ( name = "itemid" , referencedColumnName = "oid" )
	private Item item;

	/** Field description */
	@ManyToOne ( /* cascade = CascadeType.PERSIST */)
	// has to change not cascade, MERGE is better
	@JoinColumn ( name = "bidderid" , referencedColumnName = "oid" )
	private Bidder bidder;

	/** Field description */
	private Double bidAmount;

	/** Field description */
	private Double maxAmount;

	/** Field description */
	private Timestamp bidDate;

	/**
	 * Method description
	 * 
	 * 
	 * 
	 * @param bidID
	 * @return
	 */

	/* @Id
	 * @GeneratedValue( strategy = GenerationType.AUTO )
	 * @Column( name = "BID_ID" ) public Long getBidID() { return bidID; } */

	/**
	 * Method description
	 * 
	 * 
	 * @param bidID
	 */

	/* public void setBidID( Long bidID ) { this.bidID = bidID; } */

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */

	// @Column( name = "BID_AMOUNT" )
	public Double getBidAmount()
	{
		return bidAmount;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public Item getItem()
	{
		return item;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param item
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param bidAmount
	 */
	public void setBidAmount(Double bidAmount)
	{
		this.bidAmount = bidAmount;
	}

	public Double getMaxAmount()
	{
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount)
	{
		this.maxAmount = maxAmount;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */

	// @Column( name = "BID_DATE" )
	public Timestamp getBidDate()
	{
		return bidDate;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param bidDate
	 */
	public void setBidDate(Timestamp bidDate)
	{
		this.bidDate = bidDate;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public Bidder getBidder()
	{
		return bidder;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param bidder
	 */
	public void setBidder(Bidder bidder)
	{
		this.bidder = bidder;
	}
}
