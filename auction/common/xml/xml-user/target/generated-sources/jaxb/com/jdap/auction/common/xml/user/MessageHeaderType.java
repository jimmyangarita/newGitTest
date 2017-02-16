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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MessageHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Sender" type="{http://xmlns.jdap.com/Common/MessageHeader/v2}SenderType"/>
 *         &lt;element name="TransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessProcessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoggingLevel" type="{http://xmlns.jdap.com/Common/MessageHeader/v2}LoggingLevelType" minOccurs="0"/>
 *         &lt;element name="ExtensionProperties" type="{http://xmlns.jdap.com/Common/MessageHeader/v2}NameValuePairType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extension" type="{http://xmlns.jdap.com/Common/MessageHeader/Extension/v2}ExtensionMessageHeaderType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeaderType", namespace = "http://xmlns.jdap.com/Common/MessageHeader/v2", propOrder = {
    "requestId",
    "creationDateTime",
    "sender",
    "transactionId",
    "businessProcessId",
    "userId",
    "loggingLevel",
    "extensionProperties",
    "extension"
})
public class MessageHeaderType {

    @XmlElement(name = "RequestId", required = true)
    protected String requestId;
    @XmlElement(name = "CreationDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDateTime;
    @XmlElement(name = "Sender", required = true)
    protected SenderType sender;
    @XmlElement(name = "TransactionId")
    protected String transactionId;
    @XmlElement(name = "BusinessProcessId")
    protected String businessProcessId;
    @XmlElement(name = "UserId")
    protected String userId;
    @XmlElement(name = "LoggingLevel")
    protected LoggingLevelType loggingLevel;
    @XmlElement(name = "ExtensionProperties", type = NameValuePairType.class)
    protected List<NameValuePairType> extensionProperties;
    protected ExtensionMessageHeaderType extension;

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the creationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets the value of the creationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link SenderType }
     *     
     */
    public SenderType getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link SenderType }
     *     
     */
    public void setSender(SenderType value) {
        this.sender = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the businessProcessId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessProcessId() {
        return businessProcessId;
    }

    /**
     * Sets the value of the businessProcessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessProcessId(String value) {
        this.businessProcessId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the loggingLevel property.
     * 
     * @return
     *     possible object is
     *     {@link LoggingLevelType }
     *     
     */
    public LoggingLevelType getLoggingLevel() {
        return loggingLevel;
    }

    /**
     * Sets the value of the loggingLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoggingLevelType }
     *     
     */
    public void setLoggingLevel(LoggingLevelType value) {
        this.loggingLevel = value;
    }

    /**
     * Gets the value of the extensionProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensionProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensionProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValuePairType }
     * 
     * 
     */
    public List<NameValuePairType> getExtensionProperties() {
        if (extensionProperties == null) {
            extensionProperties = new ArrayList<NameValuePairType>();
        }
        return this.extensionProperties;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionMessageHeaderType }
     *     
     */
    public ExtensionMessageHeaderType getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionMessageHeaderType }
     *     
     */
    public void setExtension(ExtensionMessageHeaderType value) {
        this.extension = value;
    }

}