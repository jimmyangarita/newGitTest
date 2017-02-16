package com.jdap.auction.service.user;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.xml.user.AddressType;
import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserKind;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.exceptions.AuctionServiceValidationException;
import com.jdap.auction.model.Address;
import com.jdap.auction.model.Bidder;
import com.jdap.auction.model.BillingInfo;
import com.jdap.auction.model.Seller;
import com.jdap.auction.model.User;
import com.jdap.auction.persistence.repository.ItemRepository;
import com.jdap.auction.persistence.repository.UserRepository;
import com.jdap.auction.service.BaseActionService;

//~--- JDK imports -------------------------------------------------------------

//~--- non-JDK imports ---------------------------------------------------------
import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Class description
 *
 *
 * @version 9.0, 2013.May.06 11:57 AM
 * @author JDAP Corporation
 */
public abstract class BaseUserService extends BaseActionService
{
    /** Field description */
    protected static final String SERVICE_NAME = "UserService";

    /** Field description */
    protected static final String ERR_CODE_BID_AMOUNT = "30001";

    /** Field description */
    protected static final String MSG_BID_AMOUNT = " The user amount has to be more than cero and less " + "than 1000";

    /** Field description */
    protected static final String ERR_INVALIDAD_BID_AMOUNT = "Invalid user Amount";

    /** Field description */
    protected static final String ERR_CODE_BID_MAXAMOUNT = "30002";

    /** Field description */
    protected static final String ERR_MSG_BID_MAXAMOUNT = "Max amount MUST be grater than User amount";

    /** Field description */
    protected static final String MSG_MIN_BID_AMOUNT = "Validation Fault, Minimun price per article is $1";

    /** Field description */
    protected static final String ERR_INVALIDAD_LOW_BID_AMOUNT = "User Amount is too Low!!!";

    /** Field description */
    public static Logger logger = Logger.getLogger( BaseUserService.class.getName() );

    /** Field description */
    @Resource( name = "serviceTimeout", mappedName = "com.jdap.auction.service.main.serviceTimeout" )
    protected Integer serviceTimeout;

    /** Field description */
    @EJB
    protected UserRepository userRepository;	// can be injected on the setBibEJB() in the UserBean

    /** Field description */
    @EJB
    protected ItemRepository itemRepository;
    @Resource
    SessionContext context;

    /** Field description */
    protected static final int CLIENT_CODE_STACK_INDEX;

    static
    {

        // Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5
        // and 1.6
        int i = 0;

        for( StackTraceElement ste : Thread.currentThread().getStackTrace() )
        {
            i++;

            if( ste.getClassName().equals( BaseUserService.class.getName() ) )
            {
                break;
            }
        }

        CLIENT_CODE_STACK_INDEX = i;
    }

    /**
     * Method description
     *
     *
     * @param depth
     *
     * @return
     */
    public static String getMethodName( final int depth )
    {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        // System.
        // out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
        return ste[ ste.length - 1 - depth ].getMethodName();		// Thank you Tom Tresansky
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @throws AuctionServiceException
     */
    protected void validateUserId( UserType userType ) throws AuctionServiceException
    {
        if( null != userType.getId() )
        {
            super.validateId( SERVICE_NAME, userType.getId() );
        }
        else
        {
            apiExceptionMsg = bUtil.buildApiExceptionMsg( SERVICE_NAME, ERR_CODE_NULL_PARAMETER, ERR_NULL_PARAMETERS,
                                                          "User Id is Null", "", "" );

            throw new AuctionServiceValidationException( this.getClass().getSimpleName() + ", ERROR: "
                                                         + ERR_NULL_PARAMETERS, apiExceptionMsg );
        }
    }

    /**
     *
     * @param addressType
     * @throws AuctionServiceException
     */
    protected void validateAddressType( AddressType addressType ) throws AuctionServiceException
    {
        String exceptionNullAddressMsg = "Address basic values are null";
        String exceptionEmptyAddressMsg = "Address basic values are empty";

        if( (null == addressType) || (null == addressType.getLine1()) || (null == addressType.getCity())
            || (null == addressType.getState()) || (null == addressType.getCountry())
            || (null == addressType.getZip()) )
        {
            super.throwBasicValidationExeption( SERVICE_NAME, "AddressType", "NULL", exceptionNullAddressMsg );
            logger.info( "Temporally remove it!! for testing" );
        }

        if( !bUtil.textHasContent( addressType.getLine1() ) ||!bUtil.textHasContent( addressType.getCity() )
            ||!bUtil.textHasContent( addressType.getState() ) ||!bUtil.textHasContent( addressType.getCountry() )
            ||!bUtil.textHasContent( addressType.getZip() ) )
        {
            super.throwBasicValidationExeption( SERVICE_NAME, "AddressType", "EMPTY", exceptionEmptyAddressMsg );
        }
    }

    /**
     *
     * @param userType
     * @throws AuctionServiceException
     */
    protected void validateUserType( UserType userType ) throws AuctionServiceException
    {
        String exceptionNullUserMsg = "User basic values are null";
        String exceptionEmptyUserMsg = "User basic values are empty";

        if( (null == userType.getEmail()) || (null == userType.getFirstName()) || (null == userType.getLastName())
            || (null == userType.getPassword()) || (null == userType.getPhone()) || (null == userType.getUsername()) )
        {
            super.throwBasicValidationExeption( SERVICE_NAME, "UserType", "NULL", exceptionNullUserMsg );
        }

        if( !bUtil.textHasContent( userType.getEmail() ) ||!bUtil.textHasContent( userType.getFirstName() )
            ||!bUtil.textHasContent( userType.getLastName() ) ||!bUtil.textHasContent( userType.getPassword() )
            ||!bUtil.textHasContent( userType.getPhone() ) ||!bUtil.textHasContent( userType.getUsername() ) )
        {
            super.throwBasicValidationExeption( SERVICE_NAME, "UserType", "EMPTY", exceptionEmptyUserMsg );
        }

        logger.info( "+++++++++++++++validateUserType.validateAddressType(userType.getAddress()) "
                     + "Temporally remove it!! for testing+++++++++++" );

        // validateAddressType(userType.getAddress());
    }

    /**
     * Method description
     *
     *
     * @param user
     * @param <T>
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public <T extends UserType> T mapUserByInstanceType( User user ) throws AuctionServiceException
    {
        if( (null != user) && (user instanceof Bidder) )
        {
            Bidder bidder = (Bidder) user;
            BidderType bidderType = new BidderType();

            mapUserModelToUserType( bidder, bidderType );

            return(T) bidderType;
        }
        else if( (null != user) && (user instanceof Seller) )
        {
            Seller seller = (Seller) user;
            SellerType sellerType = new SellerType();

            mapUserModelToUserType( seller, sellerType );

            return(T) sellerType;
        }
        else if( user == null )
        {
            return null;
        }
        else
        {
            throw new AuctionServiceException( "ERROR: Not Valid User Instance Type" );
        }
    }

    /**
     *
     * @param userType
     * @param user
     * @param <T>
     * @return
     * @throws AuctionServiceException
     */
    public <T extends User> T mapUserTypetoUserModel( UserType userType, T user ) throws AuctionServiceException
    {
        validateUserType( userType );
        user.setFirstName( userType.getFirstName() );
        user.setLastName( userType.getLastName() );
        user.setUsername( userType.getUsername() );
        user.setPassword( userType.getPassword() );
        user.setEmail( userType.getEmail() );
        user.setPhone( userType.getPhone() );

        // user.setPicture(userType.getP)

        Address address = new Address();

        if( null != userType.getAddress() )
        {
            address.setStreetLine1( userType.getAddress().getLine1() );
            address.setStreeLine2( userType.getAddress().getLine2() );
            address.setCity( userType.getAddress().getCity() );
            address.setState( userType.getAddress().getState() );
            address.setCountry( userType.getAddress().getCountry() );
            address.setZipCode( userType.getAddress().getZip() );
        }

        user.setAddress( address );

        BillingInfo billingInfo = new BillingInfo();

        return user;
    }

    /**
     *
     * @param user
     * @param userType
     * @param <T>
     * @throws AuctionServiceException
     */
    protected <T extends User> void mapUserModelToUserType( T user, UserType userType ) throws AuctionServiceException
    {
        userType.setId( user.getId().toString() );
        userType.setFirstName( user.getFirstName() );
        userType.setLastName( user.getLastName() );
        userType.setUsername( user.getUsername() );
        userType.setPassword( user.getPassword() );
        userType.setEmail( user.getEmail() );
        userType.setPhone( user.getPhone() );

        String userKind = user.getUserType().getCode();		// get value

        userType.setUsertype( UserKind.valueOf( userKind ) );

        if( null != user.getCreationDate() )
        {
            XMLGregorianCalendar xmlUserCreationTime = bUtil.converToXmlCurrentime( user.getCreationDate() );

            userType.setCreationDate( xmlUserCreationTime );
        }

        AddressType address = new AddressType();

        if( null != user.getAddress() )
        {
            address.setLine1( user.getAddress().getStreetLine1() );
            address.setLine2( user.getAddress().getStreeLine2() );
            address.setCity( user.getAddress().getCity() );
            address.setState( user.getAddress().getState() );
            address.setCountry( user.getAddress().getCountry() );
            address.setZip( user.getAddress().getZipCode() );
        }

        userType.setAddress( address );
    }
}
