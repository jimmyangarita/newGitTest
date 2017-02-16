package com.jdap.auction.service;

//~--- JDK imports -------------------------------------------------------------

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Class description
 *
 *
 * @version        9.0, 2012.May.15 11:11 AM
 * @author         jdap Corporation
 */
public class ActionInterceptorLogger
{
    /** Field description */
    public static Logger logger = Logger.getLogger( ActionInterceptorLogger.class.getName() );

    /**
     * Method description
     *
     *
     * @param ic
     *
     * @return
     *
     * @throws Exception
     */
    @AroundInvoke
    public Object logMethodEntry( InvocationContext ic ) throws Exception
    {
        logger.log( Level.INFO,
                    "\n -----> IC Logger,  call to ====> " + "Entering Method: "
                    + ( ( ic.getTarget() != null )
                        ?ic.getTarget().getClass().getName() + "##" + ic.getMethod().getName()
                        : ic.getMethod().getName() ) );

        return ic.proceed();
    }
}
