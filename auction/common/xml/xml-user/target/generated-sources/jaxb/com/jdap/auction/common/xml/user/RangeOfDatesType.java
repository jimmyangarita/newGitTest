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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RangeOfDatesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RangeOfDatesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="QueryEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeOfDatesType", namespace = "http://xmlns.jdap.com/auction/Common/v2", propOrder = {
    "queryStartDate",
    "queryEndDate"
})
public class RangeOfDatesType {

    @XmlElement(name = "QueryStartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar queryStartDate;
    @XmlElement(name = "QueryEndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar queryEndDate;

    /**
     * Gets the value of the queryStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getQueryStartDate() {
        return queryStartDate;
    }

    /**
     * Sets the value of the queryStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setQueryStartDate(XMLGregorianCalendar value) {
        this.queryStartDate = value;
    }

    /**
     * Gets the value of the queryEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getQueryEndDate() {
        return queryEndDate;
    }

    /**
     * Sets the value of the queryEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setQueryEndDate(XMLGregorianCalendar value) {
        this.queryEndDate = value;
    }

}