//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 02:45:57 PM EST 
//


package com.jdap.auction.common.xml.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Response of read user query
 * 
 * <p>Java class for QuerySellerResponseMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QuerySellerResponseMessageType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlns.jdap.com/auction/Common/Messages/v2}MessageType">
 *       &lt;sequence>
 *         &lt;element ref="{http://xmlns.jdap.com/auction/Seller/Objects/v2}seller" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuerySellerResponseMessageType", propOrder = {
    "seller"
})
public class QuerySellerResponseMessageType
    extends MessageType
{

    @XmlElement(namespace = "http://xmlns.jdap.com/auction/Seller/Objects/v2", required = true, type = SellerType.class)
    protected List<SellerType> seller;

    /**
     * Details of the user in response to query user request Gets the value of the seller property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seller property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeller().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SellerType }
     * 
     * 
     */
    public List<SellerType> getSeller() {
        if (seller == null) {
            seller = new ArrayList<SellerType>();
        }
        return this.seller;
    }

}
