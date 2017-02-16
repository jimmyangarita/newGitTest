/**
 * XmlMappingException.java   2012.February.25 04:44 PM
 *
 * Copyright (c) 2010 jdap Corporation
 */

package com.jdap.auction.common.spring.edn;

/**
 * XmlMappingException
 *
 * @author jdap
 */
public class XmlMappingException extends Exception
{
    /**
     * Create an XmlMappingException
     *
     * @param message
     * @param cause Throwable
     */
    public XmlMappingException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
