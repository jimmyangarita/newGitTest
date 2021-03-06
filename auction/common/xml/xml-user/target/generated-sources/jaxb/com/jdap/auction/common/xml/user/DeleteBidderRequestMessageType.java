//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 02:45:57 PM EST 
//


package com.jdap.auction.common.xml.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Request to delete a user
 * 
 * <p>Java class for DeleteBidderRequestMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteBidderRequestMessageType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlns.jdap.com/auction/Common/Messages/v2}MessageType">
 *       &lt;sequence>
 *         &lt;element ref="{http://xmlns.jdap.com/auction/Bidder/Objects/v2}bidder"/>
 *         &lt;element name="ForceFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteBidderRequestMessageType", propOrder = {
    "bidder",
    "forceFlag"
})
public class DeleteBidderRequestMessageType
    extends MessageType
{

    @XmlElement(namespace = "http://xmlns.jdap.com/auction/Bidder/Objects/v2", required = true)
    protected BidderType bidder;
    @XmlElement(name = "ForceFlag")
    protected boolean forceFlag;

    /**
     * Details of the user object to be deleted
     * 
     * @return
     *     possible object is
     *     {@link BidderType }
     *     
     */
    public BidderType getBidder() {
        return bidder;
    }

    /**
     * Sets the value of the bidder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BidderType }
     *     
     */
    public void setBidder(BidderType value) {
        this.bidder = value;
    }

    /**
     * Gets the value of the forceFlag property.
     * 
     */
    public boolean isForceFlag() {
        return forceFlag;
    }

    /**
     * Sets the value of the forceFlag property.
     * 
     */
    public void setForceFlag(boolean value) {
        this.forceFlag = value;
    }

}
