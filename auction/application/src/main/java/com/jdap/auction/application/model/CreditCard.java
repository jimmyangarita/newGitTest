package com.jdap.auction.application.model;

//~--- JDK imports -------------------------------------------------------------

import java.io.Serializable;

/**
 * Class description
 *
 *
 * @version        9.0, 2014.September.19 02:35 PM
 * @author         JDAP Corporation    
 */
public class CreditCard implements Serializable
{
    /** Field description */
    private String number;

    /**
     * Constructs ...
     *
     *
     * @param number
     */
    public CreditCard( String number )
    {
        this.number = number;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String toString()
    {
        return number;
    }
}
