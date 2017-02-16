package com.jdap.auction.service;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.spring.edn.ApplicationEventFactory;
import com.jdap.auction.common.spring.edn.DeliverableApplicationEvent;
import com.jdap.auction.common.spring.edn.publisher.BusinessEventPublisherImpl;
import com.jdap.auction.common.spring.edn.XmlMappingException;
import com.jdap.auction.common.xml.apiconfiguration.RuntimeFaultMessageType;
import com.jdap.auction.exceptions.AuctionServicePublisherException;
import com.jdap.auction.exceptions.AuctionServicePublishEventException;
import com.jdap.auction.service.JmsPublisherUtilBeanLocal;

import oracle.fabric.blocks.event.BusinessEventConnection;
import oracle.fabric.blocks.event.BusinessEventConnectionFactory;

import oracle.integration.platform.blocks.event.BusinessEventConnectionFactorySupport;

//~--- JDK imports -------------------------------------------------------------

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 * Class description
 *
 *
 * @version 9.0, 2012.November.08 03:12 PM
 * @author JDAP Corporation
 */
@Stateless( name = "JmsPublisherUtilLocal", mappedName = "ejb.jdap.auction.service.JmsPublisherUtilLocal" )
public class JmsPublisherUtilBean implements JmsPublisherUtilBeanLocal
{
    /** Field description */
    public static Logger logger = Logger.getLogger( JmsPublisherUtilBean.class.getName() );

    /** Field description */
    @Resource( mappedName = "jms.ApiFaultQueueConnectionFactory" )
    private ConnectionFactory apiFaultQueueConnectionFactory;

    /** Field description */
    @Resource( mappedName = "jms.ApiFaultQueue" )
    private Destination apiFaultQueue;
    @Resource
    SessionContext context;

    /**
     * Method description
     *
     */
    @PostConstruct
    public void initialize()
    {
        logger.log( Level.WARNING, "\n...................Initialize JMS Properties........................\n:" );

        /*
         * Properties env = new Properties();
         *
         * env.setProperty( Context.INITIAL_CONTEXT_FACTORY,
         * "weblogic.jndi.WLInitialContextFactory" ); env.setProperty(
         * Context.PROVIDER_URL, "t3://localhost.localdomain:8001" );
         */
    }

    /**
     * Method description
     *
     *
     * @param message
     *
     * @param strFault
     *
     * @throws AuctionServicePublisherException
     */
    @TransactionAttribute( TransactionAttributeType.REQUIRES_NEW )
    public void send( String strFault ) throws AuctionServicePublisherException
    {
        logger.warning( " into publishing . with properties" );

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try
        {
            logger.info( " publishing......... to ADF server" );
            connection = apiFaultQueueConnectionFactory.createConnection();
            session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
            producer = session.createProducer( apiFaultQueue );

            /*
             * String MYCF_LOOKUP_NAME = "jms.ApiFaultQueueConnectionFactory";
             * String MYQUEUE_LOOKUP_NAME = "jms.ApiFaultQueue";
             *
             * // Hashtable env;
             * Properties env;
             * Context ctx = null;
             *
             * env = new Properties();
             * env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
             *               "weblogic.jndi.WLInitialContextFactory");
             * env.setProperty(Context.PROVIDER_URL,
             *               "t3://localhost.localdomain:8001");
             * ctx = new InitialContext(env);
             *
             * ConnectionFactory cf1 = (ConnectionFactory) ctx
             *               .lookup(MYCF_LOOKUP_NAME);
             *
             * connection = cf1.createConnection();
             * session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             *
             * Destination dest = (Queue) ctx.lookup(MYQUEUE_LOOKUP_NAME);
             *
             * producer = session.createProducer(dest);
             */
            TextMessage txtMessage = session.createTextMessage( strFault );

            // message.setBooleanProperty( "Fragile", true );
            logger.info( " publishing strFault: " + strFault );
            producer.send( txtMessage );
        }
        catch( JMSException e )
        {
            logger.info( "Error publishing the excetion xsd:: " + e.getMessage() );

            throw new AuctionServicePublisherException( "Failed to publish Fault to the error queue" + strFault, e );
        }	/*
                 * catch( NamingException e )
                 * {
                 *  logger.info( "NamingException :: " + e.getMessage() );
                 *  e.printStackTrace();
                 * }
                 */
        finally
        {
            try
            {
                if( producer != null )
                {
                    producer.close();
                }

                if( session != null )
                {
                    session.close();
                }

                if( connection != null )
                {
                    connection.close();
                }
            }
            catch( JMSException jmse )
            {
                logger.severe( "Problems publishing to ApiFaultQueue, error: " + jmse.getMessage() );
            }
        }
    }

    /**
     * Publish event to the EDN network
     *
     * @param event The event to publish
     * @param name
     *
     *
     * @throws common servicePublishEventException
     *
     * @throws AuctionServicePublishEventException
     */
    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void sendEvent( Object event, QName name ) throws AuctionServicePublishEventException
    {
        BusinessEventConnection conn = null;

        try
        {
            BusinessEventConnectionFactory businessEventConnectionFactory =
                BusinessEventConnectionFactorySupport.findRelevantBusinessEventConnectionFactory( true );

            if( businessEventConnectionFactory != null )
            {
            	RuntimeFaultMessageType s = new RuntimeFaultMessageType();
            	s.setCode("1");
            	s.setSummary("a");
            	logger.info("step1....");
                conn = businessEventConnectionFactory.createBusinessEventConnection();
                
                DeliverableApplicationEvent ednEvent = null;
                
                JAXBContext jaxbContext = JAXBContext.newInstance( event.getClass() );
                
                Marshaller marshaller = jaxbContext.createMarshaller();
                
                BusinessEventPublisherImpl publisher = new BusinessEventPublisherImpl();
                

                publisher.setBusinessEventConnection( conn );
                
                publisher.setMarshaller( marshaller );
                
                ednEvent = new ApplicationEventFactory().createEvent( this, event );
                
                publisher.publishEvent(ednEvent );
                logger.info("step10");
            }
            else
            {
                logger.severe(
                    "An error has occured while publishing EDN the event, Cannot obtain connection from Factory." );

                throw new AuctionServicePublishEventException(
                    "An error has occured while publishing EDN the event. Cannot obtain connection from Factory." );
            }
        }
        catch( JAXBException e )
        {
            logger.severe( "Error marshalling event::: " + e.getMessage() );

            throw new AuctionServicePublishEventException( "Error marshalling event", e );
        }
        catch( XmlMappingException e )
        {
            logger.severe( "Error creating EDN event: " + e.getMessage() );

            throw new AuctionServicePublishEventException( "Error creating EDN event", e );
        }
        catch( IOException e )
        {
            logger.severe( "Error publishing EDN event: " + e.getMessage() );

            throw new AuctionServicePublishEventException( "Error publishing EDN event", e );
        }
        catch( Exception e )
        {
            logger.severe( "Unknown exception, Error publishing EDN event" + e.getMessage() );

            throw new AuctionServicePublishEventException( "Unknown exception, Error publishing EDN event", e );
        }
        finally
        {
            if( null != conn )
            {
                conn.close();
            }
        }
    }
}
