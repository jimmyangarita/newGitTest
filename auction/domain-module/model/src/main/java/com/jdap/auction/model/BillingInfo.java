package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

//~--- JDK imports -------------------------------------------------------------

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.27 11:22 AM
 * @author         JDAP Corporation    
 */


@Entity
public class BillingInfo extends AbstractPersistentObject
{
    /** Field description */
    private String creditCardType;

    /** Field description */
    private String creditCardNamber;

    /** Field description */
    private String nameOnCreditCard;

    /** Field description */
    private Date creditCardExpiration;

    /** Field description */
    private String bankAccountNumber;

    /** Field description */
    private String bankName;

    /** Field description */
    private String routingNumber;

    /** Field description */
    @OneToOne( mappedBy = "billingInfo", optional = false )
    private User user;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getCreditCardType()
    {
        return creditCardType;
    }

    /**
     * Method description
     *
     *
     * @param creditCardType
     */
    public void setCreditCardType( String creditCardType )
    {
        this.creditCardType = creditCardType;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getCreditCardNamber()
    {
        return creditCardNamber;
    }

    /**
     * Method description
     *
     *
     * @param creditCardNamber
     */
    public void setCreditCardNamber( String creditCardNamber )
    {
        this.creditCardNamber = creditCardNamber;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getNameOnCreditCard()
    {
        return nameOnCreditCard;
    }

    /**
     * Method description
     *
     *
     * @param nameOnCreditCard
     */
    public void setNameOnCreditCard( String nameOnCreditCard )
    {
        this.nameOnCreditCard = nameOnCreditCard;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getCreditCardExpiration()
    {
        return creditCardExpiration;
    }

    /**
     * Method description
     *
     *
     * @param creditCardExpiration
     */
    public void setCreditCardExpiration( Date creditCardExpiration )
    {
        this.creditCardExpiration = creditCardExpiration;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getBankAccountNumber()
    {
        return bankAccountNumber;
    }

    /**
     * Method description
     *
     *
     * @param bankAccountNumber
     */
    public void setBankAccountNumber( String bankAccountNumber )
    {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getBankName()
    {
        return bankName;
    }

    /**
     * Method description
     *
     *
     * @param bankName
     */
    public void setBankName( String bankName )
    {
        this.bankName = bankName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getRoutingNumber()
    {
        return routingNumber;
    }

    /**
     * Method description
     *
     *
     * @param routingNumber
     */
    public void setRoutingNumber( String routingNumber )
    {
        this.routingNumber = routingNumber;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Method description
     *
     *
     * @param user
     */
    public void setUser( User user )
    {
        this.user = user;
    }
}
