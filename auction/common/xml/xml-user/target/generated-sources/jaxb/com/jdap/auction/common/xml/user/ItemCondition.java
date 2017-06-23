//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 02:45:57 PM EST 
//


package com.jdap.auction.common.xml.user;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itemCondition.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="itemCondition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NEW"/>
 *     &lt;enumeration value="USED_LIKE_NEW"/>
 *     &lt;enumeration value="USED_VERY_GOOD"/>
 *     &lt;enumeration value="USED_GOOD"/>
 *     &lt;enumeration value="USED_ACCEPTABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "itemCondition", namespace = "http://xmlns.jdap.com/auction/Common/v2")
@XmlEnum
public enum ItemCondition {

    NEW,
    USED_LIKE_NEW,
    USED_VERY_GOOD,
    USED_GOOD,
    USED_ACCEPTABLE;

    public String value() {
        return name();
    }

    public static ItemCondition fromValue(String v) {
        return valueOf(v);
    }

}