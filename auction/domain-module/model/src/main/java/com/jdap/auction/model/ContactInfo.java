package com.jdap.auction.model;

//~--- JDK imports -------------------------------------------------------------

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.28 04:44 PM
 * @author         JDAP Corporation    
 */
@Entity
public class ContactInfo extends AbstractPersistentObject
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Field description */
    @Column( name = "FIRSTNAME", nullable = false )
    private String firstName;

    /** Field description */
    @Column( name = "LASTNAME" )
    private String lastName;

    /** Field description */
    @Embedded
    private Address address;

    /** Field description */
    private String email;

    /** Field description */
    private String phone;

    /** Field description */
    @ManyToOne
    @JoinColumn( name = "userid", referencedColumnName = "oid" )
    private User user;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Method description
     *
     *
     * @param firstName
     */
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Method description
     *
     *
     * @param lastName
     */
    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Method description
     *
     *
     * @param address
     */
    public void setAddress( Address address )
    {
        this.address = address;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Method description
     *
     *
     * @param email
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Method description
     *
     *
     * @param phone
     */
    public void setPhone( String phone )
    {
        this.phone = phone;
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
