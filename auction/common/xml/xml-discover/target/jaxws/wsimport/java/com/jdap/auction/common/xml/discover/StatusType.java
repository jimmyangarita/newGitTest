
package com.jdap.auction.common.xml.discover;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IDLE"/>
 *     &lt;enumeration value="IN_PROGRESS"/>
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAIL"/>
 *     &lt;enumeration value="TERMINATED"/>
 *     &lt;enumeration value="CANCELLED"/>
 *     &lt;enumeration value="STALE"/>
 *     &lt;enumeration value="UN_KNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusType")
@XmlEnum
public enum StatusType {

    IDLE,
    IN_PROGRESS,
    SUCCESS,
    FAIL,
    TERMINATED,
    CANCELLED,
    STALE,
    UN_KNOWN;

    public String value() {
        return name();
    }

    public static StatusType fromValue(String v) {
        return valueOf(v);
    }

}
