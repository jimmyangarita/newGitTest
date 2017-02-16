
package com.jdap.auction.common.xml.discover;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JobPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JobPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaskIdEBO" type="{http://xmlns.dcim.emerson.com/cps/scheduler/ebo}TaskIdEBOType"/>
 *         &lt;element name="TaskActionIdEBO" type="{http://xmlns.dcim.emerson.com/cps/scheduler/ebo}TaskActionIdEBOType"/>
 *         &lt;element name="TaskActionParameters" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="JobStatusEBOType" type="{http://xmlns.dcim.emerson.com/cps/scheduler/ebo}StatusEBOType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JobPayload", namespace = "http://xmlns.dcim.emerson.com/cps/scheduler/quartz-scheduler/ebo", propOrder = {
    "taskIdEBO",
    "taskActionIdEBO",
    "taskActionParameters",
    "jobStatusEBOType"
})
@XmlSeeAlso({
    ResumeTaskRequestEBM.class
})
public class JobPayload {

    @XmlElement(name = "TaskIdEBO", required = true)
    protected TaskIdEBOType taskIdEBO;
    @XmlElement(name = "TaskActionIdEBO", required = true)
    protected TaskActionIdEBOType taskActionIdEBO;
    @XmlElement(name = "TaskActionParameters", required = true)
    protected byte[] taskActionParameters;
    @XmlElement(name = "JobStatusEBOType", required = true)
    protected StatusEBOType jobStatusEBOType;

    /**
     * Gets the value of the taskIdEBO property.
     * 
     * @return
     *     possible object is
     *     {@link TaskIdEBOType }
     *     
     */
    public TaskIdEBOType getTaskIdEBO() {
        return taskIdEBO;
    }

    /**
     * Sets the value of the taskIdEBO property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskIdEBOType }
     *     
     */
    public void setTaskIdEBO(TaskIdEBOType value) {
        this.taskIdEBO = value;
    }

    /**
     * Gets the value of the taskActionIdEBO property.
     * 
     * @return
     *     possible object is
     *     {@link TaskActionIdEBOType }
     *     
     */
    public TaskActionIdEBOType getTaskActionIdEBO() {
        return taskActionIdEBO;
    }

    /**
     * Sets the value of the taskActionIdEBO property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskActionIdEBOType }
     *     
     */
    public void setTaskActionIdEBO(TaskActionIdEBOType value) {
        this.taskActionIdEBO = value;
    }

    /**
     * Gets the value of the taskActionParameters property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getTaskActionParameters() {
        return taskActionParameters;
    }

    /**
     * Sets the value of the taskActionParameters property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setTaskActionParameters(byte[] value) {
        this.taskActionParameters = ((byte[]) value);
    }

    /**
     * Gets the value of the jobStatusEBOType property.
     * 
     * @return
     *     possible object is
     *     {@link StatusEBOType }
     *     
     */
    public StatusEBOType getJobStatusEBOType() {
        return jobStatusEBOType;
    }

    /**
     * Sets the value of the jobStatusEBOType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusEBOType }
     *     
     */
    public void setJobStatusEBOType(StatusEBOType value) {
        this.jobStatusEBOType = value;
    }

}
