package com.jdap.auction.service.bid;

//~--- non-JDK imports --------------------------------------------------------

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;


/**
 * Class description
 *
 *
 * @version 9.0, 2013.May.06 11:57 AM
 * @author JDAP Corporation
 */
public abstract class BaseBidService {

    /** Field description */
    protected static final String SERVICE_NAME = "BidService";

    /** Field description */
    protected static final String ERR_CODE_BID_AMOUNT = "30001";

    /** Field description */
    protected static final String MSG_BID_AMOUNT = " The bid amount has to be more than cero and less " + "than 1000";

    /** Field description */
    protected static final String ERR_INVALIDAD_BID_AMOUNT = "Invalid bid Amount";

    /** Field description */
    protected static final String ERR_CODE_BID_MAXAMOUNT = "30002";

    /** Field description */
    protected static final String ERR_MSG_BID_MAXAMOUNT = "Max amount MUST be grater than Bid amount";

    /** Field description */
    protected static final String MSG_MIN_BID_AMOUNT = "Validation Fault, Minimun price per article is $1";

    /** Field description */
    protected static final String ERR_INVALIDAD_LOW_BID_AMOUNT = "Bid Amount is too Low!!!";

    /** Field description */
    public static Logger       logger = Logger.getLogger(BaseBidService.class.getName());
    protected static final int CLIENT_CODE_STACK_INDEX;

    static {

        // Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5
        // and 1.6
        int i = 0;

        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            i++;

            if (ste.getClassName().equals(BaseBidService.class.getName())) {
                break;
            }
        }

        CLIENT_CODE_STACK_INDEX = i;
    }

    /** Field description */
    @Resource(
        name       = "serviceTimeout",
        mappedName = "com.jdap.auction.service.main.serviceTimeout"
    )
    protected Integer serviceTimeout;


    @Resource
    SessionContext           context;

    public static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        // System.
        // out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName(); //Wrong, fails for depth = 0
        return ste[ste.length - 1 - depth].getMethodName();    // Thank you Tom Tresansky
    }

    
   

}


//~ Formatted by Jindent --- http://www.jindent.com
