package com.jdap.auction.common.spring.edn.publisher;
/**
 * BusinessEventPublisherImpl.java   2012.February.25 04:44 PM
 *
 * Copyright (c) 2010 jdap Corporation
 */


//~--- non-JDK imports ---------------------------------------------------------


import com.jdap.auction.common.spring.edn.XmlMappingException;
import com.jdap.auction.common.spring.edn.DeliverableApplicationEvent;

import oracle.fabric.blocks.event.BusinessEventConnection;
import oracle.fabric.common.BusinessEvent;

import oracle.integration.platform.blocks.event.BusinessEventBuilder;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/

import java.util.logging.Logger;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//~--- JDK imports -------------------------------------------------------------

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;

/**
 * BusinessEventPublisherImpl
 *
 * The publishEvent() method first marshals the payload (content property) of the DeliverableApplicationEvent into an XML document
 * using the Spring O/X Mapping support. After that an instance of oracle.fabric.common.BusinessEvent is created and published
 * via the BusinessEventConnection, which is also injected by Spring (i.e. the bean declared before).
 *
 * @author jdap@jdap.com
 */
public class BusinessEventPublisherImpl implements BusinessEventPublisher
{
    /** Field description */
    private Logger logger =
        Logger.getLogger(BusinessEventPublisherImpl.class.getName() );

    /** Field description */
    private BusinessEventConnection conn;

    /** Field description */
    private Marshaller marshaller;

    /**
     * Set Business Event Connection
     * @param conn BusinessEventConnection
     */
    public void setBusinessEventConnection( BusinessEventConnection conn )
    {
        this.conn = conn;
    }

    /**
     * Set Marshaller
     * @param marshaller Marshaller
     */
    public void setMarshaller( Marshaller marshaller )
    {
        this.marshaller = marshaller;
    }

    /**
     * Method description
     *
     */
    public void closeBusinessEventConnection()
    {
        this.conn.close();
    }

    /**
     * Publish event to EDN
     * @param applicationEvent DeliverableApplicationEvent
     * @throws XmlMappingException on failure
     * @throws IOException on failure
     */
    public
    void publishEvent( DeliverableApplicationEvent applicationEvent )
            throws XmlMappingException,
                   IOException
    {
        DocumentBuilderFactory documentBuilderFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        logger.warning("publishEvent 1.... ");
        System.out.println("publishEvent 1.... ");
        try
        {
            builder = documentBuilderFactory.newDocumentBuilder();
            
            Document document = builder.newDocument();
            
            System.out.println("publishEvent 3.... ");
            DOMResult result = new DOMResult( document );
            
            this.marshaller.marshal( applicationEvent.getContent(), result );
            
            Element payload = document.getDocumentElement();
            
            BusinessEvent event = buildEvent( applicationEvent, payload );
            logger.warning("publishEvent done.... ");
            System.out.println("publishEvent done.... ");

            this.conn.publishEvent( event, 3 );
            
        }
        catch( ParserConfigurationException e )
        {
            logger.log(Level.SEVERE, "Failed to create document builder", e );

            throw new XmlMappingException( "Failed to create document builder",
                                           e );
        }
        catch( JAXBException e )
        {
        	logger.log(Level.SEVERE, "Failed to map event data. \n"
                               + "Check that the class "
                               + applicationEvent.getContent()
                               + " is registered with the marshaller.\n"
                               + "Also check that the class "
                               + applicationEvent.getContent()
                               + " has an @XmlRootElement annotation", e );

            throw new XmlMappingException(
                "Failed to map event data. \n" + "Check that the class "
                + applicationEvent.getContent()
                + " is registered with the marshaller.\n"
                + "Also check that the class " + applicationEvent.getContent()
                + " has an @XmlRootElement annotation", e );
        }
    }

    /**
     * Publish event to EDN
     *
     * @param eventName
     * @param applicationEvent DeliverableApplicationEvent
     * @throws XmlMappingException on failure
     * @throws IOException on failure
     */
    public
    void publishEvent( QName eventName,
                       DeliverableApplicationEvent applicationEvent )
                               throws XmlMappingException,
                                      IOException
    {
        DocumentBuilderFactory documentBuilderFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        logger.info("publishEvent QName 1.... ");
        System.out.println("publishEvent QName  1.... ");
        try
        {
        	logger.info("publishEvent 2.... ");
            builder = documentBuilderFactory.newDocumentBuilder();
            logger.info("publishEvent 3.... ");
            System.out.println("publishEvent 3.... ");
            Document document = builder.newDocument();
            logger.info("publishEvent 4.... ");
            DOMResult result = new DOMResult( document );
            logger.info("publishEvent 5.... ");
            this.marshaller.marshal( applicationEvent.getContent(), result );
            logger.info("publishEvent 6.... ");
            Element payload = document.getDocumentElement();
            logger.info("publishEvent 7.... ");
            System.out.println("publishEvent 7.... ");
            BusinessEvent event = buildEvent( eventName, applicationEvent,
                                              payload );
            logger.info("publishEvent 8.... ");
            this.conn.publishEvent( event, 3 );
            logger.info("publishEvent 9.... ");
        }
        catch( ParserConfigurationException e )
        {
        	logger.log(Level.SEVERE, "Failed to create document builder", e );

            throw new XmlMappingException( "Failed to create document builder",
                                           e );
        }
        catch( JAXBException e )
        {
        	this.logger.log(Level.SEVERE, "Failed to map event data. \n"
                               + "Check that the class "
                               + applicationEvent.getContent()
                               + " is registered with the marshaller.\n"
                               + "Also check that the class "
                               + applicationEvent.getContent()
                               + " has an @XmlRootElement annotation", e );

            throw new XmlMappingException(
                "Failed to map event data. \n" + "Check that the class "
                + applicationEvent.getContent()
                + " is registered with the marshaller.\n"
                + "Also check that the class " + applicationEvent.getContent()
                + " has an @XmlRootElement annotation", e );
        }
    }

    /**
     * Build event
     *
     * This event will be published on the EDN
     *
     * @param applicationEvent DeliverableApplicationEvent
     * @param payload Element
     * @return BusinessEVent
     */
    private
    BusinessEvent buildEvent( DeliverableApplicationEvent applicationEvent,
                              Element payload )
    {
        BusinessEventBuilder beb = BusinessEventBuilder.newInstance();
        QName eventName = new QName( applicationEvent.getNamespace(),
                                     applicationEvent.getName() );

        beb.setEventName( eventName );
        beb.setProperty( BusinessEvent.NAME, applicationEvent.getName() );
        beb.setBody( payload );
        beb.setProperty( BusinessEvent.EVENT_ID,
                         applicationEvent.getEventId() );
        beb.setProperty( BusinessEvent.PROPERTY_CONVERSATION_ID,
                         applicationEvent.getConversationId() );
        beb.setProperty( BusinessEvent.PRIORITY, 1 );

        return beb.createEvent();
    }

    /**
     * Build event
     *
     * This event will be published on the EDN
     *
     * @param eventName QName
     * @param applicationEvent DeliverableApplicationEvent
     * @param payload Element
     * @return BusinessEVent
     */
    private
    BusinessEvent buildEvent( QName eventName,
                              DeliverableApplicationEvent applicationEvent,
                              Element payload )
    {
        BusinessEventBuilder beb = BusinessEventBuilder.newInstance();

        beb.setEventName( eventName );
        beb.setProperty( BusinessEvent.NAME, applicationEvent.getName() );
        beb.setBody( payload );
        beb.setProperty( BusinessEvent.EVENT_ID,
                         applicationEvent.getEventId() );
        beb.setProperty( BusinessEvent.PROPERTY_CONVERSATION_ID,
                         applicationEvent.getConversationId() );
        beb.setProperty( BusinessEvent.PRIORITY, 1 );

        return beb.createEvent();
    }
}
