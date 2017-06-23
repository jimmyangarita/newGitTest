
package com.jdap.auction.common.xml.discover;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jdap.auction.common.xml.discover package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jdap.auction.common.xml.discover
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StatusEBOType }
     * 
     */
    public StatusEBOType createStatusEBOType() {
        return new StatusEBOType();
    }

    /**
     * Create an instance of {@link TaskIdEBOType }
     * 
     */
    public TaskIdEBOType createTaskIdEBOType() {
        return new TaskIdEBOType();
    }

    /**
     * Create an instance of {@link TaskActionIdEBOType }
     * 
     */
    public TaskActionIdEBOType createTaskActionIdEBOType() {
        return new TaskActionIdEBOType();
    }

    /**
     * Create an instance of {@link ResumeTaskRequestEBM }
     * 
     */
    public ResumeTaskRequestEBM createResumeTaskRequestEBM() {
        return new ResumeTaskRequestEBM();
    }

    /**
     * Create an instance of {@link JobPayload }
     * 
     */
    public JobPayload createJobPayload() {
        return new JobPayload();
    }

}
