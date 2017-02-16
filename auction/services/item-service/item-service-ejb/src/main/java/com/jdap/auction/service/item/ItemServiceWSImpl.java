package com.jdap.auction.service.item;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.Hashtable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import com.jdap.auction.common.xml.apiconfiguration.AddItemResponseType;
//import com.jdap.auction.common.xml.apiconfiguration.AddItemType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.item.ItemRemote;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.May.06 10:43 AM
 * @author         JDAP Corporation    
 */
@WebService
public class ItemServiceWSImpl
{
    ItemRemote remoteTest;
    
    public ItemServiceWSImpl ()
    {}
    

    /**
     * Method description
     *
     *
     * @param itemrequest
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    @WebMethod
    public String addItemaddItem( String itemTitle,  String itemDescription,  Double itemInitialPrice ) 
    		throws AuctionServiceException
    {
        try
        {
        	final Context context = getInitialContext();
            remoteTest = (ItemRemote) context.lookup( "ejb.jdap.auction.service.ItemEJB" );
            return null;//remoteTest.addItem( itemTitle, itemDescription, itemInitialPrice );
            
        }
        /*catch( AuctionServiceException a )
        {
            System.out.println("\"AuctionServiceException\": " + a + "\nCause: " + a.getCause() );
        }*/
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
