package com.jdap.auction.common.xml.apiconfiguration;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="originalPayload" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="coreFault" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "code",
    "summary",
    "detail"
})
@XmlRootElement(name = "runtimeFaultMessage", namespace = "http://www.auction.com/api/osb/runtimeFault")
public class RuntimeFaultMessageOsbType implements Serializable{

    @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
    protected String code;
    @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
    protected String summary;
    @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
    protected RuntimeFaultMessageOsbType.Detail detail;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link RuntimeFaultMessageOsbType.Detail }
     *     
     */
    public RuntimeFaultMessageOsbType.Detail getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuntimeFaultMessageOsbType.Detail }
     *     
     */
    public void setDetail(RuntimeFaultMessageOsbType.Detail value) {
        this.detail = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="originalPayload" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="coreFault" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
        "message",
        "originalPayload",
        "coreFault"
    })
    public static class Detail implements Serializable {

        @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
        protected String message;
        @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
        protected Object originalPayload;
        @XmlElement(namespace = "http://www.auction.com/api/osb/runtimeFault", required = true)
        protected Object coreFault;

        /**
         * Gets the value of the message property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the value of the message property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessage(String value) {
            this.message = value;
        }

        /**
         * Gets the value of the originalPayload property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getOriginalPayload() {
            return originalPayload;
        }

        /**
         * Sets the value of the originalPayload property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setOriginalPayload(Object value) {
            this.originalPayload = value;
        }

        /**
         * Gets the value of the coreFault property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getCoreFault() {
            return coreFault;
        }

        /**
         * Sets the value of the coreFault property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setCoreFault(Object value) {
            this.coreFault = value;
        }

    }

}
