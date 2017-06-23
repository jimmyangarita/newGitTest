package com.jdap.auction.model;

//~--- JDK imports -------------------------------------------------------------

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.27 11:06 AM
 * @author         JDAP Corporation    
 */
@Embeddable
public class Address implements Serializable
{
    /** Field description */
    private String streetLine1;

    /** Field description */
    private String streeLine2;

    /** Field description */
    private String city;

    /** Field description */
    private String state;

    /** Field description */
    private String zipCode;

    /** Field description */
    private String country;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getStreetLine1()
    {
        return streetLine1;
    }

    /**
     * Method description
     *
     *
     * @param streetLine1
     */
    public void setStreetLine1( String streetLine1 )
    {
        this.streetLine1 = streetLine1;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getStreeLine2()
    {
        return streeLine2;
    }

    /**
     * Method description
     *
     *
     * @param streeLine2
     */
    public void setStreeLine2( String streeLine2 )
    {
        this.streeLine2 = streeLine2;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Method description
     *
     *
     * @param city
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getState()
    {
        return state;
    }

    /**
     * Method description
     *
     *
     * @param state
     */
    public void setState( String state )
    {
        this.state = state;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * Method description
     *
     *
     * @param zipCode
     */
    public void setZipCode( String zipCode )
    {
        this.zipCode = zipCode;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Method description
     *
     *
     * @param country
     */
    public void setCountry( String country )
    {
        this.country = country;
    }
}
