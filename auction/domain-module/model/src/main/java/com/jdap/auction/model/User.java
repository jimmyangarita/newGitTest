package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.model.type.UserType;
import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

//~--- JDK imports -------------------------------------------------------------

//~--- non-JDK imports ---------------------------------------------------------
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class description
 *
 *
 * @version 9.0, 2013.February.27 11:08 AM
 * @author JDAP Corporation
 */
@Entity
@Table( name = "USERS" )
@SecondaryTable( name = "USERSPICTURE",
                 pkJoinColumns = @PrimaryKeyJoinColumn( name = "OID", referencedColumnName = "OID" ) )
@Inheritance( strategy = InheritanceType.JOINED )
@DiscriminatorColumn(
    name = "USERTYPE",
    discriminatorType = DiscriminatorType.STRING,
    length = 1
)
@NamedQueries( { @NamedQuery( name = "user.getUserLogin",
                              query = "SELECT u FROM User u " + "WHERE u.username = :username and "
                                      + "u.password = :password " + "ORDER BY u.creationDate ASC" ) ,
                 @NamedQuery( name = "user.getUserName",
                              query = "SELECT u FROM User u " + "WHERE u.username = :username" ) } )
public abstract class User extends AbstractPersistentObject
{
    /** Field description */
    @Column( name = "USERNAME", nullable = false )
    private String username;

    /** Field description */
    @Column( name = "PASSWORD", nullable = false )
    private String password;

    /** Field description */
    @Column( name = "FISRTNAME", nullable = false )
    private String firstName;

    /** Field description */
    @Column( name = "LASTNAME", nullable = false )
    private String lastName;

    /** Field description */
    @Embedded
    private Address address;

    /** Field description */
    private String email;

    /** Field description */
    private String phone;

    /** Field description */
    @Temporal( TemporalType.TIMESTAMP )
    private Date creationDate;

    /** Field description */
    @Enumerated( EnumType.STRING )
    @Column( name = "USERTYPE", nullable = false )
    private UserType userType;

    /** Field description */
    @Column( name = "PICTURE", table = "USERSPICTURE" )
    @Lob
    @Basic( fetch = FetchType.LAZY )
    private byte[] picture;

    /** Field description */
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "billingInfoid", referencedColumnName = "oid" )
    private BillingInfo billingInfo;

    /** Field description */
    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
    private List<ContactInfo> contactInfo;

    /** Field description */
    @ManyToMany
    @JoinTable(
        name = "users_category",
        joinColumns = @JoinColumn( name = "userid", referencedColumnName = "oid" ) ,
        inverseJoinColumns = @JoinColumn( name = "categoryid", referencedColumnName = "oid" )
    )
    private List<Category> categories;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Method description
     *
     *
     * @param username
     */
    public void setUsername( String username )
    {
        this.username = username;
    }

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
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Method description
     *
     *
     * @param creationDate
     */
    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Category> getCategories()
    {
        return categories;
    }

    /**
     * Method description
     *
     *
     * @param categories
     */
    public void setCategories( List<Category> categories )
    {
        this.categories = categories;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public UserType getUserType()
    {
        return userType;
    }

    /**
     * Method description
     *
     *
     * @param userType
     */
    public void setUserType( UserType userType )
    {
        this.userType = userType;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public byte[] getPicture()
    {
        return picture;
    }

    /**
     * Method description
     *
     *
     * @param picture
     */
    public void setPicture( byte[] picture )
    {
        this.picture = picture;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public BillingInfo getBillingInfo()
    {
        return billingInfo;
    }

    /**
     * Method description
     *
     *
     * @param billingInfo
     */
    public void setBillingInfo( BillingInfo billingInfo )
    {
        this.billingInfo = billingInfo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Method description
     *
     *
     * @param password
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<ContactInfo> getContactInfo()
    {
        return contactInfo;
    }

    /**
     * Method description
     *
     *
     * @param contactInfo
     */
    public void setContactInfo( List<ContactInfo> contactInfo )
    {
        this.contactInfo = contactInfo;
    }
}
