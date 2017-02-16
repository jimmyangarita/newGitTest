
package com.jdap.auction.common.xml.apiconfiguration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bidRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bidRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bidderId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bidderId",
    "itemId",
    "amount"
})
@XmlRootElement(name = "bid", namespace = "http://www.auction.com/api/adf/bidservice/ebo")
public class AddBidType implements Serializable{

    @XmlElement(namespace = "http://www.auction.com/api/adf/bidservice/ebo", required = true,type = Long.class, nillable = true)
	protected Long bidderId;
    @XmlElement(namespace = "http://www.auction.com/api/adf/bidservice/ebo", required = true,type = String.class, nillable = true)
    protected String itemId;
    @XmlElement(namespace = "http://www.auction.com/api/adf/bidservice/ebo", required = true, type = Double.class, nillable = true)
    protected Double amount;

    /**
     * Gets the value of the bidderId property.
     * 
     */
    public Long getBidderId() {
        return bidderId;
    }

    /**
     * Sets the value of the bidderId property.
     * 
     */
    public void setBidderId(Long value) {
        this.bidderId = value;
    }

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

}
