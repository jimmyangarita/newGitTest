package com.jdap.auction.service.stateful.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jdap.auction.service.PlaceOrderRemote;

import java.util.logging.Level;
import java.util.logging.Logger;


public class TestStatefulClient {

	public static Logger logger = Logger.getLogger(TestStatefulClient.class.getName());
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlaceOrderRemote statefulRetome;
		try {
			final Context context = getInitialContext();
			statefulRetome = (PlaceOrderRemote) context.lookup("ejb.jdap.auction.service.PlaceOrderEJB");
			logger.info("Starting Stateful test...................");
			statefulRetome.setBiderID(1L);
			statefulRetome.addItem(200L);
			statefulRetome.addItem(300L);
			statefulRetome.addItem(400L);
			statefulRetome.setShipingInfo(" 874 nw 92 nd Ave \n Plantation FL \n 33324");
			statefulRetome.setBillingInfo(" $550 Dollars Orden Info etc..");
						
			logger.info("Remote adding the order......");
			logger.info(statefulRetome.confirmOrder().toString());
			logger.info("End..............");
			
		}catch( Exception e) {
			logger.log(Level.SEVERE,"Error: " + e.getMessage(), e);
		}
	}
	
	private static Context getInitialContext() throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://10.207.67.59:7011");
		return new InitialContext(env);
	}

}
