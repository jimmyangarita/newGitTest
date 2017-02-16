package com.jdap.auction.service.user;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.Hashtable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.May.06 10:43 AM
 * @author         JDAP Corporation    
 */
@WebService
public class UserServiceWSImpl
{
    UserRemote remoteTest;
    
    public UserServiceWSImpl ()
    {}
    

    /**
     * Method description
     *
     *
     * @param userrequest
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod
    public UserType addUser( SellerType userrequest ) throws AuctionServiceException
    {
        try
        {
        	final Context context = getInitialContext();
            remoteTest = (UserRemote) context.lookup( "ejb.jdap.auction.service.UserEJB" );
            return remoteTest.addSeller( userrequest );
            
        }
        catch( AuctionServiceException a )
        {
            System.out.println("\"AuctionServiceException\": " + a + "\nCause: " + a.getCause() );
        }
        catch( Exception e )
        {
        	System.out.println( "Error: " + e + "\nCause: " + e.getCause() );
        }
		return null;
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws NamingException
     */
    private static Context getInitialContext() throws NamingException
    {
        Hashtable env = new Hashtable();

        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put( Context.PROVIDER_URL, "t3://localhost.localdomain:7011" );

        return new InitialContext( env );
    }
}
