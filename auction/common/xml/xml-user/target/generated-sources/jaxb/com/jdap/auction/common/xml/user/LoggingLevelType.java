//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 02:45:57 PM EST 
//


package com.jdap.auction.common.xml.user;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoggingLevelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoggingLevelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Fatal"/>
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Warning"/>
 *     &lt;enumeration value="Notification"/>
 *     &lt;enumeration value="Trace"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoggingLevelType", namespace = "http://xmlns.jdap.com/Common/MessageHeader/v2")
@XmlEnum
public enum LoggingLevelType {


    /**
     * Highest severity level. Unexpected errors that occur during normal execution. Fatal exceptions or any other serious problems that require immediate attention from the System Administrator.
     * 
     */
    @XmlEnumValue("Fatal")
    FATAL("Fatal"),

    /**
     * The Error level designates error events that might still allow the application to continue running. Administrator action can be delayed in this severity.
     * 
     */
    @XmlEnumValue("Error")
    ERROR("Error"),

    /**
     * The Warning level designates potentially harmful situations. Any potential problem that should be reviewed by the System Administrator.
     * 
     */
    @XmlEnumValue("Warning")
    WARNING("Warning"),

    /**
     * Key flow steps; high-level functional progress messages. A major life cycle event such as the activation or deactivation of a primary sub-component or feature.
     * 
     */
    @XmlEnumValue("Notification")
    NOTIFICATION("Notification"),

    /**
     * Configuration properties and environment settings. This is a finer level of granularity for reporting normal events.
     * 
     */
    @XmlEnumValue("Trace")
    TRACE("Trace");
    private final String value;

    LoggingLevelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoggingLevelType fromValue(String v) {
        for (LoggingLevelType c: LoggingLevelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
