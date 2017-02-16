package com.jdap.auction.service;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.exceptions.AuctionServiceObjectCreationException;
import com.jdap.auction.service.XmlUtilBeanLocal;

//~--- JDK imports -------------------------------------------------------------

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

import javax.ejb.Stateless;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

/**
 * Class description
 *
 *
 * @version        9.0, 2012.November.08 03:41 PM
 * @author         jdap Corporation
 */
@Stateless( name = "XmlUtilLocal", mappedName = "ejb.jdap.auction.service.XmlUtilLocal" )
public class XmlUtilBean implements XmlUtilBeanLocal
{
    /** Field description */
    public static Logger logger = Logger.getLogger( BaseUtilBean.class.getName() );

    /** Field description */
    private static final Map<String, JAXBContext> JAXBContextCache = new ConcurrentHashMap<String, JAXBContext>();

    /**
     * Default constructor.
     */
    public XmlUtilBean()
    {
        logger.log( Level.FINE, "XmlUtil..." );
    }

    /**
     * Method description
     *
     *
     * @param contextPath
     *
     * @return
     *
     * @throws JAXBException
     */

    /**
     * Method description
     *
     *
     * @param contextPath
     *
     * Create aJAXB context if it already not exist
     * @return
     *
     * @throws JAXBException
     */
    public JAXBContext createJAXBContext( final Class contextPath ) throws JAXBException
    {
        JAXBContext context = JAXBContextCache.get( contextPath.getName() );

        if( context == null )
        {
            logger.info( "Creating a new instance of JAXBContext :: " + contextPath.getName() );
            context = JAXBContext.newInstance( contextPath );
            JAXBContextCache.put( contextPath.getName(), context );
        }

        return context;
    }

    /**
     * Method description
     *
     *
     * @param obj
     *
     * @return
     *
     * @throws AuctionServiceObjectCreationException
     */
    public String marshal( Object obj ) throws AuctionServiceObjectCreationException
    {
        StringWriter sw = null;

        try
        {
            JAXBContext jaxbCtx = createJAXBContext( obj.getClass() );
            Marshaller marshaller = jaxbCtx.createMarshaller();

            marshaller.setProperty( Marshaller.JAXB_FRAGMENT, Boolean.TRUE );		// Remove xml declaration
            sw = new StringWriter();
            marshaller.marshal( obj, sw );

            String xmlDefinition = sw.toString();

            return xmlDefinition;
        }
        catch( JAXBException e )
        {
        	e.printStackTrace();
            logger.info( "marshalException........" + e.getMessage()  + " , cause: " + e.getStackTrace());

            throw new AuctionServiceObjectCreationException( " , Failed to marshall: " + obj.getClass().getName(), e );
        }
        finally
        {
            try
            {
                if( sw != null )
                {
                    sw.close();
                }
            }
            catch( Exception ioe )
            {
                logger.log( Level.WARNING, ioe.getMessage() );
            }
        }
    }
    
    
    public <T> String  marshalUrl( Class<T> clazz, T obj, String uri ) throws AuctionServiceObjectCreationException
    {
    	//Class<T> clazz, expects ApiFaultType.class
    	//T obj, expects apiFaultType, that is and INSTANCE of ApiFaultType
        StringWriter sw = null;
        QName _QNAME = new QName(uri, obj.getClass().getName());

        try
        {
            JAXBContext jaxbCtx = createJAXBContext( obj.getClass() );
            Marshaller marshaller = jaxbCtx.createMarshaller();

            marshaller.setProperty( Marshaller.JAXB_FRAGMENT, Boolean.TRUE );		// Remove xml declaration
            sw = new StringWriter();
            
            System.out.println("_QNAME.getClass():: " + _QNAME.getClass());
            
            marshaller.marshal( new JAXBElement<T>(_QNAME,  clazz, obj), sw );

            String xmlDefinition = sw.toString();

            return xmlDefinition;
        }
        catch( JAXBException e )
        {
        	e.printStackTrace();
            logger.info( "marshalException........" + e.getMessage()  + " , cause: " + e.getStackTrace());

            throw new AuctionServiceObjectCreationException( " , Failed to marshall: " + obj.getClass().getName(), e );
        }
        finally
        {
            try
            {
                if( sw != null )
                {
                    sw.close();
                }
            }
            catch( Exception ioe )
            {
                logger.log( Level.WARNING, ioe.getMessage() );
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param xml
     * @param clazz
     *
     * @return
     *
     * @throws AuctionServiceObjectCreationException
     */
    public Object unMarshal( String xml, Class clazz ) throws AuctionServiceObjectCreationException
    {
        ByteArrayInputStream baXml = null;

        logger.info( "clazz:: " + clazz );

        try
        {
            // jaxb context = JAXBContext.newInstance(clazz);
            JAXBContext jaxbCtx = createJAXBContext( clazz );
            Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

            unmarshaller.setSchema( null );	// disable validation
            baXml = new ByteArrayInputStream( xml.getBytes() );

            Object xmlObject = clazz.cast( unmarshaller.unmarshal( baXml ) );

            return xmlObject;
        }
        catch( JAXBException e )
        {
            throw new AuctionServiceObjectCreationException( e );
        }
        finally
        {
            try
            {
                if( baXml != null )
                {
                    baXml.close();
                }
            }
            catch( Exception ioe )
            {
                logger.warning( ioe.getMessage() );
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param xml
     * @param clazz
     * @param <T>
     *
     * @return
     *
     * @throws AuctionServiceObjectCreationException
     */
    public <T> T xmlToObject( String xml, Class<T> clazz ) throws AuctionServiceObjectCreationException
    {
        ByteArrayInputStream baXml = null;

        //logger.info( "clazz:: " + clazz );
        //logger.info( "clazz.getName:: " + clazz.getName() );

        try
        {
            // jaxb context = JAXBContext.newInstance(clazz);
            JAXBContext jaxbCtx = createJAXBContext( clazz );
            Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

            unmarshaller.setSchema( null );	// disable validation
            baXml = new ByteArrayInputStream( xml.getBytes() );

            Object xmlObject = unmarshaller.unmarshal( baXml );

            // return T = clazz.cast(xmlObject)z
            return clazz.cast( xmlObject );
        }
        catch( JAXBException e )
        {
            throw new AuctionServiceObjectCreationException( e );
        }
        finally
        {
            try
            {
                if( baXml != null )
                {
                    baXml.close();
                }
            }
            catch( Exception ioe )
            {
                logger.warning( ioe.getMessage() );
            }
        }
    }


}
