//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 02:45:56 PM EST 
//


package com.jdap.auction.common.xml.bid;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for billingFrequencyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="billingFrequencyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MONTHLY"/>
 *     &lt;enumeration value="WEEKLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "billingFrequencyType", namespace = "http://xmlns.jdap.com/auction/Common/v2")
@XmlEnum
public enum BillingFrequencyType {

    MONTHLY,
    WEEKLY;

    public String value() {
        return name();
    }

    public static BillingFrequencyType fromValue(String v) {
        return valueOf(v);
    }

}
