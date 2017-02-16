package com.jdap.auction.service;

//~--- non-JDK imports ---------------------------------------------------------

import org.python.util.InteractiveInterpreter;

import weblogic.management.scripting.utils.WLSTInterpreter;

//~--- JDK imports -------------------------------------------------------------


/**
 * Class description
 *
 *
 * @version        9.0, 2012.May.23 09:33 AM
 * @author         jdap Corporation    
 */
public class AuctionFiltersWLST
{
    static InteractiveInterpreter interpreter = null;

    /**
     * Constructs ...
     *
     */
    public AuctionFiltersWLST()
    {
        interpreter = new WLSTInterpreter();
    }

    /**
     * Method description
     *
     */
    public static void connect()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( "connect('weblogic','Passw0rd','t3://10.207.67.59:7001')" );
        interpreter.exec( buffer.toString() );
    }

    /**
     * Method description
     *
     */
    public static void createFilters()
    {
        StringBuffer buf = new StringBuffer();

        buf.append( startTransaccion() );
        buf.append( "cd('Security/auctionDomain')\n" );
        buf.append( "filter = jarray.array(['10.207.32.138 10.207.67.59 7011 deny'],java.lang.String)\n" );
        buf.append( "cmo.setConnectionFilterRules(filter)\n" );
        buf.append( endTransaccion() );
        buf.append( "print 'Script run successfully...'\n" );
        interpreter.exec( buf.toString() );
    }

    /**
     * Method description
     *
     *
     * @return
     */
    private static String startTransaccion()
    {
        StringBuffer buf = new StringBuffer();

        buf.append( "edit()\n" );
        buf.append( "startEdit()\n" );

        return buf.toString();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    private static String endTransaccion()
    {
        StringBuffer buf = new StringBuffer();

        buf.append( "save()\n" );
        buf.append( "activate(block='true')\n" );

        return buf.toString();
    }
}
