package com.jdap.auction.service;

//~--- JDK imports -------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Session Bean implementation class PlaceOrderBean
 */
@Stateful( name = "PlaceOrderEJB", mappedName = "ejb.jdap.auction.service.PlaceOrderEJB" )
public class PlaceOrderBean implements PlaceOrderRemote, PlaceOrderLocal
{
    /** Field description */
    public static Logger logger = Logger.getLogger( PlaceOrderBean.class.getName() );

    /** Field description */
    @Resource( mappedName = "jms.OrderBillingQueueConnectionFactory" )
    private ConnectionFactory orderBillingQueueConnectionFactory;

    /** Field description */
    @Resource( mappedName = "jms.OrderBillingQueue" )
    private Destination orderBillingQueue;
    @EJB
    BidderAccountCreatorLocal bidderAcountCreator;

    /** Field description */
    private Long biderID;

    /** Field description */
    private List<Long> items;

    /** Field description */
    private String shipingInfo;

    /** Field description */
    private String billingInfo;

    /**
     * Constructs ...
     *
     */
    public PlaceOrderBean()
    {
        items = new ArrayList<Long>();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Long getBiderID()
    {
        return biderID;
    }

    /**
     * Method description
     *
     *
     * @param biderID
     */
    @Override
    public void setBiderID( Long biderID )
    {
        this.biderID = biderID;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Long> getItems()
    {
        return items;
    }

    /**
     * Method description
     *
     *
     * @param itemID
     */
    @Override
    public void addItem( Long itemID )
    {
        this.items.add( itemID );
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getShipingInfo()
    {
        return shipingInfo;
    }

    /**
     * Method description
     *
     *
     * @param shipingInfo
     */
    @Override
    public void setShipingInfo( String shipingInfo )
    {
        this.shipingInfo = shipingInfo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getBillingInfo()
    {
        return billingInfo;
    }

    /**
     * Method description
     *
     *
     * @param billingInfo
     */
    @Override
    public void setBillingInfo( String billingInfo )
    {
        this.billingInfo = billingInfo;
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    @Remove
    public Long confirmOrder() throws Exception
    {
        logger.info( "1. Create Acount :: \n" );
        bidderAcountCreator.addLoginInfo( "jangarita" );
        bidderAcountCreator.addBiographicalInfo( "Jimmy" );
        bidderAcountCreator.addBillingInfo( "Master Card" );

        try
        {
            bidderAcountCreator.createAccount();

            String order = "\n Bidder = " + this.biderID + "\n ShipingInfo = " + this.shipingInfo + "\n BillingInfo = "
                           + this.billingInfo + "\n Items :: ";

            System.out.println( "2. Create Order :: \n" + order );

            for( Long item : items )
            {
                logger.info( " id: " + item );
                order = order + "\n id ::  " + item;
            }

            logger.info( "3. Save Order :: \n" );
            billOrder( order );
        }
        catch( Exception e )
        {
            logger.info( "\nJA ERROR XXX Creating Acount  XXX\n" );
            e.printStackTrace();

            throw new Exception( e );
        }

        return this.biderID;
    }

    /**
     * Method description
     *
     *
     * @param order
     */
    private void billOrder( String order )
    {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try
        {
            logger.info( " 4 .Bill order........." );
            connection = orderBillingQueueConnectionFactory.createConnection();
            session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
            producer = session.createProducer( orderBillingQueue );

            TextMessage message = session.createTextMessage( order );

            message.setBooleanProperty( "Fragile", true );
            producer.send( message );
        }
        catch( Exception e )
        {
            logger.info( "Error publishing the order" );
            e.printStackTrace();
        }
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
                logger.info( "Ignore error...." + jmse.getMessage() );
            }
        }
    }
}
