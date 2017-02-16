package com.jdap.auction.service;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.exceptions.AuctionServiceObjectCreationException;

/**
 * Interface description
 *
 *
 * @version        9.0, 2012.November.08 02:20 PM
 * @author         jdap Corporation    
 */
public interface XmlUtilBeanLocal
{
    String marshal( Object obj ) throws AuctionServiceObjectCreationException;
    
    <T> String  marshalUrl( Class<T> clazz, T obj, String uri ) throws AuctionServiceObjectCreationException;

    Object unMarshal( String xml, Class clazz ) throws AuctionServiceObjectCreationException;

    <T> T xmlToObject( String xml, Class<T> clazz ) throws AuctionServiceObjectCreationException;
}
