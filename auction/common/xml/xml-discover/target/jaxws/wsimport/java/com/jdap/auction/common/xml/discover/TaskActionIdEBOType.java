
package com.jdap.auction.common.xml.discover;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaskActionIdEBOType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskActionIdEBOType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaskActionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskActionIdEBOType", propOrder = {
    "taskActionId"
})
public class TaskActionIdEBOType {

    @XmlElement(name = "TaskActionId")
    protected String taskActionId;

    /**
     * Gets the value of the taskActionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskActionId() {
        return taskActionId;
    }

    /**
     * Sets the value of the taskActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskActionId(String value) {
        this.taskActionId = value;
    }

}
