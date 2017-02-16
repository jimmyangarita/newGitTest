package com.jdap.auction.exceptions;

/**
 * Class description
 *
 *
 * @version        9.0, 2012.November.08 02:46 PM
 * @author         jdap Corporation
 */
public class AuctionServicePublisherException extends AuctionServiceException
{
    /**
     *    
     */
    private static final long serialVersionUID = -9095092248269269412L;

    /**
     * Constructs ...
     *
     *
     * @param message
     */
    public AuctionServicePublisherException( String message )
    {
        super( message );
    }

    /**
     * Constructs ...
     *
     *
     * @param cause
     */
    public AuctionServicePublisherException( Throwable cause )
    {
        super( cause );
    }

    /**
     * Constructs ...
     *
     *
     * @param message
     * @param cause
     */
    public AuctionServicePublisherException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
